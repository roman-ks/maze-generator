package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneratorBuilderTest {

    GeneratorBuilder builder;

    @BeforeEach
    void createBuilder() {
        builder = GeneratorBuilder.builder();
    }

    @Test
    void noGenerator_exception() {
        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertEquals(
                "Generator type has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorSetNoMatrixGen_exception() {
        builder.naiveGenerator();

        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertEquals(
                "Adjacency matrix generator has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenSetNoNodeSelector_exception() {
        builder.naiveGenerator();
        builder.withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(1, 1));

        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertEquals(
                "Node selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenNodeSelectorSetNoEnter_exception() {
        builder.naiveGenerator();
        builder.withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(1, 1));
        builder.withNodeSelector(new RandomNodeSelector());

        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertEquals(
                "Entrance selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenNodeSelectorSetEnterNoExit_exception() {
        builder.naiveGenerator();
        builder.withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(1, 1));
        builder.withNodeSelector(new RandomNodeSelector());
        builder.withEntranceSelector(new CenterEntranceSelector(1, 1, true));

        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertEquals(
                "Exit selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenNodeSelectorSetEnterExit() {
        builder.naiveGenerator();
        builder.withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(1, 1));
        builder.withNodeSelector(new RandomNodeSelector());
        builder.withEntranceSelector(new CenterEntranceSelector(1, 1, true));
        builder.withExitSelector(new CenterEntranceSelector(1, 1, true));

        builder.build();
    }

}