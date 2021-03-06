package com.roman_ks.maze.generator.visitor;

import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.model.RectMaze;

import java.util.List;

public class ToStringVisitor implements Visitor {

    private char[][] screen;

    // screen size in "pixels"
    private int screenHeight;
    private int screenWidth;

    // screen size in cells
    private int height;
    private int wight;

    @Override
    public void visitRectMaze(RectMaze maze) {
        height = maze.getHeight();
        wight = maze.getWight();
        createScreen();

        var nodeList = maze.getNodeList();
        makeConnections(nodeList);

        // todo open entrance and exit
    }

    public String getScreenView() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                builder.append(screen[i][j]);
            }
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    private void makeConnections(List<Node> nodeList) {
        for (int i = 0; i < nodeList.size(); i++) {
            final var currNumber = i;
            nodeList.get(i).getConnected().stream()
                    .map(Node::getNumber)
                    .filter(n -> n > currNumber)
                    .forEach(n -> removeWall(currNumber, n));
        }
    }

    /**
     * Remove wall between cells a and b.
     * Cell number count starts from upper left cell.
     *
     * @param a first cell number
     * @param b second cell number, has not be greater then first cell number
     */
    private void removeWall(int a, int b) {
        int wallLocationX, wallLocationY;
        if (b - a == wight) {
            // b cell is below cell a
            wallLocationX = (b % wight) * 2 + 1;
            wallLocationY = (b / wight) * 2;
        } else {
            // b cell is to the left of a cell
            wallLocationX = (b % wight) * 2;
            wallLocationY = (b / wight) * 2 + 1;
        }

        screen[wallLocationY][wallLocationX] = ' ';
    }

    private void createScreen() {
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
        drawLine(screenHeight - 1, '┴', '─', '└', '┘');
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
