package com.roman_ks.maze.generator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphUtilsTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void generateAdjacencyMatrixForRectangularMaze_size2() throws JsonProcessingException {
        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(2, 2);

        assertEquals(4, matrix.length);
        assertEquals(4, matrix[0].length);

        var expectedJson = "[" +
                "[0,1,1,0]," +
                "[1,0,0,1]," +
                "[1,0,0,1]," +
                "[0,1,1,0]]";
        var expectedMatrix = mapper.readValue(expectedJson, int[][].class);

        assertArrayEquals(expectedMatrix, matrix);
    }

    @Test
    void generateAdjacencyMatrixForRectangularMaze_size3() throws JsonProcessingException {
        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(3, 3);

        assertEquals(9, matrix.length);
        assertEquals(9, matrix[0].length);

        var expectedJson = "[" +
                "[0,1,0,1,0,0,0,0,0]," +
                "[1,0,1,0,1,0,0,0,0]," +
                "[0,1,0,0,0,1,0,0,0]," +
                "[1,0,0,0,1,0,1,0,0]," +
                "[0,1,0,1,0,1,0,1,0]," +
                "[0,0,1,0,1,0,0,0,1]," +
                "[0,0,0,1,0,0,0,1,0]," +
                "[0,0,0,0,1,0,1,0,1]," +
                "[0,0,0,0,0,1,0,1,0]]";
        var expectedMatrix = mapper.readValue(expectedJson, int[][].class);

        assertArrayEquals(expectedMatrix, matrix);
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