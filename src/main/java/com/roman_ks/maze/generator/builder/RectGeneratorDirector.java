package com.roman_ks.maze.generator.builder;

import com.roman_ks.maze.generator.Generator;
import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;

public class RectGeneratorDirector {

    public Generator centersAndRandomNodeSelector(int width, int height) {
        return naiveGenerator()
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withNodeSelector(new RandomNodeSelector())
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();

    }

    public Generator centersAndRandomNodeSelector(int width, int height, long seed) {
        return naiveGenerator()
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withNodeSelector(new RandomNodeSelector(seed))
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();

    }

    private GeneratorBuilder naiveGenerator() {
        return GeneratorBuilder.builder()
                .naiveGenerator();
    }

}
