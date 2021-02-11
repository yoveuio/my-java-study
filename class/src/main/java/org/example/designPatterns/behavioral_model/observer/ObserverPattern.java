package org.example.designPatterns.behavioral_model.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className ObserverPattern
 * @description 观察者模式的实现
 * @date 2021/2/11 16:07
 */
public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();
    }

    static abstract class Subject {
        protected List<Observer> observers = new ArrayList<>();

        public void add(Observer observer) {
            observers.add(observer);
        }

        public void remove (Observer observer) {
            observers.remove(observer);
        }

        public abstract void notifyObserver();
    }

    static class ConcreteSubject extends Subject{
        @Override
        public void notifyObserver() {
            System.out.println("具体目标对象改变...");
            System.out.println("------------------");

            for (Object obs: observers) {
                ((Observer) obs).update();
            }
        }
    }

    interface Observer {
        void update();
    }

    static class ConcreteObserver1 implements Observer {
        @Override
        public void update() {
            System.out.println("具体观察者对象1做出反应");
        }
    }

    static class ConcreteObserver2 implements Observer {
        @Override
        public void update() {
            System.out.println("具体观察者对象2做出反应");
        }
    }
}
