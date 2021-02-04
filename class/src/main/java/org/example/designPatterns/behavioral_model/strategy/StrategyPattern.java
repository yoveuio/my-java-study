package org.example.designPatterns.behavioral_model.strategy;

/**
 * @author yoveuio
 * @version 1.0
 * @className strategyPattern
 * @description TODO
 * @date 2021/2/4 13:49
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context c = new Context();
        Strategy s = new ConcreteStrategy();
        c.setStrategy(s);
        c.strategyMethod();
    }

    // 抽象策略类
    interface Strategy {
        void strategyMethod();
    }

    // 具体策略类
    static class ConcreteStrategy implements Strategy {
        @Override
        public void strategyMethod() {
            System.out.println("具体策略A的策略方法被调用");
        }
    }

    static class ConcreteStrategyB implements Strategy {
        @Override
        public void strategyMethod() {
            System.out.println("具体策略B的策略方法被调用");
        }
    }

    static class Context {
        private Strategy strategy;

        public Strategy getStrategy() {
            return strategy;
        }

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void strategyMethod() {
            strategy.strategyMethod();
        }
    }
}
