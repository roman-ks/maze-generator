package com.roman_ks.maze.generator.adjacency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman_ks.maze.generator.utils.TestUtils;

public final class AdjacencyUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    protected static int[][] loadMatrix(String path) throws JsonProcessingException {
        String json = TestUtils.readResource(path);
        return mapper.readValue(json, int[][].class);
    }

}
