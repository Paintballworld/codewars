package kyu5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClosestWeight {

    static class Node {
        int value;
        int index;
        int weight;
        public static Node of(int value, int weight, int index) {
            Node res = new Node();
            res.value = value;
            res.index = index;
            res.weight = weight;
            return res;
        }
        public int getWeight() {
            return weight;
        }
        public int getIndex() {
            return index;
        }
    }

    public static int[][] closest(String string) {
        if (string == null ||  string.isEmpty())
            return new int[0][0];
        AtomicInteger ai = new AtomicInteger(0);
        List<Node> collect = Stream.of(string.split(" "))
                .map(Integer::valueOf)
                .map(o -> Node.of(o, weight(o + ""), ai.getAndIncrement()))
                .sorted(Comparator.comparing(Node::getWeight).thenComparingInt(Node::getIndex))
                .collect(Collectors.toList());
            System.out.println(collect.stream().map(o -> o.weight + ":" + o.index + ":" + o.value)
                    .collect(Collectors.joining(", ")));
        int index = 0;
        for (int i = 0; i < collect.size() - 1; i++) {
            if (diffIsSmaller(collect, i, index))
                index = i;
        }
        return buildAnAnswerFor(collect, index);
    }

    private static boolean diffIsSmaller(List<Node> collect, int i, int index) {
        int wd1 = Math.abs(collect.get(i + 1).weight - collect.get(i).weight);
        int wd2 = Math.abs(collect.get(index + 1).weight - collect.get(index).weight);
        int id1 = Math.abs(collect.get(i + 1).index);
        int id2 = Math.abs(collect.get(index + 1).index);
        if (wd1 != wd2)
            return wd1 < wd2;
        if (collect.get(i).weight != collect.get(index).weight)
            return collect.get(i).weight < collect.get(index).weight;
        return id1 < id2;
    }

    private static int[][] buildAnAnswerFor(List<Node> collect, int index) {
        Node f = collect.get(index);
        Node s = collect.get(index + 1);
        int[] first = new int[]{f.weight, f.index, f.value};
        int[] second = new int[]{s.weight, s.index, s.value};
        return new int[][]{first, second};
    }

    private static int weight(String aNumber) {
        return Stream.of(aNumber.split("")).mapToInt(Integer::valueOf).sum();
    }







    private static void testing(String actual, String expected) {
        System.out.println("\nActual:" + actual + "\nExpect:" + expected);
        if (!Objects.equals(expected, actual))
            throw new RuntimeException("WRONG!");
            /*System.out.println("What that?!?");
        else
            System.out.println("That's right!");*/
    }

    public void test() {
        System.out.println("Fixed Tests closest");
        String s = "";
        String r = "[]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "456899 50 11992 176 272293 163 389128 96 290193 85 52";
        r = "[[13, 9, 85], [14, 3, 176]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "239382 162 254765 182 485944 134 468751 62 49780 108 54";
        r = "[[8, 5, 134], [8, 7, 62]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "241259 154 155206 194 180502 147 300751 200 406683 37 57";
        r = "[[10, 1, 154], [10, 9, 37]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "89998 187 126159 175 338292 89 39962 145 394230 167 1";
        r = "[[13, 3, 175], [14, 9, 167]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "462835 148 467467 128 183193 139 220167 116 263183 41 52";
        r = "[[13, 1, 148], [13, 5, 139]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);

        s = "403749 18 278325 97 304194 119 58359 165 144403 128 38";
        r = "[[11, 5, 119], [11, 9, 128]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "28706 196 419018 130 49183 124 421208 174 404307 60 24";
        r = "[[6, 9, 60], [6, 10, 24]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "189437 110 263080 175 55764 13 257647 53 486111 27 66";
        r = "[[8, 7, 53], [9, 9, 27]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "79257 160 44641 146 386224 147 313622 117 259947 155 58";
        r = "[[11, 3, 146], [11, 9, 155]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
        s = "315411 165 53195 87 318638 107 416122 121 375312 193 59";
        r = "[[15, 0, 315411], [15, 3, 87]]";
        testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
    }

    public static void main(String[] args) {
        new ClosestWeight().test();
    }
}
