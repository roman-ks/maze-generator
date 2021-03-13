package com.roman_ks.maze.generator.builder;

import com.roman_ks.maze.generator.AbstractGenerator;
import com.roman_ks.maze.generator.adjacency.AdjacencyMatrixGenerator;
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
    AdjacencyMatrixGenerator matrixGenerator;
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
        builder = new GeneratorBuilder(generatorSupplier);
    }

    @Test
    void noGenerator_exception() {
        builder = new GeneratorBuilder(() -> null);

        var exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Generator type has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorSetNoMatrixGen_exception() {
        var exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Adjacency matrix generator has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenSetNoMazeSupplier_exception() {
        builder.withAdjMatrixGenerator(matrixGenerator);

        var exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Maze supplier has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierSetNoNodeSelector_exception() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier);

        var exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Node selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierNodeSelectorSetNoEnter_exception() {
        builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier)
                .withNodeSelector(nodeSelector);

        var exception = assertThrows(
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

        var exception = assertThrows(
                NullPointerException.class,
                () -> builder.build());
        assertEquals(
                "Exit selector has to be set!",
                exception.getMessage());
    }

    @Test
    void generatorMatrixGenMazeSupplierNodeSelectorEnterExitSet() {
        var generator = builder.withAdjMatrixGenerator(matrixGenerator)
                .withMazeSupplier(mazeSupplier)
                .withNodeSelector(nodeSelector)
                .withEntranceSelector(entranceSelector)
                .withExitSelector(exitSelector)
                .build();
    }

}