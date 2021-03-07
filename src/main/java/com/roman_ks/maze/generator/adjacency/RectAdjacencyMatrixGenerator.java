package com.roman_ks.maze.generator.adjacency;

import com.roman_ks.maze.generator.utils.GraphUtils;

public class RectAdjacencyMatrixGenerator implements AdjacencyMatrixGenerator {

    private final int width;
    private final int height;

    public RectAdjacencyMatrixGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int[][] generateAdjMatrix() {
        return GraphUtils.generateAdjacencyMatrixForRectangularMaze(width, height);
    }
}
