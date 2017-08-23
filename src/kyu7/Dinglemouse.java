package kyu7;

import java.util.stream.Stream;

public class Dinglemouse {

    public static String nameTheShape(final char[][] card) {
        String val = new String(card[3]);
        Long count = Stream.of(val.split(""))
                .map(o -> o)
                .filter(o -> !o.equals(" "))
                .count();
        switch (count.intValue()) {
            case 7 : return "circle";
            case 4 : return "diamond";
            case 13: return "square";
        }
        return "circle";
    }
}
