package com.roman_ks.maze.generator.adjacency;

/**
 * Generates adjacency matrix for graph like:
 * 0   1   2
 * 3   4   5   6
 * 7   8   9
 * 10  11  12  13
 * In case above width is 3 and height is 4
 */
public class HexRectAdjacencyMatrixGenerator implements AdjacencyMatrixGenerator {

    private final int width;
    private final int height;

    public HexRectAdjacencyMatrixGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int[][] generateAdjMatrix() {
        return doGenerateAdjMatrix(width, height);
    }

    private static int[][] doGenerateAdjMatrix(int w, int h) {
        // all even rows have w+1 nodes, odd rows have w nodes
        int nodes = (w + 1) * (h / 2) + w * (h - h / 2);
        int[][] matrix = new int[nodes][nodes];

        int currPos = 0;
        for (int i = 0; i < h; i++) {
            boolean lastRow = i + 1 == h;
            boolean oddRow = (i % 2) == 0;
            int currW = oddRow ? w : w + 1;
            for (int j = 0; j < currW; j++, currPos++) {
                if (j + 1 < currW) {
                    // connect node on the right
                    markConnection(matrix, currPos, currPos + 1);
                }

                if (!lastRow) {
                    if (j > 0 || oddRow) {
                        // connect node on bottom left
                        markConnection(matrix, currPos, currPos + w);
                    }

                    if (j < currW - 1 || (oddRow && j == currW - 1)) {
                        // connect node on bottom left
                        markConnection(matrix, currPos, currPos + w + 1);
                    }
                }
            }
        }

        return matrix;
    }

    private static void markConnection(int[][] matrix, int pos1, int pos2) {
        matrix[pos1][pos2] = 1;
        matrix[pos2][pos1] = 1;
    }

}
