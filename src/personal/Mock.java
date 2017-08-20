package personal;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by me on 19.08.17.
 */
public class Mock {

    static String encode(String word){
        LinkedHashMap<String, AtomicInteger> countMap = new LinkedHashMap<>();
        Arrays.stream(word.toUpperCase().split(""))
                .forEach(s -> countMap.computeIfAbsent(s, v -> new AtomicInteger()).incrementAndGet());
        return Arrays.stream(word.toUpperCase().split(""))
                .map(s -> countMap.get(s).get() < 2 ? "(" : ")")
                .collect(Collectors.joining());
    }

    static String encode2(String word) {
        return Arrays.stream(word.toLowerCase().split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.mapping(Function.identity(), Collectors.counting())
                ))
                .entrySet()
                .stream()
                .map(s -> s.getValue().intValue() < 2 ? "(" : ")")
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        System.out.println(encode("Prespecialized"));
        System.out.println(encode2("Prespecialized"));
        System.out.println(")()())()(()()(".equals(encode2("Prespecialized"))) ;
    }
}
