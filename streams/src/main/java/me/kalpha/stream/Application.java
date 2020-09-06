package me.kalpha.stream;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        useFlatMap();
        createByArray();
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
        for (int i=0; i<100; i++) {
            list.add(random.nextInt(50));
        }
    }

    private static void createByArray() {
        System.out.println("\ncreateByArray()");
        String[] arr = new String[]{"a","b","c"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);
    }


}
