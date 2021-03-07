package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CenterEntranceSelectorTest {

    @ParameterizedTest
    @CsvSource({
            "3,true,1", "3,false,7",
            "4,true,2", "4,false,13",
            "5,true,2", "5,false,22"}
    )
    void selectEntrance(int size, boolean top, int expected) {
        var selector = new CenterEntranceSelector(size, size, top);
        var nodeList = createList(size * size);

        var selected = selector.selectEntrance(nodeList);

        assertEquals(expected, nodeList.indexOf(selected));
    }

    @ParameterizedTest
    @CsvSource({"true,1", "false,7"})
    void selectEntrance_4x4(boolean top, int expected) {
        var selector = new CenterEntranceSelector(4, 4, top);
        var nodeList = createList(4 * 4);

        var selected = selector.selectEntrance(nodeList);

        assertEquals(expected, nodeList.indexOf(selected));
    }

    @ParameterizedTest
    @CsvSource({"true,1", "false,7"})
    void selectEntrance_5x5(boolean top, int expected) {
        var selector = new CenterEntranceSelector(3, 3, top);
        var nodeList = createList(3 * 3);

        var selected = selector.selectEntrance(nodeList);

        assertEquals(expected, nodeList.indexOf(selected));
    }

    private static List<Node> createList(int n) {
        return Stream.iterate(0, i -> i + 1)
                .limit(n)
                .map(i -> new Node(i, 4))
                .collect(Collectors.toList());
    }
}