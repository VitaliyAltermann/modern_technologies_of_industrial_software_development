package ru.rsatu;

import ru.rsatu.pojo.*;

import javax.print.Doc;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // 1 - ООП
        System.out.println("\n// 1 - ООП");
        Document passport = new Passport(
            "Паспорт гражданина РФ",
            "58 64 563145",
            new Date(108, 3, 20)
        );
        Document birthCertificate = new BirthCertificate(
            "Свидетельство о рождении",
            "567139",
            new Date(94, 3, 15),
            "Иванова М.Д.",
            "Иванов С.В."
        );
        Document snils = new SNILS(
            "СНИЛС",
            "681-254-936-85",
            new Date(94, 3, 26)
        );
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(
            "Список документов:" +
            "\n" + passport.getType()         + " / "  + passport.getNumber()         + " / "  + df.format( passport.getDateOfIssue() ) +
            "\n" + birthCertificate.getType() + " / "  + birthCertificate.getNumber() + " / "  + df.format( birthCertificate.getDateOfIssue() ) +
            "\n" + snils.getType()            + " / "  + snils.getNumber()            + " / "  + df.format( snils.getDateOfIssue() )
        );


        // 2 - Reflection
        System.out.println("\n// 2 - Reflection");
        Reflection.printAllFieldAndMethods( BirthCertificate.class );
        Reflection.printAllParents( SNILS.class );
        Reflection.invokeGetMethods( passport );

        // 3 - Collection
        System.out.println("\n// 3 - Collection");
        System.out.println("Работа с коллекцией List");
        List<Document> list_of_docs = new ArrayList<>();
        list_of_docs.add( passport );
        list_of_docs.add( birthCertificate );
        list_of_docs.add( snils );
        list_of_docs.add(
            new Passport(
                "Паспорт гражданина Республики Беларусь",
                "MC1100586",
                new Date(101, 9, 11)
            )
        );
        for ( Document doc : list_of_docs ) {
            System.out.println( "-----------------------------------" );
            System.out.println( "Тип документа: " + doc.getType() );
            System.out.println( "Номер документа: " + doc.getNumber() );
            System.out.println( "///////////////////////////////////" );
        }

        System.out.println();
        System.out.println("Работа с коллекцией Set");
        Set<Document> set_of_docs = new HashSet<>();
        set_of_docs.add( passport );
        set_of_docs.addAll( list_of_docs );
        set_of_docs.add( birthCertificate );
        for ( Document doc : set_of_docs ) {
            System.out.println( "-----------------------------------" );
            System.out.println( "Тип документа: " + doc.getType() );
            System.out.println( "Номер документа: " + doc.getNumber() );
            System.out.println( "///////////////////////////////////" );
        }

        System.out.println();
        System.out.println("Работа с коллекцией Map");
        Map<String, Document> map_of_docs = new HashMap<>();
        map_of_docs.put( snils.getNumber(), snils );
        for ( Document doc : set_of_docs ) {
            map_of_docs.put( doc.getNumber(), doc );
        }
        map_of_docs.put( passport.getNumber(), passport );
        map_of_docs.forEach((key, value) -> {
            System.out.println("-----------------------------------");
            System.out.println("Тип документа: " + value.getType());
            System.out.println("Номер документа: " + key);
            System.out.println( "///////////////////////////////////" );
        });

        // 4 - Generic
        System.out.println("\n//4 - Generic");
        System.out.println("Работа с шаблонным методом и классом:");
        Pair<Integer,Integer> int_to_int_pair = new Pair<>(10, 20);
        print( int_to_int_pair );
        Pair<String,Integer> str_to_int_pair = new Pair<>("ten", 20);
        print( str_to_int_pair );
    }

    public static <T> void print(T value) {
        System.out.println("Было передано значение вида: " + value.toString());
    }

}