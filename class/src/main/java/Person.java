/**
 * @ClassName Person
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/13 11:02
 * @Version 1.0
 */
class Person{
    static{
        System.out.println("static");
    }
    Person(){
        System.out.println("构造");
    }
}
class Demo{
    public static void main(String[] args){
        Person p = new Person();
    }
}
