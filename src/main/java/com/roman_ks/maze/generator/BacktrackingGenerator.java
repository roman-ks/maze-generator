package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BacktrackingGenerator extends AbstractGenerator {

    @Override
    public Maze generateMaze() {
        LinkedList<Node> stack = new LinkedList<Node>();

        Maze maze = createMazeTemplate();
        Node entrance = maze.getEntrance();

        stack.push(entrance);

        while (!stack.isEmpty()) {
            Node node = stack.peek();

            List<Node> neighbours = getNotConnectedNeighbours(node);
            if (neighbours.isEmpty()) {
                stack.poll();
                continue;
            }

            Node nextToConnect = nodeSelector.selectNode(neighbours);
            node.addEdge(nextToConnect);
            stack.push(nextToConnect);
        }

        return maze;
    }

    private List<Node> getNotConnectedNeighbours(Node node) {
        return node.getNeighbors().stream()
                .filter(GraphUtils.isConnected().negate())
                .collect(Collectors.toList());
    }
}
