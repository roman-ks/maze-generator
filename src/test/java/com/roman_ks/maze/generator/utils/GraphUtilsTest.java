package com.roman_ks.maze.generator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        var json = TestUtils.readResource(path);
        return mapper.readValue(json, int[][].class);
    }

    @ParameterizedTest
    @MethodSource("provideAdjacentMatrixArgs")
    void generateAdjacencyMatrixForRectangularMaze_square(int size, int[][] expectedMatrix) {
        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(size, size);

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

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void generateAdjacencyMatrixForRectangularMaze(int side) {
        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(side, side);

        var mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(matrix));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}