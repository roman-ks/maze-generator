package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.AdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;

import java.util.Objects;
import java.util.function.Supplier;

public class GeneratorBuilder {

    private Supplier<AbstractGenerator> generatorSupplier;
    private AdjacencyMatrixGenerator matrixGenerator;
    private Supplier<Maze> mazeSupplier;
    private NodeSelector nodeSelector;
    private EntranceSelector entranceSelector;
    private EntranceSelector exitSelector;


    GeneratorBuilder(Supplier<AbstractGenerator> generatorSupplier) {
        this.generatorSupplier = generatorSupplier;
    }

    public static GeneratorBuilder naive(){
        return new GeneratorBuilder(NaiveGenerator::new);
    }

    public static GeneratorBuilder backtracking(){
        return new GeneratorBuilder(BacktrackingGenerator::new);
    }

    public GeneratorBuilder withAdjMatrixGenerator(
            AdjacencyMatrixGenerator matrixGenerator) {
        this.matrixGenerator = matrixGenerator;
        return this;
    }

    public GeneratorBuilder withMazeSupplier(Supplier<Maze> mazeSupplier) {
        this.mazeSupplier = mazeSupplier;
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
        AbstractGenerator generator = generatorSupplier.get();
        Objects.requireNonNull(generator,
                "Generator type has to be set!");

        Objects.requireNonNull(matrixGenerator,
                "Adjacency matrix generator has to be set!");
        generator.setMatrixGenerator(matrixGenerator);

        if(mazeSupplier == null)
            generator.setMazeSupplier(Maze::new);
        else
            generator.setMazeSupplier(mazeSupplier);

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
