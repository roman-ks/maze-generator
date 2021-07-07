package com.roman_ks.maze.generator.adjacency;

import com.roman_ks.maze.generator.model.AdjMatrix;

public class RectAdjMatrixGenerator implements AdjMatrixGenerator {

    private final int width;
    private final int height;

    public RectAdjMatrixGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public AdjMatrix generateAdjMatrix() {
        return generateAdjacencyMatrixForRectangularMaze(width, height);
    }

    private static AdjMatrix generateAdjacencyMatrixForRectangularMaze(int w, int h) {
        int nodes = w * h;

        AdjMatrix matrix = new AdjMatrix(nodes);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                int currentPos = w * i + j;
                if (j + 1 < w) {
                    int leftNeighbor = w * i + j + 1;
                    matrix.markConnection(currentPos, leftNeighbor);
                }
                if (i + 1 < h) {
                    int bottomNeighbour = w * (i + 1) + j;
                    matrix.markConnection(currentPos, bottomNeighbour);
                }
            }
        }

        return matrix;
    }
}
