package com.roman_ks.maze.generator.adjacency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roman_ks.maze.generator.model.AdjMatrix;
import com.roman_ks.maze.generator.utils.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.roman_ks.maze.generator.utils.TestUtils.loadMatrix;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RectAdjMatrixGeneratorTest {

    private static AdjMatrix adjacencyMatrix2x2;
    private static AdjMatrix adjacencyMatrix3x3;
    private static AdjMatrix adjacencyMatrix4x4;
    private static AdjMatrix adjacencyMatrix10x5;

    @BeforeAll
    public static void loadMatrices() throws JsonProcessingException {
        adjacencyMatrix2x2 = loadMatrix("matrices/rect/2x2.json");
        adjacencyMatrix3x3 = loadMatrix("matrices/rect/3x3.json");
        adjacencyMatrix4x4 = loadMatrix("matrices/rect/4x4.json");
        adjacencyMatrix10x5 = loadMatrix("matrices/rect/10x5.json");
    }

    @ParameterizedTest
    @MethodSource("provideAdjacentMatrixArgs")
    void generateAdjacencyMatrixForRectangularMaze_square(int size, AdjMatrix expectedMatrix) {
        RectAdjMatrixGenerator adjacencyMatrixGenerator = new RectAdjMatrixGenerator(size, size);
        AdjMatrix matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(size * size, matrix.size());

        assertEquals(expectedMatrix, matrix);
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
        RectAdjMatrixGenerator adjacencyMatrixGenerator = new RectAdjMatrixGenerator(w, h);
        AdjMatrix matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(w * h, matrix.size());

        assertEquals(adjacencyMatrix10x5, matrix);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void generateAdjacencyMatrixForRectangularMaze(int size) {
        RectAdjMatrixGenerator adjacencyMatrixGenerator = new RectAdjMatrixGenerator(size, size);
        AdjMatrix matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        try {
            System.out.println(TestUtils.serializeMatrix(matrix));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}