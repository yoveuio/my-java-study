

/**
 * @author yoveuio
 * @version 1.0
 * @className App
 * @description TODO
 * @date 2020/12/10 10:45
 */
public class App {
    static class Test{
        public static void main(String[] args) {
            Father hello = new Father() {
                @Override
                public void show() {
                    System.out.println("hello world");
                }
            };

            Object obj = hello;
        }
    }

    static class Father{
        public void show() {
            System.out.println("hello father");
        }
    }
}
