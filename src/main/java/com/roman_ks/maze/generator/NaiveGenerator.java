package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.*;
import java.util.stream.Collectors;

public class NaiveGenerator extends AbstractGenerator {

    @Override
    public Maze generateMaze() {
        TreeSet<Node> visitedNodes = new TreeSet<>(Comparator.comparing(Node::getNumber));

        Maze maze = createMazeTemplate();
        Node entrance = maze.getEntrance();

        // mark entrance as visited
        visitedNodes.add(entrance);

        List<Node> possibleToVisit;
        while (!(possibleToVisit = getPossibleToVisit(visitedNodes)).isEmpty()) {

            Node nodeToVisit = nodeSelector.selectNode(possibleToVisit);
            connectNextNode(nodeToVisit, visitedNodes);

        }

        return maze;
    }

    private List<Node> getPossibleToVisit(Collection<Node> visited) {
        // find nodes that have neighbor that hasn't been visited
        return visited.stream()
                .filter(GraphUtils.hasNotVisitedNeighbor())
                .collect(Collectors.toList());

    }

    private void connectNextNode(Node node, Set<Node> visited) {
        List<Node> unvisitedConnectedNodes = node.getNeighbors()
                .stream()
                .filter(GraphUtils.isConnected().negate())
                .collect(Collectors.toList());

        Node nodeToConnect = nodeSelector.selectNode(unvisitedConnectedNodes);
        node.addEdge(nodeToConnect);

        // set visited
        visited.add(nodeToConnect);

        // check if all neighbors are visited
        boolean allNeighborsVisited = node.getNeighbors().stream()
                .allMatch(GraphUtils.isConnected());
        if (allNeighborsVisited) {
            // make sure node won't be considered to be be visited again
            visited.remove(node);
        }
    }
}
