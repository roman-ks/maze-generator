package com.roman_ks.maze.generator.model;

import java.util.List;

public class Maze {

    private Node entrance;
    private Node exit;
    private List<Node> nodeList;

    public Node getEntrance() {
        return entrance;
    }

    public void setEntrance(Node entrance) {
        this.entrance = entrance;
    }

    public Node getExit() {
        return exit;
    }

    public void setExit(Node exit) {
        this.exit = exit;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

}
