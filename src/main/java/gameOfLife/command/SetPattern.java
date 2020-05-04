package gameOfLife.command;

import gameOfLife.model.Grid;

/**
 * todo
 */
public interface SetPattern {
    default void set(Grid grid){
        prepareGrid(grid);
        fillGrid(grid);
    }

    void prepareGrid(Grid grid);
    void fillGrid(Grid grid);

}
