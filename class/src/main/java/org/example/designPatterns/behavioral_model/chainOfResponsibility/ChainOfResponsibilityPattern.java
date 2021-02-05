package org.example.designPatterns.behavioral_model.chainOfResponsibility;

/**
 * @author yoveuio
 * @version 1.0
 * @className ChainOfResponsibility
 * @description 职责链模式
 * @date 2021/2/5 14:39
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handlerRequest("two");
    }

    static abstract class Handler {
        private Handler next;

        public void setNext(Handler next) {
            this.next = next;
        }

        public Handler getNext() {
            return next;
        }

        public abstract void handlerRequest(String request);
    }

    static class ConcreteHandler1 extends Handler {
        public void handlerRequest (String request) {
            if (request.equals("one")) {
                System.out.println("具体处理者1负责处理该请求");
            } else {
                if (getNext() != null) {
                    getNext().handlerRequest(request);
                } else {
                    System.out.println("没有人可以处理该请求");
                }
            }
        }
    }

    static class ConcreteHandler2 extends Handler {
        public void handlerRequest (String request) {
            if (request.equals("two")) {
                System.out.println("具体处理者2负责处理该请求");
            } else {
                if (getNext() != null) {
                    getNext().handlerRequest(request);
                } else {
                    System.out.println("没有人可以处理该请求");
                }
            }
        }
    }
}
