package com.roman_ks.maze.generator.model;

import com.roman_ks.maze.generator.visitor.Visitor;

public class RectMaze extends Maze {
    private final int wight;
    private final int height;

    public RectMaze(int wight, int height) {
        this.wight = wight;
        this.height = height;
    }

    public int getWight() {
        return wight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void acceptVisitor(Visitor v) {
        v.visitRectMaze(this);
    }
}
