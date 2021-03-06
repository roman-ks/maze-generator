package com.roman_ks.maze.generator.utils;

public class GraphUtils {

    public static int[][] generateAdjacencyMatrixForRectangularMaze(int w, int h) {
        var nodes = w * h;
        int[][] matrix = new int[nodes][nodes];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                var currentPos = w * i + j;
                if (j + 1 < w) {
                    var leftNeighbor = w * i + j + 1;
                    matrix[currentPos][leftNeighbor] = 1;
                    matrix[leftNeighbor][currentPos] = 1;
                }
                if (i + 1 < h) {
                    var bottomNeighbour = w * (i + 1) + j;
                    matrix[currentPos][bottomNeighbour] = 1;
                    matrix[bottomNeighbour][currentPos] = 1;
                }
            }
        }

        return matrix;
    }

}
