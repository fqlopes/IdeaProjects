package study.self;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Charlie", 3);
        map.put("Alice", 1);
        map.put("Bob", 2);

        List<Map.Entry<String, Integer>> aux =Exercises.mapToList(map);
        aux.forEach(entry -> System.out.println(entry.getClass().getName()));
        var aux2 = map.entrySet();


    }
}
