package ru.rsatu.pojo;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Обёртка над стандратным интерфейсом работы с БД
 */
public class DbWrapper {

    /**
     * Метод чтения данных из БД
     * @param table_name имя таблицы
     * @param filter условие фильтрации
     * @return результат выполнения sql-запроса
     */
    public ResultSet Read(@NotNull String table_name, @NotNull String filter) throws SQLException {
        String query = "SELECT * FROM " + table_name;
        if (!filter.isEmpty())
            query += " WHERE " + filter;
        return this.statement.executeQuery(query);
    }

    /**
     * Метод добавления данных в БД
     * @param table_name имя таблицы
     * @param data_to_insert данные для создания
     */
    public void Create(@NotNull String table_name,
                       @NotNull List<Map<String,String>> data_to_insert) throws SQLException {
        // Формируем список Заполняемых полей
        String fields_str = "";
        for ( Map.Entry<String,String> field : data_to_insert.get(0).entrySet() ) {
            if(!fields_str.isEmpty())
                fields_str += ",";
            fields_str += field.getKey();
        }
        // Формируем список значений
        String values_str = "";
        for ( Map<String,String> values_map : data_to_insert ) {
            // Формируем один элемент данных для импорта
            String values_item_str = "";
            for ( Map.Entry<String,String> value : values_map.entrySet() ) {
                if (!values_item_str.isEmpty())
                    values_item_str += ",";
                values_item_str += value.getValue();
            }
            // Формируем итоговый набор данных для импорта
            if (!values_str.isEmpty())
                values_str += ",";
            values_str += "(" + values_item_str + ")";
        }
        String query = "INSERT INTO " + table_name + " (" + fields_str + ") VALUES " + values_str;
        this.statement.execute(query);
    }

    /**
     * Метод обновления данных в БД
     * @param table_name имя таблицы
     * @param data_to_update данные для обновления
     * @param filter фильтр для обновления
     */
    public void Update(@NotNull String table_name,
                       @NotNull Map<String,String> data_to_update,
                       @NotNull String filter) throws SQLException {
        String new_values_str = "";
        for ( Map.Entry<String,String> new_value : data_to_update.entrySet() ) {
            if (!new_values_str.isEmpty())
                new_values_str += ",";
            new_values_str += new_value.getKey() + " = " + new_value.getValue();
        }
        String query = "UPDATE " + table_name + " SET " + new_values_str;
        if (!filter.isEmpty())
            query += " WHERE " + filter;
        this.statement.execute(query);
    }

    /**
     * Метод удаления данных из БД
     * @param table_name имя таблицы
     * @param filter условие фильтрации
     */
    public void Delete(@NotNull String table_name, @NotNull String filter) throws SQLException {
        String query = "DELETE FROM " + table_name;
        if (!filter.isEmpty())
            query += " WHERE " + filter;
        this.statement.execute(query);
    }

    /**
     * Создать таблицу с указанием имени и полей
     * @param table_name имя таблицы
     * @param fields поля таблицы формата <имя> <---> <тип>
     * @throws SQLException
     */
    public void CreateTableIfNeed(@NotNull String table_name,
                                  @NotNull Map<String,String> fields) throws SQLException {
        String fields_str = "";
        for ( Map.Entry<String,String> pair : fields.entrySet() ) {
            if (!fields_str.isEmpty())
                fields_str += ", ";
            fields_str += pair.getKey() + " " + pair.getValue();
        }
        this.statement.execute("CREATE TABLE IF NOT EXISTS " + table_name + " (" + fields_str + ")");
    }

    /**
     * Применить изменения в БД
     * @throws SQLException
     */
    public void Commit() throws SQLException {
        System.out.println("Применяем изменения в БД");
        this.connection.commit();
    }

    /**
     * Откатить изменения в БД
     * @throws SQLException
     */
    public void RollbackChanges() throws SQLException {
        System.out.println("Откатываем изменения в БД");
        this.connection.rollback();
    }

    /**
     * Установить соединение с БД
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void SetupConection() throws SQLException, ClassNotFoundException {
        System.out.println("Устанавливаем соединение с БД");
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(url,userName,userPassword);
        this.connection.setAutoCommit(false);
        this.statement = this.connection.createStatement();
    }

    /**
     * Закрыть соединение с БД
     * @throws SQLException
     */
    public void CloseConection() throws SQLException {
        this.connection.close();
    }

    /**
     * Установить Имя БД
     * @param dbName имя БД
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Установить имя пользователя
     * @param userName имя пользователя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Установить пароль пользователя
     * @param userPassword пароль пользователя
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private String dbName;
    private String userName;
    private String userPassword;
    private Connection connection;
    private Statement statement;
}