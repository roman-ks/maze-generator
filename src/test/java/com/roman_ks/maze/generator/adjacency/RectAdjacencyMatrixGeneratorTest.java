package com.roman_ks.maze.generator.adjacency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman_ks.maze.generator.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RectAdjacencyMatrixGeneratorTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static int[][] adjacencyMatrix2x2;
    private static int[][] adjacencyMatrix3x3;
    private static int[][] adjacencyMatrix4x4;
    private static int[][] adjacencyMatrix10x5;

    @BeforeAll
    public static void loadMatrices() throws JsonProcessingException {
        adjacencyMatrix2x2 = loadMatrix("matrices/2x2.json");
        adjacencyMatrix3x3 = loadMatrix("matrices/3x3.json");
        adjacencyMatrix4x4 = loadMatrix("matrices/4x4.json");
        adjacencyMatrix10x5 = loadMatrix("matrices/10x5.json");
    }

    private static int[][] loadMatrix(String path) throws JsonProcessingException {
        String json = TestUtils.readResource(path);
        return mapper.readValue(json, int[][].class);
    }

    @ParameterizedTest
    @MethodSource("provideAdjacentMatrixArgs")
    void generateAdjacencyMatrixForRectangularMaze_square(int size, int[][] expectedMatrix) {
        RectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new RectAdjacencyMatrixGenerator(size, size);
        int[][] matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(size * size, matrix.length);
        assertEquals(size * size, matrix[0].length);

        assertArrayEquals(expectedMatrix, matrix);
    }

    private static Stream<Arguments> provideAdjacentMatrixArgs() {
        return Stream.of(
                arguments(2, adjacencyMatrix2x2),
                arguments(3, adjacencyMatrix3x3),
                arguments(4, adjacencyMatrix4x4));
    }

    @Test
    void generateAdjacencyMatrixForRectangularMaze_notSquare() {
        int w = 10;
        int h = 5;
        RectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new RectAdjacencyMatrixGenerator(w, h);
        int[][] matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(w * h, matrix.length);
        assertEquals(w * h, matrix[0].length);

        assertArrayEquals(adjacencyMatrix10x5, matrix);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void generateAdjacencyMatrixForRectangularMaze(int size) {
        RectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new RectAdjacencyMatrixGenerator(size, size);
        int[][] matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(matrix));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}