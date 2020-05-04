package gameOfLife.model;

import gameOfLife.GameOfLifeApplication;
import gameOfLife.command.ChangePattern;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;

/**
 * Game of Life Instance Class, containing cell grid, handling updating grid in each tick
 * and capable of changing playing/pausing and restarting game.
 */
public class GameOfLife {
    private Grid grid;
    private Timeline timeline;
    private ChangePattern changePattern;

    /**
     * Creates single Game Of Life instance and sets its grid and initial pattern.
     * @param grid Grid with game of life cells.
     * @param pattern Initial pattern chosen from defaults patterns, i. e. The Queen Bee Shuttle, Tumbler or random pattern.
     */
    public GameOfLife(Grid grid, Pattern pattern) {
        setGrid(grid);
        changePattern = new ChangePattern(grid, pattern);
        changePattern.call();
        updateTimeline();
    }

    private void updateTimeline() {
        // 1000 ms / 30 (frequency) = approx. 33ms per tick
        Duration duration = new Duration(Duration.millis(1000).divide(GameOfLifeApplication.getFREQUENCY()).toMillis());
        EventHandler<ActionEvent> eventHandler = event -> playTick();
        KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Method responsible for playing the game after it was paused.
     * Possible to trigger by clicking play/pause button or Space key.
     */
    public void play() {
        timeline.play();
    }

    /**
     * Method responsible for pausing the game.
     * Possible to trigger by clicking play/pause button or Space key.
     */
    public void pause() {
        timeline.pause();
    }

    private void playTick(){
        boolean[][] nextStepCells = new boolean[grid.getWidth()][grid.getHeight()];
        for(int x = 0; x < grid.getWidth(); x++){
            for(int y = 0; y < grid.getHeight(); y++){
                Cell cell = grid.getCell(x, y);
                int numberOfAliveNeighbours = countAliveNeighbours(x, y);
                boolean isAliveInNextState = ((cell.isAlive() && numberOfAliveNeighbours == 2) || numberOfAliveNeighbours == 3);
                if(isAliveInNextState)
                    nextStepCells[x][y] = true;
            }
        }
        grid.updateCells(nextStepCells);
    }

    private int countAliveNeighbours(int x, int y){
        return (int) getNeighbours(x, y).stream().filter(Cell::isAlive).count();
    }

    private List<Cell> getNeighbours(int x, int y){
        List<Cell> neighbours = new LinkedList<>();
        for(int xOffset = -1; xOffset < 2; xOffset++){
            for(int yOffset = -1; yOffset < 2; yOffset++)
                if(xOffset != 0 || yOffset != 0)
                    neighbours.add(grid.getCell(getOverlappedIndex(x + xOffset, grid.getWidth()),
                            getOverlappedIndex(y + yOffset, grid.getHeight())));
        }
        return neighbours;
    }

    private int getOverlappedIndex(int index, int limit){
        if(index < 0)
            return limit - 1;
        else return index % limit;
    }

    /**
     * Getter returning current Game of Life Grid.
     * @return grid in the current state.
     */
    public Grid getGrid() {
        return grid;
    }

    private void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Getter returning changePattern command.
     * @return Command responsible for changing patterns on the game grid.
     */
    public ChangePattern getChangePattern() {
        return changePattern;
    }
}
