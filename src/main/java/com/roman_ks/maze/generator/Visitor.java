package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.RectMaze;

public interface Visitor {

    void visitRectMaze(RectMaze maze);

}
