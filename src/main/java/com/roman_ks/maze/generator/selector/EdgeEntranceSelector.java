package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;

import java.util.List;

public class EdgeEntranceSelector implements EntranceSelector {

    private boolean first;

    public EdgeEntranceSelector(boolean first) {
        this.first = first;
    }

    @Override
    public Node selectEntrance(List<Node> nodeList) {
        int index = nodeList.size() - 1;
        if (first) {
            index = 0;
        }
        return nodeList.get(index);
    }
}
