package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.selector.RandomNodeSelector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RectGeneratorTest {

    @Disabled
    @Test
    void generateMaze() {
        var generator = new RectGenerator(3, 3);

        generator.setNodeSelector(new RandomNodeSelector(1L));

        generator.generateMaze();

    }
}