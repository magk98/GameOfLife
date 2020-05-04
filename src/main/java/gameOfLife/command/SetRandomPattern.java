package gameOfLife.command;

import gameOfLife.model.Grid;

import java.util.Random;

/**
 * Class setting random pattern on the game grid.
 */
public class SetRandomPattern implements SetPattern {
    /**
     * Method responsible for clearing game grid (setting all cells dead).
     * @param grid Game of the life grid.
     */
    @Override
    public void prepareGrid(Grid grid) {
        grid.clearCells();
    }

    /**
     * Method filling game grid with random pattern.
     * @param grid Game of the life grid.
     */
    @Override
    public void fillGrid(Grid grid) {
        Random random = new Random();
        for(int x = 0; x < grid.getWidth(); x++){
            for(int y = 0; y < grid.getHeight(); y++){
                if(random.nextBoolean()) {
                    grid.setCellAlive(x, y, true);
                }
                else {
                    grid.setCellAlive(x, y, false);
                }
            }
        }
    }
}
