package com.roman_ks.maze.generator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman_ks.maze.generator.model.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphUtilsTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static int[][] adjacencyMatrix2x2;
    private static int[][] adjacencyMatrix3x3;
    private static int[][] adjacencyMatrix4x4;

    @BeforeAll
    public static void loadMatrices() throws JsonProcessingException {
        adjacencyMatrix2x2 = loadMatrix("matrices/2x2.json");
        adjacencyMatrix3x3 = loadMatrix("matrices/3x3.json");
        adjacencyMatrix4x4 = loadMatrix("matrices/4x4.json");
    }

    private static int[][] loadMatrix(String path) throws JsonProcessingException {
        String json = TestUtils.readResource(path);
        return mapper.readValue(json, int[][].class);
    }

    @Test
    void createGraph_2x2() {
        List<Node> graph = GraphUtils.createGraph(adjacencyMatrix2x2);

        // 0 1
        // 2 3
        for (int i = 0; i < graph.size(); i++) {
            assertEquals(i, graph.get(i).getNumber());
            assertEquals(2, graph.get(i).getNeighbors().size());
        }
    }

    @Test
    void createGraph_3x3() {
        List<Node> graph = GraphUtils.createGraph(adjacencyMatrix3x3);

        for (int i = 0; i < graph.size(); i++) {
            assertEquals(i, graph.get(i).getNumber());
        }

        // 0 1 2
        // 3 4 5
        // 6 7 8
        assertNodesHaveNeighbors(graph, 2, List.of(0, 2, 6, 8));
        assertNodesHaveNeighbors(graph, 3, List.of(1, 3, 5, 7));
        assertNodesHaveNeighbors(graph, 4, List.of(4));
    }

    @Test
    void createGraph_4x4() {
        List<Node> graph = GraphUtils.createGraph(adjacencyMatrix4x4);

        for (int i = 0; i < graph.size(); i++) {
            assertEquals(i, graph.get(i).getNumber());
        }

        // 0  1  2  3
        // 4  5  6  7
        // 8  9  10 11
        // 12 13 14 15
        assertNodesHaveNeighbors(graph, 2, List.of(0, 3, 12, 15));
        assertNodesHaveNeighbors(graph, 3, List.of(1, 2, 4, 7, 8, 11, 13, 14));
        assertNodesHaveNeighbors(graph, 4, List.of(5, 6, 9, 10));
    }

    private void assertNodesHaveNeighbors(List<Node> graph, int connections, List<Integer> nodes) {
        for (int i : nodes) {
            assertEquals(connections, graph.get(i).getNeighbors().size(),
                    "Node " + i + " expected to have " + connections + " connection(s)");
        }
    }

}