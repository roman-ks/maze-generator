package com.roman_ks.maze.generator.builder;

import com.roman_ks.maze.generator.AbstractGenerator;
import com.roman_ks.maze.generator.Generator;
import com.roman_ks.maze.generator.adjacency.AdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;

import java.util.Objects;
import java.util.function.Supplier;

public class GeneratorBuilder {

    private Supplier<AbstractGenerator> generatorSupplier;
    private AdjacencyMatrixGenerator matrixGenerator;
    private NodeSelector nodeSelector;
    private EntranceSelector entranceSelector;
    private EntranceSelector exitSelector;


    public GeneratorBuilder(Supplier<AbstractGenerator> generatorSupplier) {
        this.generatorSupplier = generatorSupplier;
    }

    public GeneratorBuilder withAdjMatrixGenerator(
            AdjacencyMatrixGenerator matrixGenerator) {
        this.matrixGenerator = matrixGenerator;
        return this;
    }

    public GeneratorBuilder withNodeSelector(NodeSelector nodeSelector) {
        this.nodeSelector = nodeSelector;
        return this;
    }

    public GeneratorBuilder withEntranceSelector(EntranceSelector entranceSelector) {
        this.entranceSelector = entranceSelector;
        return this;
    }

    public GeneratorBuilder withExitSelector(EntranceSelector exitSelector) {
        this.exitSelector = exitSelector;
        return this;
    }

    public Generator build() {
        var generator = generatorSupplier.get();
        Objects.requireNonNull(generator,
                "Generator type has to be set!");

        Objects.requireNonNull(matrixGenerator,
                "Adjacency matrix generator has to be set!");
        generator.setMatrixGenerator(matrixGenerator);

        Objects.requireNonNull(nodeSelector,
                "Node selector has to be set!");
        generator.setNodeSelector(nodeSelector);

        Objects.requireNonNull(entranceSelector,
                "Entrance selector has to be set!");
        generator.setEntranceSelector(entranceSelector);

        Objects.requireNonNull(exitSelector,
                "Exit selector has to be set!");
        generator.setExitSelector(exitSelector);

        return generator;
    }

}
