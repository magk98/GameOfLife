package gameOfLife.command;

import gameOfLife.model.Grid;

import java.util.Random;

/**
 * todo
 */
public class SetRandomPattern implements SetPattern {
    @Override
    public void prepareGrid(Grid grid) {
        grid.clearCells();
    }

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
