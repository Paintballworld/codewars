package kyu3;

import java.util.stream.IntStream;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        for (int gen = 0; gen < generations; gen++) {
            draw(cells);
            int[][] newGen = enlargeCopyOf(cells);
            for (int y = 0; y < newGen.length; y++) {
                for (int x = 0; x < newGen[0].length; x++) {
                    int sum = 0;
                    for (int dir = 1; dir < 9; dir++) {
                        sum += getByDirFrom(cells, x - 1, y - 1, dir);
                    }
                    int cellSmartValue = getBy(cells, x - 1, y - 1);
                    if (cellSmartValue == 1){
                        if (sum < 2 || sum > 3)
                            newGen[y][x] = 0;
                    }
                    if (cellSmartValue == 0 && sum == 3)
                        newGen[y][x] = 1;
                }
            }
            cells = shrink(newGen);
        }
        return cells;
    }

    private static int[][] enlargeCopyOf(int[][] cells) {
        int[][] result = new int[cells.length + 2][cells[0].length + 2];
        for (int y = 0; y < cells.length ; y++) {
            for (int x = 0; x < cells[0].length ; x++) {
                int debug = cells[y][x];
                int debug2 = result[y + 1][x + 1];
                result[y + 1][x + 1] = cells[y][x];
            }
        }
        return result;
    }

    private static int[][] shrink(int[][] newGen) {
        int negYBorder = 0;
        int posYBorder = 0;
        int leftXBorder = 0;
        int rightXBorder = 0;
        for (int i = 0; i < newGen.length; i++) {
            negYBorder = i;
            int sum = IntStream.of(newGen[i]).sum();
            if (sum > 0) break;
        }
        for (int i = newGen.length - 1; i > negYBorder; i--) {
            posYBorder = i;
            int sum = IntStream.of(newGen[i]).sum();
            if (sum > 0) break;
        }
        for (int x = 0; x < newGen[0].length; x++) {
            leftXBorder = x;
            int sum = 0;
            for (int y = 0; y < newGen.length; y++) sum += newGen[y][x];
            if (sum != 0) break;
        }
        for (int x = newGen[0].length - 1; x > leftXBorder; x--) {
            rightXBorder = x;
            int sum = 0;
            for (int y = 0; y < newGen.length; y++) sum += newGen[y][x];
            if (sum != 0) break;
        }

        int[][] result = new int[posYBorder - negYBorder + 1][rightXBorder - leftXBorder + 1];
        for (int y = 0; y < posYBorder - negYBorder + 1; y++) {
            System.arraycopy(newGen[negYBorder + y], leftXBorder, result[y], 0, rightXBorder - leftXBorder + 1);
        }
        return result;
    }

    private static int getByDirFrom(int[][] cells, int x, int y, int dir) {
        int olx = x;
        int oly = y;
        if (dir % 2 == 0 && dir < 7) y--;
        if (dir % 2 != 0 && dir > 1) y++;
        if (dir < 4) x--;
        if (dir > 5) x++;
        return getBy(cells, x, y);
    }

    private static int getBy(int[][] cells, int x, int y) {
        if (x < 0 || x >= cells[0].length) return 0;
        if (y < 0 || y >= cells.length) return 0;
        return cells[y][x];
    }



    private static void draw(int[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == 1)
                    System.out.print('X');
                else
                    System.out.print('o');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][][] gliders = {
                {{1,0,0},
                        {0,1,1},
                        {1,1,0}},
                {{0,1,0},
                        {0,0,1},
                        {1,1,1}}
        };
        int[][] twoGliders = {
                {1,1,1,0,0,0,1,0},
                {1,0,0,0,0,0,0,1},
                {0,1,0,0,0,1,1,1}
        };
//        draw(gliders[0]);
//        int[][] enlarged = enlargeCopyOf(gliders[0]);
//        draw(enlarged);
//        draw(shrink(enlarged));
        draw(getGeneration(twoGliders, 16));
    }
}
