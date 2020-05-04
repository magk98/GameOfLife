package model;

import gameOfLife.model.Grid;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTests {
    private static int WIDTH, HEIGHT;
    private static Grid grid;

    @BeforeAll
    static void setUp(){
        WIDTH = 5;
        HEIGHT = 5;
        grid = new Grid(WIDTH, HEIGHT);
    }

    @Test
    void NegativeGridWidthTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Grid(-5, HEIGHT));
        assertEquals("Width must be positive", exception.getMessage());
    }

    @Test
    void NegativeGridHeightTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Grid(WIDTH, -1));
        assertEquals("Height must be positive", exception.getMessage());
    }

    @Test
    void ZeroGridWidthTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Grid(0, HEIGHT));
        assertEquals("Width must be positive", exception.getMessage());
    }

    @Test
    void ZeroGridHeightTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Grid(WIDTH, 0));
        assertEquals("Height must be positive", exception.getMessage());
    }

    @Test
    void SetCellAliveTrueTest(){
        int x = 0, y = 0;
        assertFalse(grid.getCell(x, y).isAlive());
        grid.setCellAlive(x, y, true);
        assertTrue(grid.getCell(x, y).isAlive());
    }

    @Test
    void SetCellAliveFalseTest(){
        int x = 0, y = 0;
        grid.setCellAlive(x, y, true);
        assertTrue(grid.getCell(x, y).isAlive());
        grid.setCellAlive(x, y, false);
        assertFalse(grid.getCell(x, y).isAlive());
    }

}