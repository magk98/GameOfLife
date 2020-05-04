package gameOfLife.command;

import gameOfLife.model.Grid;

import java.util.Random;

/**
 * Class setting Tumbler pattern on the game grid.
 */
public class SetTumblerPattern implements SetPattern {
    private int tumblerHeight = 6, tumblerWidth = 7;

    /**
     * Method responsible for clearing game grid (setting all cells dead) and checking if its size is not too small.
     * @param grid Game of the life grid.
     */
    @Override
    public void prepareGrid(Grid grid) {
        if(grid.getHeight() < tumblerHeight || grid.getWidth() < tumblerWidth)
            throw new IllegalArgumentException("Grid is too small to make Tumbler Pattern. Minimal size is 7x6");
        grid.clearCells();
    }

    /**
     * Method filling game grid with Tumbler pattern.
     * @param grid Game of the life grid.
     */
    @Override
    public void fillGrid(Grid grid) {
        Random random = new Random();
        int offset = random.nextInt(grid.getWidth() - tumblerWidth + 1);
        int[] patternCoords = new int[]{1, 5, 2, 5, 1, 4, 2, 4, 2, 3, 0, 2, 2, 2, 0, 1, 2, 1, 0, 0, 1, 0};
        for(int i = 0; i < patternCoords.length; i += 2) {
            grid.setCellAlive(patternCoords[i] + offset, offset + patternCoords[i + 1], true);
            grid.setCellAlive(tumblerWidth - 1 - patternCoords[i] + offset, offset + patternCoords[i + 1], true);
        }
    }
}
