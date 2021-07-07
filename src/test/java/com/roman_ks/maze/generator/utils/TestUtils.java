package com.roman_ks.maze.generator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman_ks.maze.generator.model.AdjMatrix;
import com.roman_ks.maze.generator.model.Node;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String readResource(String path) {
        URL resourceUrl = TestUtils.class.getClassLoader().getResource(path);

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(resourceUrl.toURI()));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List<Node> createList(int n) {
        return Stream.iterate(0, i -> i + 1)
                .limit(n)
                .map(Node::new)
                .collect(Collectors.toList());
    }

    public static AdjMatrix loadMatrix(String path) throws JsonProcessingException {
        String json = TestUtils.readResource(path);
        int[][] ints = mapper.readValue(json, int[][].class);

        AdjMatrix matrix = new AdjMatrix(ints.length);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                matrix.set(i, j, ints[i][j] == 1);
            }
        }
        return matrix;
    }

    public static String serializeMatrix(AdjMatrix matrix) throws JsonProcessingException {
        int[][] ints = new int[matrix.size()][matrix.size()];

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = matrix.get(i, j) ? 1 : 0;
            }
        }

        return mapper.writeValueAsString(ints);
    }

}
