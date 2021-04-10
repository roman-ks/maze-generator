package com.roman_ks.maze.generator.utils;

import com.roman_ks.maze.generator.model.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
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

    public static Predicate<Node> hasNotVisitedNeighbor() {
        return node -> node.getNeighbors()
                .stream()
                .anyMatch(GraphUtils.isConnected().negate());
    }

    public static Predicate<Node> isConnected() {
        return node -> !node.getConnected().isEmpty();
    }

}
