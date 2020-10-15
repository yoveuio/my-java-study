/**
 * @ClassName Person
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/13 11:02
 * @Version 1.0
 */
class Person extends Thread implements Runnable{
    static{
        System.out.println("static");
    }
    Person(){
        System.out.println("构造");
    }

    @Override
    public void run() {
        System.out.println(1);
    }
}
class Demo{
    public static void main(String[] args){
        Person p = new Person();
        String name = Thread.currentThread().getName();
        System.out.println(name);
        p.start();
    }
}
