package gameOfLife.model;

import gameOfLife.GameOfLifeApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameOfLife {
    private Grid grid;
    private Pattern pattern;
    private Timeline timeline;


    public GameOfLife(Grid grid, Pattern pattern) throws Exception {
        setGrid(grid);
        setPattern(pattern);
        updateTimeline();
        createAndSetCellsPattern();
    }

    private void updateTimeline() {
        Duration duration = new Duration(Duration.millis(1000).divide(GameOfLifeApplication.getFREQUENCY()).toMillis());
        EventHandler<ActionEvent> eventHandler = event -> playTick();
        KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private void createAndSetCellsPattern() throws Exception{
        switch (this.getPattern()) {
            case RANDOM:
                createAndSetRandomCellsPattern();
                break;
            case TUMBLER:
                createAndSetTumblerCellsPattern();
                break;
            case QUEEN_BEE:
                createAndSetTheQueenBeeCellsPattern();
                break;
        }
    }

    private void createAndSetRandomCellsPattern(){
        clearCells();
        Random random = new Random();
        for(int x = 0; x < grid.getWidth(); x++){
            for(int y = 0; y < grid.getHeight(); y++){
                if(random.nextBoolean()) {
                    grid.setCellIsAlive(x, y, true);
                }
                else {
                    grid.setCellIsAlive(x, y, false);
                }
            }
        }
    }

    private void createAndSetTheQueenBeeCellsPattern() throws Exception{
        int queenHeight = 7, queenWidth = 4;
        if(grid.getHeight() < queenHeight || grid.getWidth() < queenWidth) {
            throw new Exception("Grid is too small to make The Queen Bee Shuttle Pattern. Minimal size is 8x5");
        }
        clearCells();
        Random random = new Random();
        int offset = random.nextInt(grid.getHeight() - queenHeight + 1);
        int[] patternCoords = new int[]{0, 0, 1, 0, 2, 1, 3, 2, 3, 3};
        for(int i = 0; i < patternCoords.length; i += 2) {
            grid.setCellIsAlive(patternCoords[i] + offset, patternCoords[i + 1] + offset, true);
            grid.setCellIsAlive(patternCoords[i] + offset, queenHeight - 1 - patternCoords[i + 1] + offset, true);
        }
    }

    private void createAndSetTumblerCellsPattern() throws Exception{
        int tumblerHeight = 6, tumblerWidth = 7;
        if(grid.getHeight() < tumblerHeight || grid.getWidth() < tumblerWidth)
            throw new Exception("Grid is too small to make Tumbler Pattern. Minimal size is 7x6");
        clearCells();
        Random random = new Random();
        int offset = random.nextInt(grid.getWidth() - tumblerWidth + 1);
        int[] patternCoords = new int[]{1, 5, 2, 5, 1, 4, 2, 4, 2, 3, 0, 2, 2, 2, 0, 1, 2, 1, 0, 0, 1, 0};
        for(int i = 0; i < patternCoords.length; i += 2) {
            grid.setCellIsAlive(patternCoords[i] + offset, offset + patternCoords[i + 1], true);
            grid.setCellIsAlive(tumblerWidth - 1 - patternCoords[i] + offset, offset + patternCoords[i + 1], true);
        }
    }

    public void setNextPattern(){
        try {
            Pattern[] patterns = Pattern.values();
            int index = (this.getPattern().ordinal() + 1) % patterns.length;
            this.setPattern(patterns[index]);
            System.out.println(patterns[index]);
            createAndSetCellsPattern();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void play() {
        timeline.play();
    }
    public void pause() {
        timeline.pause();
    }

    public void clear() {
        pause();
        clearCells();
    }

    private void clearCells(){
        boolean[][] clearedCells = new boolean[grid.getWidth()][grid.getHeight()];
        updateCells(clearedCells);
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
        updateCells(nextStepCells);
    }

    private void updateCells(boolean[][] updatedValueCells){
        for(int x = 0; x < grid.getWidth(); x++){
            for(int y = 0; y < grid.getHeight(); y++)
                grid.setCellIsAlive(x, y, updatedValueCells[x][y]);
        }
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

    public Grid getGrid() {
        return grid;
    }

    private void setGrid(Grid grid) {
        this.grid = grid;
    }

    private Pattern getPattern() {
        return pattern;
    }

    private void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

}
