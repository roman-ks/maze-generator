package com.roman_ks.maze.generator.utils;

import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Predicate;

public class MazeUtils {

    private MazeUtils() {
    }

    public static int countSolutions(Maze maze) {
        int solutions = 0;
        LinkedList<Node> stack = new LinkedList<Node>();
        HashSet<Node> visited = new HashSet<Node>();

        Predicate<Node> visitedContains = visited::contains;
        Predicate<Node> visitedNotContains = visitedContains.negate();

        Node entrance = maze.getEntrance();
        stack.push(entrance);
        Node exit = maze.getExit();

        while (!stack.isEmpty()) {
            Node node = stack.poll();
            visited.add(node);

            if (node.equals(exit)) {
                solutions++;
                continue;
            }

            node.getConnected().stream()
                    .filter(visitedNotContains)
                    .forEach(stack::push);
        }

        return solutions;
    }

}
