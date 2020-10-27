package org.example;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DijkstraSP;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args ) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(1, 3);
        System.out.println(map.get(1));
    }
}
