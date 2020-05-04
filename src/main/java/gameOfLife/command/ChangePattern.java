package gameOfLife.command;

import gameOfLife.model.Grid;
import gameOfLife.model.Pattern;

/**
 * todo
 */
public class ChangePattern {
    private Grid grid;
    private SetPattern setPattern;

    /**
     *
     * @param grid Game of Life grid.
     */
    public ChangePattern(Grid grid){
        setGrid(grid);
    }

    /**
     * Method setting object responsible for clearing the grid and creating particular pattern.
     * @param pattern New Pattern to set on the game grid.
     */
    public void setPattern(Pattern pattern){
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
        setPattern.set(grid);
    }

    /**
     * Method returning next pattern in the following order: <ol>
     *     <li>Random Pattern</li>
     *     <li>The Queen Bee Shuttle Pattern</li>
     *     <li>Tumbler Pattern</li>
     * </ol>
     * and setting this pattern on the grid.
     */
    public Pattern getNextPattern(Pattern pattern){
        Pattern[] patterns = Pattern.values();
        int index = (pattern.ordinal() + 1) % patterns.length;
        this.setPattern(patterns[index]);
        return patterns[index];
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
}
