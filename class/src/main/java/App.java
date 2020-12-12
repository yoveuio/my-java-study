

/**
 * @author yoveuio
 * @version 1.0
 * @className App
 * @description TODO
 * @date 2020/12/10 10:45
 */
public class App {
    static class TestA{
        public void start() throws Exception{
            System.out.println("TestA");
            throw new Exception();
        }
    }

    static class TestB extends TestA{
        @Override
        public void start() throws RuntimeException{
            System.out.println("TestB");
            //if (Math.random() < 0.5)throw new Exception();
        }
    }

    public static void main(String[] args) {
        TestB testB = new TestB();
        testB.start();
    }
}
