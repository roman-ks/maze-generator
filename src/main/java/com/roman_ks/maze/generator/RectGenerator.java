package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.model.RectMaze;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RectGenerator extends AbstractGenerator {

    private final int width;
    private final int height;

    public RectGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Maze generateMaze() {
        var visitedNodes = new TreeSet<>(Comparator.comparing(Node::getNumber));

        // create connected graph
        var matrix = GraphUtils.generateAdjacencyMatrixForRectangularMaze(width, height);
        var nodeList = GraphUtils.createGraph(matrix);

        var maze = new RectMaze(width, height);
        maze.setNodeList(nodeList);

        // get middle node from the top row
        var entrance = nodeList.get(width / 2);
        maze.setEntrance(entrance);

        // mark entrance as visited
        entrance.setVisited(true);
        visitedNodes.add(entrance);

        List<Node> possibleToVisit;
        while (!(possibleToVisit = getPossibleToVisit(visitedNodes)).isEmpty()) {

            var nodeToVisit = nodeSelector.selectNode(possibleToVisit);
            connectNextNode(nodeToVisit, visitedNodes);

        }

        //todo set exit
        //maze.setExit();

        return maze;
    }

    private List<Node> getPossibleToVisit(Collection<Node> visited) {
        // find nodes that have neighbor that hasn't been visited
        return visited.stream()
                .filter(hasNotVisitedNeighbor())
                .collect(Collectors.toList());

    }

    private static Predicate<Node> hasNotVisitedNeighbor() {
        return node -> node.getNeighbors()
                .stream()
                .anyMatch(Predicate.not(Node::isVisited));
    }

    private void connectNextNode(Node node, Set<Node> visited) {
        var unvisitedConnectedNodes = node.getNeighbors()
                .stream()
                .filter(Predicate.not(Node::isVisited))
                .collect(Collectors.toList());

        var nodeToConnect = nodeSelector.selectNode(unvisitedConnectedNodes);
        node.addEdge(nodeToConnect);

        // set visited
        nodeToConnect.setVisited(true);
        visited.add(nodeToConnect);

        // check if all neighbors are visited
        var allNeighborsVisited = node.getNeighbors().stream()
                .allMatch(Node::isVisited);
        if (allNeighborsVisited) {
            // make sure node won't be considered to be be visited again
            visited.remove(node);
        }
    }
}
