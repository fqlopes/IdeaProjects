package study.self;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {


        List<Integer> integerList = IntStream.iterate(1, i -> i + 1)
                .limit(15)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        var aux = integerList.stream();
        System.out.println(aux.toList());
        var makingAString = integerList.stream()
                .map(i -> {
                    String text = Integer.toString(i);
                    return text;
                })
//                .collect(Collectors.joining(", "));
        .collect(
                () -> new StringBuilder(),
                (sb, str) -> sb.append(str).append(" "),
                (sb1, sb2) -> sb1.append(sb2))
                .toString();

        System.out.println(makingAString);

    }
}
