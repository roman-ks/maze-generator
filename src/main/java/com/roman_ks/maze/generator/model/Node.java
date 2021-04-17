package com.roman_ks.maze.generator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Node {

    private final int number;
    private final List<Node> connected;
    private final List<Node> neighbors;

    /**
     * Create node
     *
     * @param number unique node number within the graph
     */
    public Node(int number) {
        this.number = number;
        connected = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    public void addEdge(Node node) {
        if (!neighbors.contains(node)) {
            throw new IllegalArgumentException(
                    "Node " + node + " is not a neighbor of the node " + this);
        }
        connected.add(node);
        node.connected.add(this);
    }

    public List<Node> getNeighbors() {
        return Collections.unmodifiableList(neighbors);
    }

    public List<Node> getConnected() {
        return Collections.unmodifiableList(connected);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        Collector<CharSequence, ?, String> arrayStringCollector = Collectors.joining(", ", "[ ", "]");
        String neighborsStr = neighbors.stream()
                .map(Node::getNumber)
                .map(String::valueOf)
                .collect(arrayStringCollector);

        String connectedStr = connected.stream()
                .map(Node::getNumber)
                .map(String::valueOf)
                .collect(arrayStringCollector);

        return "Node{" +
                "number=" + number +
                ", neighbors=" + neighborsStr +
                ", connected=" + connectedStr +
                "}";
    }
}
