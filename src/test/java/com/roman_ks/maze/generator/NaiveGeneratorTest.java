package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.builder.director.RectGeneratorDirector;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;
import com.roman_ks.maze.generator.utils.MazeUtils;
import com.roman_ks.maze.generator.visitor.ToStringVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class NaiveGeneratorTest {

    @Test
    void generateMaze_oneSolution() {
        int width = 20;
        int height = 20;

        GeneratorBuilder builder = new GeneratorBuilder(NaiveGenerator::new);
        RectGeneratorDirector director = new RectGeneratorDirector(builder);
        Generator generator = director.edgesAndRandomNodeSelector(width, height, 1L);

        Maze maze = generator.generateMaze();

        Assertions.assertEquals(1, MazeUtils.countSolutions(maze));
    }

    @Disabled
    @Test
    void generateMaze_rect_3x3() {
        int width = 3;
        int height = 3;

        NaiveGenerator generator = new NaiveGenerator();
        generator.setMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height));
        generator.setMazeSupplier(() -> new RectMaze(width, height));
        generator.setNodeSelector(new RandomNodeSelector(1L));

        CenterEntranceSelector entranceSelector = new CenterEntranceSelector(width, height, true);
        CenterEntranceSelector exitSelector = new CenterEntranceSelector(width, height, false);
        generator.setEntranceSelector(entranceSelector);
        generator.setExitSelector(exitSelector);

        ToStringVisitor visitor = new ToStringVisitor();

        Maze maze = generator.generateMaze();
        maze.acceptVisitor(visitor);

        System.out.println(visitor.getScreenView());
    }
}