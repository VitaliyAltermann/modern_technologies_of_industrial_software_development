package ru.rsatu;

public class Test {
    private String[] mes={
        "Класс загружен обычным загрузчиком",
        "Класс загружен загрузчиком MyCL"
    };

    // приватное поле, указывающее на выводимое сообщение (индекс для mes)
    private static int i=0;
    public void Hello(){
        System.out.println(mes[i]);
    };
}