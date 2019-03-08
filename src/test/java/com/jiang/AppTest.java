package com.jiang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
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
    public void testDateUtils_01() {
        Integer days = 5;
        Date result = null;
        try {
            Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。

            cal.add(Calendar.DAY_OF_YEAR, + days);
//            cal.set(Calendar.HOUR, 11);
//            cal.set(Calendar.MINUTE, 59);
//            cal.set(Calendar.SECOND, 59);
//            cal.set(Calendar.MILLISECOND, 999);
            //通过格式化输出日期
            result = cal.getTime();
        } catch (Exception e) {
        }

        System.out.println(new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").format(result));
    }


    @Test
    public void aa_01 (){
        String[] aa = null;

        for(String s : aa){
            System.out.println(s);
        }

    }

}
