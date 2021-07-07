package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.AdjMatrixGenerator;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GeneratorBuilderTest {

    @Mock
    AdjMatrixGenerator matrixGenerator;
    @Mock
    Supplier<Maze> mazeSupplier;
    @Mock
    NodeSelector nodeSelector;
    @Mock
    EntranceSelector entranceSelector;
    @Mock
    EntranceSelector exitSelector;
    Supplier<AbstractGenerator> generatorSupplier;

    GeneratorBuilder builder;

    @BeforeEach
    void createBuilder() {
        generatorSupplier = () -> Mockito.mock(AbstractGenerator.class);
        builder = GeneratorBuilder.naive();
    }

    @Test
    void generatorSetNoMatrixGen_exception() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Adjacency matrix generator has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierNodeSelectorSetNoEnter_exception() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier)
                .withNodeSelector(nodeSelector);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Entrance selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierNodeSelectorEnterSetNoExit_exception() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier)
                .withNodeSelector(nodeSelector)
                .withEntranceSelector(entranceSelector);

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Exit selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierNodeSelectorEnterExitSet() {
        Generator generator = builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier)
                .withNodeSelector(nodeSelector)
                .withEntranceSelector(entranceSelector)
                .withExitSelector(exitSelector)
                .build();
    }

    @Test
    void generatorMatrixGenNodeSelectorEnterExitSet() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withNodeSelector(nodeSelector)
                .withEntranceSelector(entranceSelector)
                .withExitSelector(exitSelector)
                .build();
    }

    @Test
    void generatorWithDefaults() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withEntranceSelector(entranceSelector)
                .withExitSelector(exitSelector)
                .build();
    }

}