/**
 * @ClassName JVMTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/11/5 16:14
 * @Version 1.0
 */
public class JVMTest {

    static class A{
        public static int a = 10;

        static {
            System.out.println("init A");
        }
    }

    static class B extends A{
        static {
            System.out.println("init B");
        }
    }

    /**
     * 数组引用称为被动引用，不会触发初始化
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        //静态字段只会触发直接定义该字段的类的初始化，即只会初始化A
        System.out.println(B.a);
        //数组类型的new并不会直接创建组件类型，而是[B类型，因此没有初始化任何类
        B[] bs = new B[10];
    }
}
