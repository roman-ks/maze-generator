package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.RectAdjMatrixGenerator;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;
import com.roman_ks.maze.generator.utils.MazeUtils;
import com.roman_ks.maze.generator.visitor.ToStringVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BacktrackingGeneratorTest {

    @Test
    void generateMaze_oneSolution() {
        int width = 20;
        int height = 20;

        Generator generator = GeneratorBuilder.backtracking()
                .withAdjMatrixGenerator(new RectAdjMatrixGenerator(width, height))
                .withNodeSelector(new RandomNodeSelector(1L))
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();

        Maze maze = generator.generateMaze();

        Assertions.assertEquals(1, MazeUtils.countSolutions(maze));
    }

    @Disabled
    @Test
    void generateMaze() {

        int width = 20;
        int height = 8;

        BacktrackingGenerator generator = new BacktrackingGenerator();
        generator.setMatrixGenerator(new RectAdjMatrixGenerator(width, height));
        generator.setMazeSupplier(() -> new RectMaze(width, height));
        generator.setNodeSelector(new RandomNodeSelector(1L));

        CenterEntranceSelector entranceSelector = new CenterEntranceSelector(width, height, true);
        CenterEntranceSelector exitSelector = new CenterEntranceSelector(width, height, false);
        generator.setEntranceSelector(entranceSelector);
        generator.setExitSelector(exitSelector);

        ToStringVisitor visitor = new ToStringVisitor();

        Maze maze = generator.generateMaze();
        visitor.visitRectMaze((RectMaze) maze);

        System.out.println(visitor.getScreenView());

    }
}