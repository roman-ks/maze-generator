package com.roman_ks.maze.generator.visitor;

import com.roman_ks.maze.generator.adjacency.RectAdjacencyMatrixGenerator;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.utils.GraphUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ToStringVisitorTest {

    @Test
    void visitRectMaze_3x3() {
        ToStringVisitor visitor = new ToStringVisitor();
        visitor.visitRectMaze(getMaze3x3());

        String maze = "" +
                "┌─┬ ┬─┐\n" +
                "| |   |\n" +
                "├─┼─┼ ┤\n" +
                "|     |\n" +
                "├ ┼─┼─┤\n" +
                "|   | |\n" +
                "└─┴ ┴─┘\n";
        maze = maze.replaceAll("\n", System.lineSeparator());

        Assertions.assertEquals(maze, visitor.getScreenView());
    }

    private RectMaze getMaze3x3() {
        RectMaze rectMaze = getNodeList(3, 3);
        List<Node> nodeList = rectMaze.getNodeList();

        nodeList.get(1).addEdge(nodeList.get(2));
        nodeList.get(2).addEdge(nodeList.get(5));
        nodeList.get(4).addEdge(nodeList.get(5));
        nodeList.get(3).addEdge(nodeList.get(4));
        nodeList.get(3).addEdge(nodeList.get(6));
        nodeList.get(6).addEdge(nodeList.get(7));

        rectMaze.setEntrance(nodeList.get(1));
        rectMaze.setExit(nodeList.get(7));

        return rectMaze;
    }

    @Test
    void visitRectMaze_4x4() {
        ToStringVisitor visitor = new ToStringVisitor();
        visitor.visitRectMaze(getMaze4x4());

        String maze = "" +
                "┌─┬ ┬─┬─┐\n" +
                "|   | | |\n" +
                "├ ┼─┼─┼─┤\n" +
                "|       |\n" +
                "├─┼─┼─┼ ┤\n" +
                "| |     |\n" +
                "├─┼ ┼─┼─┤\n" +
                "| |   | |\n" +
                "└─┴─┴ ┴─┘\n";
        maze = maze.replaceAll("\n", System.lineSeparator());

        Assertions.assertEquals(maze, visitor.getScreenView());
    }

    private RectMaze getMaze4x4() {
        RectMaze rectMaze = getNodeList(4, 4);
        List<Node> nodeList = rectMaze.getNodeList();

        nodeList.get(1).addEdge(nodeList.get(0));
        nodeList.get(0).addEdge(nodeList.get(4));
        nodeList.get(4).addEdge(nodeList.get(5));
        nodeList.get(5).addEdge(nodeList.get(6));
        nodeList.get(6).addEdge(nodeList.get(7));
        nodeList.get(7).addEdge(nodeList.get(11));
        nodeList.get(11).addEdge(nodeList.get(10));
        nodeList.get(10).addEdge(nodeList.get(9));
        nodeList.get(9).addEdge(nodeList.get(13));
        nodeList.get(13).addEdge(nodeList.get(14));

        rectMaze.setEntrance(nodeList.get(1));
        rectMaze.setExit(nodeList.get(14));

        return rectMaze;
    }

    private RectMaze getNodeList(int w, int h) {
        RectMaze rectMaze = new RectMaze(w, h);

        RectAdjacencyMatrixGenerator adjacencyMatrixGenerator = new RectAdjacencyMatrixGenerator(w, h);
        int[][] matrix = adjacencyMatrixGenerator.generateAdjMatrix();
        List<Node> nodeList = GraphUtils.createGraph(matrix);
        rectMaze.setNodeList(nodeList);

        return rectMaze;
    }

}