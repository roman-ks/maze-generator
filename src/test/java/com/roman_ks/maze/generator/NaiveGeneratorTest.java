package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.selector.CenterEntranceSelector;
import com.roman_ks.maze.generator.selector.RandomNodeSelector;
import com.roman_ks.maze.generator.visitor.ToStringVisitor;
import org.junit.jupiter.api.Test;

class NaiveGeneratorTest {

    //    @Disabled
    @Test
    void generateMaze_rect_3x3() {
        int width = 3;
        int height = 3;

        var generator = new NaiveGenerator();
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