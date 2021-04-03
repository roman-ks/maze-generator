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
        var stack = new LinkedList<Node>();
        var visited = new HashSet<Node>();

        var entrance = maze.getEntrance();
        stack.push(entrance);
        var exit = maze.getExit();

        while (!stack.isEmpty()) {
            var node = stack.poll();
            visited.add(node);

            if (node.equals(exit)) {
                solutions++;
                continue;
            }

            node.getConnected().stream()
                    .filter(Predicate.not(visited::contains))
                    .forEach(stack::push);
        }

        return solutions;
    }
}
