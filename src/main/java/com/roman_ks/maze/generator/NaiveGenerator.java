package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NaiveGenerator extends AbstractGenerator {

    @Override
    public Maze generateMaze() {
        var visitedNodes = new TreeSet<>(Comparator.comparing(Node::getNumber));

        // create connected graph
        var matrix = matrixGenerator.generateAdjMatrix();
        var nodeList = GraphUtils.createGraph(matrix);

        var maze = mazeSupplier.get();
        maze.setNodeList(nodeList);

        // get middle node from the top row
        var entrance = entranceSelector.selectEntrance(nodeList);
        maze.setEntrance(entrance);

        // mark entrance as visited
        entrance.setVisited(true);
        visitedNodes.add(entrance);

        List<Node> possibleToVisit;
        while (!(possibleToVisit = getPossibleToVisit(visitedNodes)).isEmpty()) {

            var nodeToVisit = nodeSelector.selectNode(possibleToVisit);
            connectNextNode(nodeToVisit, visitedNodes);

        }

        maze.setExit(exitSelector.selectEntrance(nodeList));

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
