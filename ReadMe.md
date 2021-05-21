# Maze generator lib

Customizable maze generator library based on graphs.

The main concept in this library is Generator. It is designed to be customizable for different maze shapes(rectangular,
hexagonal, etc.)

NaiveGenerator is the only one implementation of a Generator available.

## Maze generation process

First steps are the same for all generator implementation.

1. Create adjacency matrix for desired graph type and size. The graph represented by the matrix will look like this (
   rectangular type, 3x3 size):

   ![rectangular 3x3 graph](graph.png)

2. Create list of nodes, make each node aware of its neighbors according to the matrix from step 1.

3. Select entrance and exit nodes

Next steps differ for each implementation

### Naive generator

4. Add entrance node to the list of `visited nodes`.

5. For each node in the list of `visited nodes` check if it has not connected neighbors. All nodes that satisfy filter
   criteria are placed in `possible to visit` list.

6. If `possible to visit` list contains nodes do steps 7 - 11, else return result.

7. Select node to visit from the `possible to visit` list.

8. Make list of `unvisited neighbors` for the selected node.

9. Select node from `unvisited neighbors` and make connection to it. Add connected neighbor to the `visited nodes` list.

10. Check if node selected on step 6 has not connected neighbors. If no unconnected neighbors left, remove node
    from `visited nodes` list.

11. Go to step 5.

### Backtracking generator

Uses algorithm described
on [Wikipedia](https://en.wikipedia.org/wiki/Maze_generation_algorithm#Iterative_implementation)

## Prerequisites

To start maze generation one has to decide on each of the following things.

* How entrance and exit for the maze will be selected.
* How graph adjacency matrix will be generated.
* How next node on each step will be selected.

## Create and use Generator

There is one convenient way to create Generator object.  
Here's how you use GeneratorBuilder to crete Generator: 

```
var generator = GeneratorBuilder.naive()
                .withAdjMatrixGenerator(new RectAdjacencyMatrixGenerator(width, height))
                .withEntranceSelector(new CenterEntranceSelector(width, height, true))
                .withExitSelector(new CenterEntranceSelector(width, height, false))
                .build();
```

Use Generator by calling `generateMaze()` method on it.

## Goodies

### Write a maze to String

ToStringVisitor can be used to create a String with the maze. It is available in test directory. It relies on box drawing Unicode characters so make sure
output destination supports UTF-8.

Only rectangular mazes supported for now.

Here's how to write a maze in the console.

```
var maze; // assign some value
var visitor = new ToStringVisitor();
visitor.visitRectMaze((RectMaze) maze);
System.out.println(visitor.getScreenView());
```

Output example

```
┌ ┬─┬─┬─┐
|     | |
├ ┼─┼─┼ ┤
|       |
├ ┼─┼─┼─┤
|   |   |
├ ┼─┼ ┼ ┤
|     | |
└─┴─┴─┴ ┘
```

## License

This project is released under [MIT license](LICENSE)