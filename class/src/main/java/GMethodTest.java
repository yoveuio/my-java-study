import jdk.nashorn.internal.objects.NativeMath;

import java.util.ArrayList;

/**
 * @ClassName GMethodTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/10 10:35
 * @Version 1.0
 */
public class GMethodTest {

    public static void main(String[] args) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<String> words = new ArrayList<String>();
            ArrayList<Circle>  circles = new ArrayList<Circle>();
            list.add(14);
            list.add(24);
            list.add(4);
            list.add(12);

            words.add("red");
            words.add("green");
            words.add("blue");
//下一行代码加入重复的字符串，要求去重
            words.add("blue");

            circles.add(new Circle(3));
            circles.add(new Circle(2.9));
            circles.add(new Circle(5.9));
//下一行代码加入半径相同的圆，要求去重
            circles.add(new Circle(3));


            ArrayList<Integer>  newList = removeDuplicates(list);
            ArrayList<String>   newwords = removeDuplicates(words);
            ArrayList<Circle>  newcircles = removeDuplicates(circles);

//观察下面三行代码打印结果，是否去掉了ArrayList中的重复项
            System.out.println(newList);
            System.out.println(newwords);
            System.out.println(newcircles);

            Integer[] numbers = {1, 2, 3};
            System.out.println(max(numbers));
            String[]   s =  {"red", "green", "blue"};
            System.out.println(max(s));
            Circle[]   c = {new Circle(3), new Circle(2.9), new Circle(5.9)};
            System.out.println(max(c));
    }

    private static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        ArrayList<E> returnList = new ArrayList<>();

        for (E e: list) {
            if (!returnList.contains(e)) {
                returnList.add(e);
            }
        }

        return returnList;
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        if (list == null) return null;
        E max = list[0];

        for(E e: list) {
            int comp = e.compareTo(max);
            if (comp > 0) {
                max = e;
            }
        }
        return max;
    }


}
