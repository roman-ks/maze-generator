package com.roman_ks.maze.generator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private int number;
    private final List<Node> connected;
    private final List<Node> neighbors;
    private boolean visited;

    /**
     * Create node
     *
     * @param number   unique node number within the graph
     * @param maxEdges maximum number of edges
     */
    public Node(int number, int maxEdges) {
        this.number = number;
        connected = new ArrayList<>(maxEdges);
        neighbors = new ArrayList<>(maxEdges);
    }

    public int getNumber() {
        return number;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addEdge(Node node) {
        if (!neighbors.contains(node)) {
            throw new IllegalArgumentException("Node " + node + " is not a neighbor of the node " + this);
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
    public String toString() {
        var arrayStringCollector = Collectors.joining(", ", "[ ", "]");
        var neighborsStr = neighbors.stream()
                .map(Node::getNumber)
                .map(String::valueOf)
                .collect(arrayStringCollector);

        var connectedStr = connected.stream()
                .map(Node::getNumber)
                .map(String::valueOf)
                .collect(arrayStringCollector);

        return "Node{" +
                "number=" + number +
                ", visited=" + visited +
                ", neighbors=" + neighborsStr +
                ", connected=" + connectedStr +
                "}";
    }
}
