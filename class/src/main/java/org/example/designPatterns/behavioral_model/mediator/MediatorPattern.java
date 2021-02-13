package org.example.designPatterns.behavioral_model.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoveuio
 * @version 1.0
 * @className MediatorPattern
 * @description 中介者模式
 * @date 2021/2/13 14:58
 */
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator md = new ConcreteMediator();
        Colleague c1, c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("----------");
        c2.send();
    }

    static abstract class Mediator{
        public abstract void register(Colleague colleague);

        public abstract void relay(Colleague cl);
    }

    static class ConcreteMediator extends Mediator {
        private List<Colleague> colleagues = new ArrayList<>();

        @Override
        public void register(Colleague colleague) {
            if (!colleagues.contains(colleague)) {
                colleagues.add(colleague);
                colleague.setMedium(this);
            }
        }

        @Override
        public void relay(Colleague cl) {
            for (Colleague ob: colleagues) {
                if (!ob.equals(cl)) {
                    ob.receive();
                }
            }
        }
    }

    static abstract class Colleague {
        protected Mediator mediator;

        public void setMedium(Mediator mediator) {
            this.mediator = mediator;
        }

        public abstract void receive();

        public abstract void send();
    }

    static class ConcreteColleague1 extends Colleague{

        @Override
        public void receive() {
            System.out.println("具体同事类1收到请求");
        }

        @Override
        public void send() {
            System.out.println("具体同事类1发出请求");
            mediator.relay(this);
        }
    }

    static class ConcreteColleague2 extends Colleague{

        @Override
        public void receive() {
            System.out.println("具体同事类2收到请求");
        }

        @Override
        public void send() {
            System.out.println("具体同事类2发出请求");
            mediator.relay(this);
        }
    }
}
