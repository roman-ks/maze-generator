package com.roman_ks.maze.generator;

import com.roman_ks.maze.generator.adjacency.AdjMatrixGenerator;
import com.roman_ks.maze.generator.model.AdjMatrix;
import com.roman_ks.maze.generator.model.Maze;
import com.roman_ks.maze.generator.model.Node;
import com.roman_ks.maze.generator.selector.EntranceSelector;
import com.roman_ks.maze.generator.selector.NodeSelector;
import com.roman_ks.maze.generator.utils.GraphUtils;

import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractGenerator implements Generator {

    protected NodeSelector nodeSelector;
    protected AdjMatrixGenerator matrixGenerator;
    protected Supplier<Maze> mazeSupplier;
    protected EntranceSelector entranceSelector;
    protected EntranceSelector exitSelector;

    public void setNodeSelector(NodeSelector nodeSelector) {
        this.nodeSelector = nodeSelector;
    }

    public void setMatrixGenerator(AdjMatrixGenerator matrixGenerator) {
        this.matrixGenerator = matrixGenerator;
    }

    public void setMazeSupplier(Supplier<Maze> mazeSupplier) {
        this.mazeSupplier = mazeSupplier;
    }

    public void setEntranceSelector(EntranceSelector entranceSelector) {
        this.entranceSelector = entranceSelector;
    }

    public void setExitSelector(EntranceSelector exitSelector) {
        this.exitSelector = exitSelector;
    }

    /***
     * Create maze and sets entrance and exit
     * @return
     */
    protected Maze createMazeTemplate() {
        Maze maze = mazeSupplier.get();

        AdjMatrix matrix = matrixGenerator.generateAdjMatrix();
        List<Node> nodeList = GraphUtils.createGraph(matrix);
        maze.setNodeList(nodeList);

        maze.setEntrance(entranceSelector.selectEntrance(nodeList));
        maze.setExit(exitSelector.selectEntrance(nodeList));

        return maze;
    }
}
