package com.roman_ks.maze.generator.builder.director;

import com.roman_ks.maze.generator.Generator;
import com.roman_ks.maze.generator.NaiveGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.visitor.ToStringVisitor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RectGeneratorDirectorTest {

    @Disabled
    @Test
    void centersAndRandomNodeSelector() {
        GeneratorBuilder builder = new GeneratorBuilder(NaiveGenerator::new);
        RectGeneratorDirector director = new RectGeneratorDirector(builder);

        Generator generator = director.centersAndRandomNodeSelector(3, 3, 1L);

        ToStringVisitor visitor = new ToStringVisitor();

        Maze maze = generator.generateMaze();
        visitor.visitRectMaze((RectMaze) maze);

        System.out.println(visitor.getScreenView());
    }

    @Disabled
    @Test
    void edgesAndRandomNodeSelector() {
        GeneratorBuilder builder = new GeneratorBuilder(NaiveGenerator::new);
        RectGeneratorDirector director = new RectGeneratorDirector(builder);

        Generator generator = director.edgesAndRandomNodeSelector(3, 3, 1L);

        ToStringVisitor visitor = new ToStringVisitor();

        Maze maze = generator.generateMaze();
        visitor.visitRectMaze((RectMaze) maze);

        System.out.println(visitor.getScreenView());
    }
}