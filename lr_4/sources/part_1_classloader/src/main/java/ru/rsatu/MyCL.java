package ru.rsatu;
import java.lang.reflect.Field;

public class MyCL extends ClassLoader{
    public MyCL(ClassLoader s){
        super(s);
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        System.out.println("Загрузка класса "+ name);
        Class<?> a=super.loadClass(name);
        try {
            // получение доступа к приватному полю и его изменение
            Field i=a.getDeclaredField("i");
            i.setAccessible(true);
            i.set(a,1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return a;
    }
}