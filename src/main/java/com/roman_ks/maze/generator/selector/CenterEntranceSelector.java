package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;

import java.util.List;

public class CenterEntranceSelector implements EntranceSelector {

    private final int width;
    private final int height;
    private final boolean selectInTopLine;

    public CenterEntranceSelector(int width, int height, boolean selectInTopLine) {
        this.width = width;
        this.height = height;
        this.selectInTopLine = selectInTopLine;
    }

    @Override
    public Node selectEntrance(List<Node> nodeList) {
        if (selectInTopLine)
            return nodeList.get(width / 2);

        return nodeList.get((height * width - 1) - width / 2);
    }
}
