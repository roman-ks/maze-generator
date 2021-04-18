package com.roman_ks.maze.generator.utils;

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

}
