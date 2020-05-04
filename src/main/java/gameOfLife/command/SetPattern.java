package gameOfLife.command;

import gameOfLife.model.Grid;

/**
 * Setting particular pattern on the game grid.
 */
public interface SetPattern {
    default void set(Grid grid){
        prepareGrid(grid);
        fillGrid(grid);
    }

    /**
     * Method responsible for clearing game grid (setting all cells dead) and checking if its size is not too small.
     * @param grid Game of the life grid.
     */
    void prepareGrid(Grid grid);

    /**
     * Method filling game grid with particular pattern.
     * @param grid Game of the life grid.
     */
    void fillGrid(Grid grid);

}
