package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @ClassName StreamTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/10/20 10:50
 * @Version 1.0
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> myList = Arrays.asList(
                "a1", "a2", "b1", "c2", "c1"
        );

        myList.stream()
                .filter(s->s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        Stream<List<String>> myList1 = Stream.of(myList);
        myList1.forEach(System.out::println);
    }
}
