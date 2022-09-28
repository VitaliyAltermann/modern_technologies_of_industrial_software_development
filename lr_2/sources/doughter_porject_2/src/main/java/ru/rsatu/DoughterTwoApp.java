package ru.rsatu;
import ru.rsatu.DoughterOneApp;

import java.util.*;

public class DoughterTwoApp 
{
    public static void main( String[] args )
    {
        System.out.println( "RUN DoughterTwoApp" );
        
        System.out.println( "Введите размер массива: " );
        Scanner scan = new Scanner( System.in );
        int size = scan.nextInt();
        
        System.out.println( "Введите числа для нахождения среднего арифметического: " );
        ArrayList<Integer> values_list = new ArrayList<>();
        for( int i = 0; i < size; i++ )
        {
           values_list.add( scan.nextInt() );
        }
        
        Integer[] values = new Integer[values_list.size()];
        values = values_list.toArray(values);
        System.out.printf( "Результат: %f", DoughterOneApp.GetMean( values ) );
    }
}
