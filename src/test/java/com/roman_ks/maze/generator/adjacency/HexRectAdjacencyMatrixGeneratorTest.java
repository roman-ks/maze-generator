package com.roman_ks.maze.generator.adjacency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roman_ks.maze.generator.model.AdjMatrix;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.roman_ks.maze.generator.utils.TestUtils.loadMatrix;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HexRectAdjacencyMatrixGeneratorTest {

    @ParameterizedTest
    @MethodSource("provideAdjacentMatrixArgs")
    void generateAdjacencyMatrixForRectangularMaze_square(int size, int expSize, AdjMatrix expectedMatrix) {
        HexRectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new HexRectAdjacencyMatrixGenerator(size, size);
        AdjMatrix matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(expSize, matrix.size());

        assertEquals(expectedMatrix, matrix);
    }

    private static Stream<Arguments> provideAdjacentMatrixArgs() throws JsonProcessingException {
        return Stream.of(
                arguments(2, 5, loadMatrix("matrices/hex/2x2.json")),
                arguments(3, 10, loadMatrix("matrices/hex/3x3.json")),
                arguments(4, 18, loadMatrix("matrices/hex/4x4.json")));
    }

    @Test
    void generateAdjacencyMatrixForRectangularMaze_notSquare() throws JsonProcessingException {
        int w = 10;
        int h = 5;
        int expSize = 52;
        HexRectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new HexRectAdjacencyMatrixGenerator(w, h);
        AdjMatrix matrix = adjacencyMatrixGenerator.generateAdjMatrix();

        assertEquals(expSize, matrix.size());

        AdjMatrix expMatrix = loadMatrix("matrices/hex/10x5.json");
        assertEquals(expMatrix, matrix);
    }


}