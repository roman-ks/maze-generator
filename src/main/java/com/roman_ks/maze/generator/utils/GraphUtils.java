package com.roman_ks.maze.generator.utils;

import com.roman_ks.maze.generator.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class GraphUtils {

    /**
     * Create graph from adjacency matrix.
     *
     * @param matrix adjacency matrix
     * @return node list of the generated graph
     */
    public static List<Node> createGraph(int[][] matrix) {
        var nodesCount = matrix.length;

        var edges = maxEdges(matrix);

        // create nodes without edges
        var nodes = new ArrayList<Node>(nodesCount);
        for (int i = 0; i < nodesCount; i++) {
            var node = new Node(i, edges);
            nodes.add(node);
        }

        // add edges from the matrix
        for (int i = 0; i < nodesCount; i++) {
            Node node = nodes.get(i);
            for (int j = 0; j < nodesCount; j++) {
                if (matrix[i][j] == 1) {
                    node.addNeighbor(nodes.get(j));
                }
            }
        }

        return Collections.unmodifiableList(nodes);
    }

    public static int maxEdges(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(Arrays::stream)
                .map(IntStream::sum)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }


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
