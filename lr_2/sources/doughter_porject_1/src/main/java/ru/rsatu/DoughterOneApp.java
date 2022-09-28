package ru.rsatu;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.*;

public class DoughterOneApp 
{
    public static void main( String[] args )
    {
        System.out.println( "RUN DoughterOneApp" );
    }

    public static double GetMean( Integer[] values )
    {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        for( int i=0; i < values.length; i++ )
        {
            ds.addValue( values[i] );
        }
        
        return ds.getMean();
    }
}