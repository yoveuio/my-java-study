package homework.communication;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName Sum
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/27 11:36
 * @Version 1.0
 */
public class Sum {
    static Exchanger<Integer> exchanger = new Exchanger<>();

    static class A implements Runnable {
        static Random random = new Random();

        @Override
        public void run() {
            try {
                int[] nums = new int[5];
                //记录长度
                exchanger.exchange(nums.length);
                for (int i = 0; i < nums.length; ++i) {
                    nums[i] = random.nextInt(100) + 1;
                    System.out.println("num[" + i + "] = "+ nums[i]);
                    //阻塞线程直到另一个exchanger匹配
                    exchanger.exchange(nums[i]);
                    //让程序不要运行地太快导致输出跟不上
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class B implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            try {
                int n = exchanger.exchange(sum);
                for (int i=0; i<n; i++) {
                    sum += exchanger.exchange(sum);
                    System.out.println("sum = " + sum);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new A());
        Future<Integer> submit = executorService.submit(new B());
        executorService.shutdown();
        System.out.println(submit.get());
    }
}
