package kyu5;


import java.util.*;
import java.util.stream.Collectors;

public class SumOfK {

    public static Integer chooseBestSum(int targetSum, int cityCount, List<Integer> ls) {
        System.out.println("cityCount = " + cityCount + ':' + ls.stream().map(t-> t + "").collect(Collectors.joining(", ")));
        Collections.sort(ls, Comparator.naturalOrder());
        LinkedList<Integer> ignoredIds = new LinkedList<>();
        List<Integer> filteredAndSorted = ls.stream().filter(o -> o <= targetSum).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Integer result = recursiveSum(filteredAndSorted, ignoredIds, 0, cityCount, targetSum);
        return result != null ? targetSum - result : null;
    }

    private static Integer recursiveSum(List<Integer> list, LinkedList<Integer> ignoredIndexes, int iteratorPoint, int level, int targetSum) {

        if (list == null || list .isEmpty() || targetSum < 0) return null; // конец списка

        if (level == 0) return targetSum  >= 0 ? targetSum : null; // конец рекурсии

        int minimum = targetSum;
        boolean found = false;
        for (int i = iteratorPoint; i < list.size(); i++) {
            if (ignoredIndexes.contains(i)) continue;
            if (list.get(i) > targetSum) continue;
            ignoredIndexes.addLast(i);
            Integer subResult = recursiveSum(list, ignoredIndexes, i, level - 1, targetSum - list.get(i));
            ignoredIndexes.pollLast();
            if (subResult != null && subResult < minimum) {
                minimum = subResult;
                if (minimum == 0) return 0;
                found = true;
            }
        }
        return found ? minimum : null;
    }



    private void BasicTests1() {
        System.out.println("****** Basic Tests small numbers******");
        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        Integer n = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(163, n);
        ts = new ArrayList<>(Collections.singletonList(50));
        Integer m = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(null, m);
        ts = new ArrayList<>(Arrays.asList(91, 74, 73, 85, 73, 81, 87));
        n = SumOfK.chooseBestSum(230, 3, ts);
        assertEquals(228, n);
        ts = new ArrayList<>(Arrays.asList(34, 44, 50, 56, 56, 64, 68, 73, 76, 89, 89, 100, 123, 123, 132, 144, 2333));
        n = SumOfK.chooseBestSum(880, 8, ts);
        System.out.println("n :" + n);
    }

    private void assertEquals(Integer expected, Integer actual)  {

        System.out.print("\nExpect [" + expected + "]\nActual [" + actual + "]");
        System.out.println((Objects.equals(expected, actual)) ? "--Ok" : "--NO");
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        new SumOfK().BasicTests1();

        System.out.println("t :" + (System.currentTimeMillis() - start));

    }


}
