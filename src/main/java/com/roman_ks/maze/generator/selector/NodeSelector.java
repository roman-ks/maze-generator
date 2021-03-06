package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;

import java.util.List;

public interface NodeSelector {

    Node selectNode(List<Node> nodes);

}
