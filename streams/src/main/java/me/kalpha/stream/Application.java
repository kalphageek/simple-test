package me.kalpha.stream;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        createStringStream();
    }

    private static void createStringStream() {
        System.out.println("\ncreateStringStream()");
        String str = "Eric, Elena, Jhon";
        Stream<String> stringStream =
                Pattern.compile(",").splitAsStream(str).
                map(v -> v.trim());
        stringStream.forEach(System.out::println);
    }

    private static void createIntStream() {
        System.out.println("\ncreateIntStream()");
        IntStream intStream = IntStream.range(1, 11);
        intStream.forEach(System.out::println);
    }

    private static void useFlatMap() {
        System.out.println("\nuseFlatMap()");
        Map<Integer, String > map = new HashMap<>();
        map.put(20, "park");
        map.put(35, "kyung");
        map.put(67, "seok");
        map.put(10, "test man");
        map.put(45, "test woman");

        Stream<String> stream = map.values().stream().
                map(s -> s.split(" ")).
                flatMap(Arrays::stream);
        stream.forEach(System.out::println);
    }

    private static void createByGenerate() {
        System.out.println("\ncreateByGenerate()");
        Random random = new Random(System.currentTimeMillis());
        List<Integer> list = new LinkedList<Integer>();
        for (int i=0; i<10; i++) {
            list.add(random.nextInt(50));
        }
        list.stream().map(n -> n%11)
                .forEach(System.out::println);
    }

    private static void createByArray() {
        System.out.println("\ncreateByArray()");
        String[] arr = new String[]{"a","b","c"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);
    }


}
