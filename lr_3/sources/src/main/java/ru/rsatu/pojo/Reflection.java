package ru.rsatu.pojo;

import jdk.dynalink.Operation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Обёртка для проверки рефлексии
 */
public class Reflection {
    /**
     * Выгружаем все поля и методы класса
     * @param clazz - класс
     */
    static public void printAllFieldAndMethods(Class clazz) {
        System.out.println("--------------------------------------");
        System.out.println("- Выгружаем все поля и методы класса " + clazz.getName() + " -" );
        System.out.println("--------------------------------------");
        System.out.println("- Поля:");
        Class superclass = clazz.getSuperclass();
        for (Field fld : superclass.getDeclaredFields()) {
            System.out.println(fld.getName());
        }
        System.out.println("- Методы:");
        for (Method fld : superclass.getDeclaredMethods()) {
            System.out.println(fld.getName());
        }
        System.out.println("//////////////////////////////////////");
    }

    /**
     * Выводим всех предков класса
     * @param clazz - класс
     */
    static public void printAllParents(Class clazz) {
        System.out.println( "--------------------------------------" );
        System.out.println( "- Выводим всех предков класса " + clazz.getName() + " -" );
        System.out.println( "--------------------------------------" );
        Class superclass = clazz.getSuperclass();
        System.out.println("- Классы:");
        printParent( superclass );
        System.out.println("- Интерфейсы:");
        for (Class intrface : superclass.getInterfaces()) {
            System.out.println(intrface.getName());
        }
        System.out.println( "//////////////////////////////////////" );
    }

    static private void printParent(Class clazz)
    {
        if( clazz == null ) // || Object.class.equals(clazz)
            return;
        System.out.println(clazz.getName());
        printParent(clazz.getSuperclass());
    }

    /**
     * Вызывать гетторы указанного класса
     * @param clazz - класс
     */
    static public void invokeGetMethods(IDocument doc) {
        System.out.println( "--------------------------------------" );
        System.out.println( "- Вызываем часть методов класса " + doc.getClass().getName() + " -" );
        System.out.println( "--------------------------------------" );
        for (Method method : doc.getClass().getMethods() ) {
            if( isGetter(method) ) {
                try {
                    System.out.println( "Метод '" + method.getName() + "' вернул: " + method.invoke( doc ) );
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println( "//////////////////////////////////////" );
    }

    static private boolean isGetter(Method metod) {
        return metod.getName().startsWith("get") &&
               metod.getParameterCount() == 0 &&
               !void.class.equals(metod.getReturnType());
    }
}
