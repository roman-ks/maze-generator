package com.roman_ks.maze.generator.utils;

import com.roman_ks.maze.generator.model.AdjMatrix;
import com.roman_ks.maze.generator.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class GraphUtils {

    /**
     * Create graph from adjacency matrix.
     *
     * @param matrix adjacency matrix
     * @return node list of the generated graph
     */
    public static List<Node> createGraph(AdjMatrix matrix) {
        int nodesCount = matrix.size();


        // create nodes without edges
        ArrayList<Node> nodes = new ArrayList<Node>(nodesCount);
        for (int i = 0; i < nodesCount; i++) {
            Node node = new Node(i);
            nodes.add(node);
        }

        // add edges from the matrix
        for (int i = 0; i < nodesCount; i++) {
            Node node = nodes.get(i);
            for (int j = 0; j < nodesCount; j++) {
                if (matrix.get(i,j)) {
                    node.addNeighbor(nodes.get(j));
                }
            }
        }

        return Collections.unmodifiableList(nodes);
    }

    public static Predicate<Node> hasNotVisitedNeighbor() {
        return node -> node.getNeighbors()
                .stream()
                .anyMatch(isConnected().negate());
    }

    public static Predicate<Node> isConnected() {
        return node -> !node.getConnected().isEmpty();
    }

}
