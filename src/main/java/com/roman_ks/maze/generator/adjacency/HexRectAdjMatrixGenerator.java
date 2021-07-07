package com.roman_ks.maze.generator.adjacency;

import com.roman_ks.maze.generator.model.AdjMatrix;

/**
 * Generates adjacency matrix for graph like:
 *   0   1   2
 * 3   4   5   6
 *   7   8   9
 * 10  11  12  13
 * In case above width is 3 and height is 4
 */
public class HexRectAdjMatrixGenerator implements AdjMatrixGenerator {

    private final int width;
    private final int height;

    public HexRectAdjMatrixGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AdjMatrix generateAdjMatrix() {
        return doGenerateAdjMatrix(width, height);
    }

    private static AdjMatrix doGenerateAdjMatrix(int w, int h) {
        // all even rows have w+1 nodes, odd rows have w nodes
        int nodes = (w + 1) * (h / 2) + w * (h - h / 2);

        AdjMatrix matrix = new AdjMatrix(nodes);

        int currPos = 0;
        for (int i = 0; i < h; i++) {
            boolean lastRow = i + 1 == h;
            boolean oddRow = (i % 2) == 0;
            int currW = oddRow ? w : w + 1;
            for (int j = 0; j < currW; j++, currPos++) {
                if (j + 1 < currW) {
                    // connect node on the right
                    matrix.markConnection(currPos, currPos + 1);
                }

                if (!lastRow) {
                    if (j > 0 || oddRow) {
                        // connect node on bottom left
                        matrix.markConnection(currPos, currPos + w);
                    }

                    if (j < currW - 1 || (oddRow && j == currW - 1)) {
                        // connect node on bottom right
                        matrix.markConnection(currPos, currPos + w + 1);
                    }
                }
            }
        }

        return matrix;
    }

}
