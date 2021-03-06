package com.roman_ks.maze.generator.visitor;

import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.utils.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToStringVisitorTest {

    @Test
    void visitRectMaze() {
        var visitor = new ToStringVisitor();
        visitor.visitRectMaze(getMaze());

        var maze = "" +
                "┌─┬─┬─┐\n" +
                "| |   |\n" +
                "├─┼─┼ ┤\n" +
                "|     |\n" +
                "├ ┼─┼─┤\n" +
                "|   | |\n" +
                "└─┴─┴─┘\n";
        maze = maze.replaceAll("\n", System.lineSeparator());

        Assertions.assertEquals(maze, visitor.getScreenView());
    }

    private RectMaze getMaze() {
        var rectMaze = new RectMaze(3, 3);

        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(3, 3);
        var nodeList = GraphUtils.createGraph(matrix);
        rectMaze.setNodeList(nodeList);

        nodeList.get(1).addEdge(nodeList.get(2));
        nodeList.get(2).addEdge(nodeList.get(5));
        nodeList.get(4).addEdge(nodeList.get(5));
        nodeList.get(3).addEdge(nodeList.get(4));
        nodeList.get(3).addEdge(nodeList.get(6));
        nodeList.get(6).addEdge(nodeList.get(7));

        return rectMaze;
    }

}