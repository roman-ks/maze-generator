package com.roman_ks.maze.generator.printer;

import com.roman_ks.maze.generator.model.RectMaze;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ConsolePrinterTest {

    @Disabled
    @Test
    void visitRectMaze() {

        var printer = new ConsolePrinter();

        printer.visitRectMaze(new RectMaze(3, 3));

    }
}