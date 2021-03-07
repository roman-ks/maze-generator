package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.AdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;

import java.util.function.Supplier;

public abstract class AbstractGenerator implements Generator {

    protected NodeSelector nodeSelector;
    protected AdjacencyMatrixGenerator matrixGenerator;
    protected Supplier<Maze> mazeSupplier;
    protected EntranceSelector entranceSelector;
    protected EntranceSelector exitSelector;

    public void setNodeSelector(NodeSelector nodeSelector) {
        this.nodeSelector = nodeSelector;
    }

    public void setMatrixGenerator(AdjacencyMatrixGenerator matrixGenerator) {
        this.matrixGenerator = matrixGenerator;
    }

    public void setMazeSupplier(Supplier<Maze> mazeSupplier) {
        this.mazeSupplier = mazeSupplier;
    }

    public void setEntranceSelector(EntranceSelector entranceSelector) {
        this.entranceSelector = entranceSelector;
    }

    public void setExitSelector(EntranceSelector exitSelector) {
        this.exitSelector = exitSelector;
    }
}
