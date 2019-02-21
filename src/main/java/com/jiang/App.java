package com.jiang;

import com.jiang.entity.Student;
import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Hello world!
 *
 */
public class App {

    public static Integer i3 = 1;

    public static void main( String[] args ) {

        Student s1 = new Student(1);

        Integer i1 = new Integer(1);

        Integer i2 = new Integer(1);

        System.out.println(i1 == i2);

        System.out.println(i1 == i3);

        System.out.println(s1.getStuNo() == i3);

    }
}
