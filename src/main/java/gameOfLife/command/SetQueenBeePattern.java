package gameOfLife.command;

import gameOfLife.model.Grid;

import java.util.Random;

/**
 * Class setting The Queen Bee Shuttle pattern on the game grid.
 */
public class SetQueenBeePattern implements SetPattern{
    private int queenHeight = 7, queenWidth = 4;

    /**
     * Method responsible for clearing game grid (setting all cells dead) and checking if its size is not too small.
     * @param grid Game of the life grid.
     */
    @Override
    public void prepareGrid(Grid grid) {
        if(grid.getHeight() < queenHeight || grid.getWidth() < queenWidth) {
            throw new IllegalArgumentException("Grid is too small to make The Queen Bee Shuttle Pattern. Minimal size is 8x5");
        }
        grid.clearCells();
    }

    /**
     * Method filling game grid with The Queen Bee Shuttle pattern.
     * @param grid Game of the life grid.
     */
    @Override
    public void fillGrid(Grid grid) {
        Random random = new Random();
        int offset = random.nextInt(grid.getHeight() - queenHeight + 1);
        int[] patternCoords = new int[]{0, 0, 1, 0, 2, 1, 3, 2, 3, 3};
        for(int i = 0; i < patternCoords.length; i += 2) {
            grid.setCellAlive(patternCoords[i] + offset, patternCoords[i + 1] + offset, true);
            grid.setCellAlive(patternCoords[i] + offset, queenHeight - 1 - patternCoords[i + 1] + offset, true);
        }
    }
}
