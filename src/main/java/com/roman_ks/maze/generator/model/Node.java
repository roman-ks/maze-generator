package com.roman_ks.maze.generator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private int number;
    private final List<Node> connected;
    private final List<Node> neighbors;

    /**
     * Create node
     * @param number unique node number within the graph
     * @param maxEdges maximum number of edges
     */
    public Node(int number,int maxEdges) {
        this.number = number;
        connected = new ArrayList<>(maxEdges);
        neighbors = new ArrayList<>(maxEdges);
    }

    public int getNumber() {
        return number;
    }

    public void addNeighbor(Node node){
        neighbors.add(node);
    }

    public void addEdge(Node node) {
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
        var neighbors = connected.stream()
                .map(Node::getNumber)
                .map(String::valueOf)
                .collect(Collectors.toList());

        return "Node{" +
                "number=" + number +
                ", neighbors=" + neighbors.toString() +
                "}";
    }
}
