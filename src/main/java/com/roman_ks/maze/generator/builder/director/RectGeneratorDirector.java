package com.roman_ks.maze.generator.builder.director;

import com.roman_ks.maze.generator.Generator;
import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.EdgeEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;

public class RectGeneratorDirector {

    private GeneratorBuilder builder;

    public RectGeneratorDirector(GeneratorBuilder builder) {
        this.builder = builder;
    }

    public Generator centersAndRandomNodeSelector(int width, int height) {
        return builder
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withMazeSupplier(() -> new RectMaze(width, height))
                .withNodeSelector(new RandomNodeSelector())
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();
    }

    public Generator centersAndRandomNodeSelector(int width, int height, long seed) {
        return builder
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withMazeSupplier(() -> new RectMaze(width, height))
                .withNodeSelector(new RandomNodeSelector(seed))
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();
    }

    public Generator edgesAndRandomNodeSelector(int width, int height) {
        return builder
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withMazeSupplier(() -> new RectMaze(width, height))
                .withNodeSelector(new RandomNodeSelector())
                .withEntranceSelector(new EdgeEntranceSelector(true))
                .withExitSelector(new EdgeEntranceSelector(false))
                .build();
    }

    public Generator edgesAndRandomNodeSelector(int width, int height, long seed) {
        return builder
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withMazeSupplier(() -> new RectMaze(width, height))
                .withNodeSelector(new RandomNodeSelector(seed))
                .withEntranceSelector(new EdgeEntranceSelector(true))
                .withExitSelector(new EdgeEntranceSelector(false))
                .build();
    }

}
