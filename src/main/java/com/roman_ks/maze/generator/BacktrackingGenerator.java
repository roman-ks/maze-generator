package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BacktrackingGenerator extends AbstractGenerator {

    @Override
    public Maze generateMaze() {
        var stack = new LinkedList<Node>();

        var maze = createMazeTemplate();
        var entrance = maze.getEntrance();

        stack.push(entrance);

        while (!stack.isEmpty()) {
            var node = stack.peek();

            var neighbours = getNotConnectedNeighbours(node);
            if (neighbours.isEmpty()) {
                stack.poll();
                continue;
            }

            var nextToConnect = nodeSelector.selectNode(neighbours);
            node.addEdge(nextToConnect);
            stack.push(nextToConnect);
        }

        return maze;
    }

    private List<Node> getNotConnectedNeighbours(Node node) {
        return node.getNeighbors().stream()
                .filter(Predicate.not(GraphUtils.isConnected()))
                .collect(Collectors.toList());
    }
}
