package com.roman_ks.maze.generator.printer;

import com.roman_ks.maze.generator.Visitor;
import com.roman_ks.maze.generator.model.RectMaze;

public class ConsolePrinter implements Visitor {

    private char[][] screen;
    private int screenHeight;
    private int screenWidth;

    @Override
    public void visitRectMaze(RectMaze maze) {
        createScreen(maze.getWight(), maze.getHeight());

        var nodeList = maze.getNodeList();
        // todo modify screen based on maze


        printScreen();
    }

    private void printScreen() {
        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }
    }

    private void createScreen(int wight, int height) {
        screenHeight = height * 2 + 1;
        screenWidth = wight * 2 + 1;
        screen = new char[screenHeight][screenWidth];

        drawCellWalls();
    }

    private void drawCellWalls() {
        //draw top lane
        // ┌─┬─┐
        drawLine(0, '┬', '─', '┌', '┐');

        for (int i = 1; i < screenHeight; i++) {
            if (i % 2 != 0) {
                // draw vertical walls
                // | | |
                drawLine(i, '|', ' ', '|', '|');
            } else {
                // draw horizontal lines
                // ├─┼─┤
                drawLine(i, '┼', '─', '├', '┤');
            }
        }

        // draw bottom lane
        // └─┴─┘
        drawLine(0, '┴', '─', '└', '┘');
    }

    private void drawLine(int line, char even, char odd, char first, char last) {
        screen[line][0] = first;
        for (int i = 1; i < screenWidth - 1; i++) {
            // draw lines in each even position
            if (i % 2 == 0) {
                screen[line][i] = even;
            } else {
                screen[line][i] = odd;
            }
        }
        screen[line][screenWidth - 1] = last;
    }

}
