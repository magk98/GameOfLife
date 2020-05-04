package command;

import gameOfLife.command.SetPattern;
import gameOfLife.command.SetQueenBeePattern;
import gameOfLife.model.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetQueenBeePatternTests {
    private static SetPattern queenBeePattern;
    private static Grid grid;

    @BeforeAll
    static void setUp(){
        int WIDTH = 5;
        int HEIGHT = 5;
        grid = new Grid(WIDTH, HEIGHT);
        queenBeePattern = new SetQueenBeePattern();
    }

    @Test
    void TooSmallGridForBeeShuttleTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> queenBeePattern.prepareGrid(grid));
        assertEquals("Grid is too small to make The Queen Bee Shuttle Pattern. Minimal size is 8x5",
                exception.getMessage());
    }
}
