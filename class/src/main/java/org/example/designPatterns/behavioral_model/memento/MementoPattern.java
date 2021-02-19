package org.example.designPatterns.behavioral_model.memento;

/**
 * @author yoveuio
 * @version 1.0
 * @className MementoPattern
 * @description 备忘录模式的实现
 * @date 2021/2/19 14:52
 */
@SuppressWarnings("unused")
public class MementoPattern {
    public static void main(String[] args) {
        Originator or = new Originator();
        Caretaker cr = new Caretaker();

        or.setState("S0");
        System.out.println("初始状态:" + or.getState());
        // 保存状态
        cr.setMemento(or.createMemento());
        or.setState("S1");
        System.out.println("新的状态:" + or.getState());
        or.restoreMemento(cr.getMemento());
        System.out.println("恢复状态:" + or.getState());
    }

    static class Memento {
        private String state;

        public Memento(String state) {
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    /**
     * 发起人
     */
    static class Originator {
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento createMemento() {
            return new Memento(state);
        }

        public void restoreMemento(Memento memento) {
            setState(memento.getState());
        }
    }

    /**
     * 管理者
     */
    static class Caretaker {
        private Memento memento;

        public void setMemento(Memento memento) {
            this.memento = memento;
        }

        public Memento getMemento (){
            return memento;
        }

    }
}
