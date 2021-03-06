package com.roman_ks.maze.generator.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestUtils {

    public static String readResource(String path) {
        var resourceUrl = TestUtils.class.getClassLoader().getResource(path);

        try {
            return Files.readString(Path.of(resourceUrl.toURI()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

}
