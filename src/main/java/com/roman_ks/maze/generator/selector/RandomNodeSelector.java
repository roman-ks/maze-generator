package com.roman_ks.maze.generator.selector;

import com.roman_ks.maze.generator.model.Node;

import java.util.List;
import java.util.Random;

public class RandomNodeSelector implements NodeSelector{

    private final Random random;

    public RandomNodeSelector() {
        random = new Random();
    }

    public RandomNodeSelector(long seed){
        random = new Random(seed);
    }

    @Override
    public Node selectNode(List<Node> nodes) {
        return nodes.get(random.nextInt(nodes.size()));
    }
}
