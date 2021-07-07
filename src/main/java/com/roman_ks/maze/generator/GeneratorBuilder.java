package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.AdjMatrixGenerator;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class GeneratorBuilder {

    private final Supplier<AbstractGenerator> generatorSupplier;
    private AdjMatrixGenerator matrixGenerator;
    private Supplier<Maze> mazeSupplier;
    private NodeSelector nodeSelector;
    private EntranceSelector entranceSelector;
    private EntranceSelector exitSelector;


    GeneratorBuilder(Supplier<AbstractGenerator> generatorSupplier) {
        this.generatorSupplier = generatorSupplier;
        setDefaults();
    }

    private void setDefaults(){
        mazeSupplier = Maze::new;
        nodeSelector = new RandomNodeSelector();
    }

    public static GeneratorBuilder naive(){
        return new GeneratorBuilder(NaiveGenerator::new);
    }

    public static GeneratorBuilder backtracking(){
        return new GeneratorBuilder(BacktrackingGenerator::new);
    }

    public GeneratorBuilder withAdjMatrixGenerator(
            AdjMatrixGenerator matrixGenerator) {
        this.matrixGenerator = matrixGenerator;
        return this;
    }

    public GeneratorBuilder withMazeSupplier(Supplier<Maze> mazeSupplier) {
        this.mazeSupplier = requireNonNull(mazeSupplier);
        return this;
    }

    public GeneratorBuilder withNodeSelector(NodeSelector nodeSelector) {
        this.nodeSelector = requireNonNull(nodeSelector);;
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
        requireNonNull(generator,
                "Generator type has to be set!");

        requireNonNull(matrixGenerator,
                "Adjacency matrix generator has to be set!");
        generator.setMatrixGenerator(matrixGenerator);

        generator.setMazeSupplier(mazeSupplier);
        generator.setNodeSelector(nodeSelector);

        requireNonNull(entranceSelector,
                "Entrance selector has to be set!");
        generator.setEntranceSelector(entranceSelector);

        requireNonNull(exitSelector,
                "Exit selector has to be set!");
        generator.setExitSelector(exitSelector);

        return generator;
    }

}
