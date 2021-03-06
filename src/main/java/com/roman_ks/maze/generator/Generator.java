package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.selector.NodeSelector;

public interface Generator {

    void setNodeSelector(NodeSelector selector);

    Node generateMaze();

}
