package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws ClassNotFoundException {

        Collection<String> test = new ArrayList<>();
        Collections.addAll(test, "test1", "test2");
        Class<?> aClass = Class.forName("org.example.AppTest");
        System.out.println(aClass);

    }


}
