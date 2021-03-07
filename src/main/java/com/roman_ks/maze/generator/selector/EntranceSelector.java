package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;

import java.util.List;

public interface EntranceSelector {

    Node selectEntrance(List<Node> nodeList);

}
