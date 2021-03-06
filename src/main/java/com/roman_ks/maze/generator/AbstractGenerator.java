package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.selector.NodeSelector;

public abstract class AbstractGenerator implements Generator{

    protected NodeSelector nodeSelector;

    @Override
    public void setNodeSelector(NodeSelector selector) {
        nodeSelector = selector;
    }

}
