package com.roman_ks.maze.generator.model;

import java.util.Arrays;
import java.util.BitSet;


/**
 * Abstraction for adjacency matrix
 */
public class AdjMatrix {

    private final BitSet[] matrix;

    public AdjMatrix(int nodes) {
        matrix = new BitSet[nodes];
        for (int i = 0; i < nodes; i++) {
            matrix[i] = new BitSet(nodes);
        }
    }

    public void set(int i, int j) {
        set(i, j, true);
    }

    public void set(int i, int j, boolean val) {
        matrix[i].set(j, val);
    }

    /**
     * Get value from this matrix
     *
     * @param i index of row
     * @param j index of column
     * @return value in given row and column
     */
    public boolean get(int i, int j) {
        return matrix[i].get(j);
    }

    /**
     * Return number of nodes in this matrix
     */
    public int size() {
        return matrix.length;
    }

    /**
     * Mark connection between two nodes.
     *
     * @param node1 index of the first node
     * @param node2 index of the second node
     */
    public void markConnection(int node1, int node2) {
        set(node1, node2);
        set(node2, node1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdjMatrix)) return false;
        AdjMatrix adjMatrix = (AdjMatrix) o;
        return Arrays.equals(matrix, adjMatrix.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}
