package org.example.exception;

/**
 * @ClassName ExceptionTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/9/5 21:51
 * @Version 1.0
 */
public class ExceptionTest extends Exception {

    public static class InheritingException{
        public static void f() throws ExceptionTest {
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println(1);
            System.out.println("throw from f()");
            System.err.println("throw from f()");
            throw new ExceptionTest();
        }

        public static void main(String[] args) {
            try {
                InheritingException.f();
            } catch (ExceptionTest exceptionTest) {
            }
        }
    }

}
