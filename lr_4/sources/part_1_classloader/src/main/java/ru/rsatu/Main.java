package ru.rsatu;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException,
                                                  InstantiationException,
                                                  NoSuchMethodException,
                                                  InvocationTargetException,
                                                  IllegalAccessException {
        //создание своего загрузчика
        MyCL mycl=new MyCL(Main.class.getClassLoader());

        //обычная загрузка класса
        System.out.println("Обычная загрузка класса");
        Test t1=new Test();
        t1.Hello();
        System.out.println();

        //загрузка своим загрузчиком
        System.out.println("Загрузка своим загрузчиком");
        //загрузка по имени с указанием своего загрузчика
        Class<?> test= Class.forName("ru.rsatu.Test",true,mycl);
        //создание объекта класса Test
        Object ob=test.getDeclaredConstructor().newInstance();
        Method a=ob.getClass().getMethod("Hello");
        //вызов метода
        a.invoke(ob);
    }
}