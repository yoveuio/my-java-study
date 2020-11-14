package org.example;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DijkstraSP;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    static abstract class Human{}
    static class Man extends Human {}
    static class Woman extends Human {}

    public void sayHello(Human guy) {
        System.out.println("hello guy");
    }
    public void sayHello(Man man) {
        System.out.println("hello gentleman");
    }
    public void sayHello(Woman woman) {
        System.out.println("hello lady");
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        App app = new App();
        app.sayHello(man);
        app.sayHello(woman);
    }
}
