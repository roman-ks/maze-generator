package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.builder.director.RectGeneratorDirector;
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

        var builder = new GeneratorBuilder(BacktrackingGenerator::new);
        var director = new RectGeneratorDirector(builder);
        var generator = director.edgesAndRandomNodeSelector(width, height, 1L);

        var maze = generator.generateMaze();

        Assertions.assertEquals(1, MazeUtils.countSolutions(maze));
    }

    @Disabled
    @Test
    void generateMaze() {

        int width = 20;
        int height = 8;

        var generator = new BacktrackingGenerator();
        generator.setMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height));
        generator.setMazeSupplier(() -> new RectMaze(width, height));
        generator.setNodeSelector(new RandomNodeSelector(1L));

        var entranceSelector = new CenterEntranceSelector(width, height, true);
        var exitSelector = new CenterEntranceSelector(width, height, false);
        generator.setEntranceSelector(entranceSelector);
        generator.setExitSelector(exitSelector);

        var visitor = new ToStringVisitor();

        var maze = generator.generateMaze();
        maze.acceptVisitor(visitor);

        System.out.println(visitor.getScreenView());

    }
}