package gameOfLife.command;

import gameOfLife.model.Grid;
import gameOfLife.model.Pattern;

/**
 * Class cycling through available patterns.
 */
public class ChangePattern {
    private Grid grid;
    private Pattern pattern;
    private SetPattern setPattern;

    /**
     * Creates object responsible for changing pattern on the game grid.
     * @param grid Game of Life grid (2D cell array)
     * @param pattern Initial Pattern (for now it's Random Pattern)
     */
    public ChangePattern(Grid grid, Pattern pattern){
        setPattern(pattern);
        setGrid(grid);
    }

    /**
     * Method setting object responsible for clearing the grid and creating particular pattern.
     */
    public void call(){
        switch (pattern) {
            case RANDOM:
                setPattern = new SetRandomPattern();
                break;
            case TUMBLER:
                setPattern = new SetTumblerPattern();
                break;
            case QUEEN_BEE:
                setPattern = new SetQueenBeePattern();
                break;
        }
        setNextPattern();
        setPattern.set(grid);
    }

    private void setNextPattern(){
        Pattern[] patterns = Pattern.values();
        int index = (pattern.ordinal() + 1) % patterns.length;
        setPattern(patterns[index]);
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

    private void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
