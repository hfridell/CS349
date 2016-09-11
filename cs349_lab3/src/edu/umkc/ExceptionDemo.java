package edu.umkc;

import java.io.PrintStream;
import java.util.Random;

public class ExceptionDemo {

    static private int checkedCount = 0;
    static private int unCheckedCount = 0;
    static private int noExceptionCount = 0;

    static public void f() throws IllegalStateException {
        Random ran = new Random();
        // will return 0, 1 or 2 with equal probability
        switch(ran.nextInt(3)) {
            case 0:
                ++checkedCount;
                throw(new IllegalStateException());
            case 1:
                ++unCheckedCount;
                throw(new RuntimeException());
            default:
                ++noExceptionCount;

        }
    }

    static public void printExceptionCount(PrintStream out){
        out.println("f() was called: " + (checkedCount + unCheckedCount + noExceptionCount) + " times.");
        out.println("Checked Exceptions thrown:\t " + checkedCount);
        out.println("Unchecked Exceptions thrown: " + unCheckedCount);
        out.println("Exceptions not thrown:\t\t " + noExceptionCount);
    }

}
