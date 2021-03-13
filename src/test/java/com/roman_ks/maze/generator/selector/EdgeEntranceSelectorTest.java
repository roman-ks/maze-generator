package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.roman_ks.maze.generator.utils.TestUtils.createList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeEntranceSelectorTest {

    int size = 10;
    List<Node> nodeList = createList(size);

    @Test
    void first_firstSelected() {
        var selector = new EdgeEntranceSelector(true);

        var selected = selector.selectEntrance(nodeList);

        assertEquals(0, nodeList.indexOf(selected));
    }

    @Test
    void notFirst_firstSelected() {
        var selector = new EdgeEntranceSelector(false);

        var selected = selector.selectEntrance(nodeList);

        assertEquals(size - 1, nodeList.indexOf(selected));
    }

}