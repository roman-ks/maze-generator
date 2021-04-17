package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.roman_ks.maze.generator.utils.TestUtils.createList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CenterEntranceSelectorTest {

    @ParameterizedTest
    @CsvSource({
            "3,true,1", "3,false,7",
            "4,true,2", "4,false,13",
            "5,true,2", "5,false,22"}
    )
    void selectEntrance(int size, boolean top, int expected) {
        CenterEntranceSelector selector = new CenterEntranceSelector(size, size, top);
        List<Node> nodeList = createList(size * size);

        Node selected = selector.selectEntrance(nodeList);

        assertEquals(expected, nodeList.indexOf(selected));
    }

}