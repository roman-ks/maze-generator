package com.roman_ks.maze.generator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private int number;
    private final List<Node> connected;

    public Node(int maxEdges) {
        connected = new ArrayList<>(maxEdges);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addEdge(Node node) {
        connected.add(node);
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
