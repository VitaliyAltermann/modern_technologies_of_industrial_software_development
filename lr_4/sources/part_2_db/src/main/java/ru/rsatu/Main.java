package ru.rsatu;

import org.jetbrains.annotations.NotNull;
import ru.rsatu.pojo.DbWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Устанавливаем параметры подключения к БД
        System.out.println("Устанавливаем параметры подключения к БД");
        DbWrapper db = new DbWrapper();
        db.setDbName("hibernate");
        db.setUserName("hibernate");
        db.setUserPassword("hibernate");

        // Устанавливаем соединение с БД
        db.SetupConection();

        // Создаём таблицу
        CreateTable(db);

        // Вносим стартовые данные транзакцией
        ReadAllValues(db);
        InitTable(db);
        UpdateById(db, 1);
        ReadAllValues(db);
        DeleteById(db, 1);
        ReadAllValues(db);
        db.Commit();

        // Проверяем откат транзакции
        try {
            // Посмотрим что есть в базе на данный момент
            ReadAllValues(db);
            // Удалим одно поле
            DeleteById(db, 2);
            // Посмотрим что есть в базе на данный момент
            ReadAllValues(db);
            // Проведём инициализацию ещё раз (тут будет выкинуто исключение)
            throw new SQLException();
        }
        catch (SQLException ex)
        {
            System.out.println("Обработали исключение");
            db.RollbackChanges();
        }
        // Посмотрим что есть в базе на данный момент
        ReadAllValues(db);

        // Закрываем соединение с БД
        db.CloseConection();
    }

    private static void CreateTable(@NotNull DbWrapper db) throws SQLException {
        System.out.println("Создаём таблицу Persons с полями PersonId и Name");
        String table_name = "Persons";
        Map<String,String> fields = new HashMap<>();
        fields.put("PersonId","int");
        fields.put("Name","varchar(255)");
        db.CreateTableIfNeed(table_name, fields);
    }

    private static void InitTable(@NotNull DbWrapper db) throws SQLException {
        System.out.println("Заполняем таблицу Persons 4 строкамии:");
        String table_name = "Persons";
        List<Map<String,String>> data_to_insert = new ArrayList<>();
        {
            System.out.println("PersonId: 1, Name: Федор");
            Map<String, String> first_values = new HashMap<>();
            first_values.put("PersonId", "1");
            first_values.put("Name", "'Федор'");
            data_to_insert.add(first_values);
        }
        {
            System.out.println("PersonId: 2, Name: Иван");
            Map<String, String> second_values = new HashMap<>();
            second_values.put("PersonId", "2");
            second_values.put("Name", "'Иван'");
            data_to_insert.add(second_values);
        }
        {
            System.out.println("PersonId: 3, Name: Дмитрий");
            Map<String, String> third_values = new HashMap<>();
            third_values.put("PersonId", "3");
            third_values.put("Name", "'Дмитрий'");
            data_to_insert.add(third_values);
        }
        {
            System.out.println("PersonId: 4, Name: Иван");
            Map<String, String> fourth_values = new HashMap<>();
            fourth_values.put("PersonId", "4");
            fourth_values.put("Name", "'Иван'");
            data_to_insert.add(fourth_values);
        }
        db.Create(table_name, data_to_insert);
    }

    private static void DeleteById(@NotNull DbWrapper db, @NotNull Integer id) throws SQLException {
        System.out.println("Удаляем данные по идентификаторку " + id.toString());
        String table_name = "Persons";
        String field_name = "PersonId";
        db.Delete(table_name, field_name + " = " + id.toString());
    }

    private static void UpdateById(@NotNull DbWrapper db, @NotNull Integer id) throws SQLException {
        System.out.println("Обновляем данные по идентификаторку.\n" +
                           "Обновили PersonId: " + id.toString() + ", Name: Федор Иванович");
        Map<String, String> new_values = new HashMap<>();
        new_values.put("PersonId", "1");
        new_values.put("Name", "'Федор Иванович'");
        String table_name = "Persons";
        db.Update(table_name, new_values, "PersonId = " + id.toString());
    }

    private static void ReadAllValues(@NotNull DbWrapper db) throws SQLException {
        System.out.println("Читаем все данные из БД");
        String table_name = "Persons";
        ResultSet rs = db.Read(table_name,"");
        System.out.println("Получили следующее:");
        while (rs.next()) {
            System.out.println("PersonId: " + rs.getString(1) + ", Name: " + rs.getString(2));
        };
    }
}