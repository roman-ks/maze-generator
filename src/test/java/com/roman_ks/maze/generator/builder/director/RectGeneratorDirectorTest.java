package com.roman_ks.maze.generator.builder.director;

import com.roman_ks.maze.generator.NaiveGenerator;
import com.roman_ks.maze.generator.builder.GeneratorBuilder;
import com.roman_ks.maze.generator.visitor.ToStringVisitor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RectGeneratorDirectorTest {

    @Disabled
    @Test
    void centersAndRandomNodeSelector() {
        var builder = new GeneratorBuilder(NaiveGenerator::new);
        var director = new RectGeneratorDirector(builder);

        var generator = director.centersAndRandomNodeSelector(3, 3, 1L);

        var visitor = new ToStringVisitor();

        var maze = generator.generateMaze();
        maze.acceptVisitor(visitor);

        System.out.println(visitor.getScreenView());
    }

    @Disabled
    @Test
    void edgesAndRandomNodeSelector() {
        var builder = new GeneratorBuilder(NaiveGenerator::new);
        var director = new RectGeneratorDirector(builder);

        var generator = director.edgesAndRandomNodeSelector(3, 3, 1L);

        var visitor = new ToStringVisitor();

        var maze = generator.generateMaze();
        maze.acceptVisitor(visitor);

        System.out.println(visitor.getScreenView());
    }
}