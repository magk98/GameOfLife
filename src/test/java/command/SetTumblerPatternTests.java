package command;

import gameOfLife.command.SetPattern;
import gameOfLife.command.SetTumblerPattern;
import gameOfLife.model.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SetTumblerPatternTests {
    private static SetPattern tumblerPattern;
    private static Grid grid;

    @BeforeAll
    static void setUp(){
        int WIDTH = 5;
        int HEIGHT = 5;
        grid = new Grid(WIDTH, HEIGHT);
        tumblerPattern = new SetTumblerPattern();
    }

    @Test
    void TooSmallGridForTumblerTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tumblerPattern.prepareGrid(grid));
        assertEquals("Grid is too small to make Tumbler Pattern. Minimal size is 7x6",
                exception.getMessage());
    }

}
