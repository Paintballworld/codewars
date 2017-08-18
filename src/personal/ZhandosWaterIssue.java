package personal;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by me on 18.08.17.
 */
public class ZhandosWaterIssue {

    public static int waterVolume(int[] ground) {
        int lBorder = 0;
        int rBorder = 0;
        int subVolume = 0;
        boolean left = true;
        for (int i = 0; i < ground.length; i++) {
            if (left) {
                // если идем вверх - двигаем левый край
                if (ground[i] >= ground[lBorder]) {
                    lBorder = i;
                } else {
                    rBorder = i;
                    left = false; //Пошли вниз - Нашли левую - ищем правую
                }
            }

            if (!left) {
                if (ground[i] < ground[rBorder] &&
                        (ground[rBorder] >= ground[lBorder] || ground[i] == 0)) {
                    // пошли вниз при этом правый край был не ниже левого -
                    // Значит закрыли резервуар, суммируем объем
                    subVolume += getWaterVolume(ground, lBorder, rBorder);
                    left = true;
                    lBorder = i;
                }
                rBorder = i;
            }

            // если дырка - опять ищем левую
            if (ground[i] == 0) {
                left = true;
                lBorder = i;
                rBorder = i;
            }
        }
        return subVolume +
                // Добавляем последний резервуар
                getWaterVolume(ground, lBorder, rBorder);
    }

    private static int getWaterVolume(int[] g, int lBorder, int rBorder) {
        int minBorder = g[lBorder] > g[rBorder] ? g[rBorder] : g[lBorder];
        return ((rBorder - lBorder + 1) *
                (minBorder))
                - IntStream
                .of(Arrays.copyOfRange(g, lBorder, rBorder + 1))
                .map(p -> p > minBorder ? minBorder : p)
                .sum();
    }

    private static List<String> visualizationOf(int[] array) {
        List<String> result = new ArrayList<>();
        int highestLevel = IntStream.of(array).boxed().max(Integer::compareTo).orElse(0);
        while (highestLevel-- > 0) {
            int currentLevel = highestLevel + 1;
            StringBuilder sb = new StringBuilder(String.format("Level: %-2d  |", currentLevel));
            IntStream.of(array).forEach(p -> sb.append(p >= currentLevel ? "X" : " "));
            result.add(sb.append("|\n").toString());
        }
        return result;
    }

    private static int[] getRandomGround(int length, int height) {
        Random rnd = new Random(System.currentTimeMillis());
        return IntStream
                .rangeClosed(0, length)
                .map(p -> rnd.nextInt(height))
                .toArray();
    }

    public static void main(String[] args) {
        int[] pool = getRandomGround(10, 5);
//        pool = new int[]{1, 5, 4, 2, 1, 1, 6, 6, 7, 7};
//        pool = new int[]{3, 2, 0, 3, 4, 0, 0, 1, 4, 2, 2};
//        pool = new int[]{3, 2, 4, 3, 3, 1, 2, 2, 2, 1, 3};
//        pool = new int[]{3, 1, 2, 1, 1, 3, 0, 4, 0, 4, 0};
        pool = new int[]{4, 4, 0, 3, 2, 2, 2, 1, 2, 0, 4};
        visualizationOf(pool).forEach(System.out::print);
        System.out.println(waterVolume(pool));
    }

}
