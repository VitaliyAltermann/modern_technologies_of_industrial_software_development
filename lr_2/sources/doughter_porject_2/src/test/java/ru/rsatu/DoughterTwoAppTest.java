package ru.rsatu;

import static org.junit.Assert.assertTrue;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import org.junit.Test;

/**
 * Unit test for simple DoughterTwoApp.
 */
public class DoughterTwoAppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAbs()
    {
        double[] values = new double[]{1,3,5};
        DescriptiveStatistics ds = new DescriptiveStatistics();
        for( int i = 0; i < 3; i++ )
        {
            ds.addValue(values[i]);
        }
        int real_result = (int)ds.getMean();
        int valide_result = 3;
        assertTrue( real_result == valide_result );
    }
}
