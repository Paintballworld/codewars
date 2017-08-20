package kyu6;


import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongestConsec {

    private static Comparator<String> sizeComparator = (s, t1) -> -Integer.compare(s.length(), t1.length());

    public static String longestConsec(String[] strarr, int k) {
        String consec = "";
        for (int i = 0; i <= strarr.length - k; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < k ; j++) {
                sb.append(strarr[i + j]);
            }
            if (sb.length() > consec.length())
                consec = sb.toString();
        }
        return consec;

/*
        return Stream.of(strarr)
                .sorted(sizeComparator)
                .distinct()
                .limit(k)
                .collect(Collectors.joining());*/
    }

    private static void testing(String actual, String expected) {
        System.out.println("--------------------\nActual [" + actual + "]\nExpect [" + expected + "]");
        if (!expected.equals(actual)) {
            throw new RuntimeException("Wronf");
        }
    }

    public void test() {
        System.out.println("longestConsec Fixed Tests");
        testing(LongestConsec.longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2), "abigailtheta");
        testing(LongestConsec.longestConsec(new String[] {"ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"}, 1), "oocccffuucccjjjkkkjyyyeehh");
        testing(LongestConsec.longestConsec(new String[] {}, 3), "");
        testing(LongestConsec.longestConsec(new String[] {"itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"}, 2), "wkppqsztdkmvcuwvereiupccauycnjutlvvweqilsfytihvrzlaodfixoyxvyuyvgpck");
        testing(LongestConsec.longestConsec(new String[] {"wlwsasphmxx","owiaxujylentrklctozmymu","wpgozvxxiu"}, 2), "wlwsasphmxxowiaxujylentrklctozmymu");
        testing(LongestConsec.longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas"}, -2), "");
        testing(LongestConsec.longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 3), "ixoyx3452zzzzzzzzzzzz");
        testing(LongestConsec.longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 15), "");
        testing(LongestConsec.longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 0), "");
    }

    public static void main(String[] args) {
        new LongestConsec().test();
    }
}
