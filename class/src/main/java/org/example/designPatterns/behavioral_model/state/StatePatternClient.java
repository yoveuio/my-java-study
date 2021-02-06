package org.example.designPatterns.behavioral_model.state;

/**
 * @author yoveuio
 * @version 1.0
 * @className StatePatternClient
 * @description 状态模式的实现
 * @date 2021/2/6 14:16
 */
public class StatePatternClient {
    public static void main(String[] args) {
        Context context = new Context();
        context.handle();
        context.handle();
        context.handle();
        context.handle();
    }

    static class Context {
        private State state;

        public Context() {
            this.state = new ConcreteStateA();
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void handle() {
            state.Handler(this);
        }
    }

    static abstract class State {
        public abstract void Handler(Context context);
    }

    static class ConcreteStateA extends State {
        @Override
        public void Handler(Context context) {
            System.out.println("当前状态是 A.");
            context.setState(new ConcreteStateB());
        }

    }

    static class ConcreteStateB extends State {
        @Override
        public void Handler(Context context) {
            System.out.println("当前状态是 B.");
            context.setState(new ConcreteStateA());
        }
    }
}
