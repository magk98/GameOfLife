package gameOfLife.model;

/**
 * Grid class which represents grid of cells in Game Of Life, contains information about every cell in the game. Every grid must be given positive width and height parameters
 */
public class Grid {
    private int width;
    private int height;
    private Cell[][] cells;

    /**
     * Creates single Game Of Life grid instance and sets its size (width and height)
     * @param width positive width of the grid
     * @param height positive height of the grid
     */
    public Grid(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
        this.setCells(initCells());
    }

    /**
     * Setting cell at the given coordinates alive value.
     * @param x - horizontal coordinate of the cell
     * @param y - vertical coordinate of the cell
     * @param isAlive whether the cell is alive or not
     */
    public void setCellAlive(int x, int y, boolean isAlive){
        this.cells[x][y].setAlive(isAlive);
    }

    /**
     * Returns cell in the grid at the given coordinates.
     * @param x horizontal coordinate of the cell
     * @param y vertical coordinate of the cell
     * @return cell from the grid
     */
    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    private Cell[][] initCells(){
        Cell[][] cells = new Cell[getWidth()][getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                cells[i][j] = new Cell();
                cells[i][j].setAlive(false);
            }
        }
        return cells;
    }

    void updateCells(boolean[][] updatedValueCells){
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0; y < getHeight(); y++)
                setCellAlive(x, y, updatedValueCells[x][y]);
        }
    }

    /**
     * Method clearing all cells in the grid (setting all Cells dead).
     */
    public void clearCells(){
        boolean[][] clearedCells = new boolean[getWidth()][getHeight()];
        updateCells(clearedCells);
    }

    /**
     * Returns width of the grid
     * @return width of the grid
     */
    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        if(width <= 0) throw new IllegalArgumentException("Width must be positive");
        this.width = width;
    }

    /**
     * Returns height of the grid
     * @return height of the grid
     */
    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        if(height <= 0) throw new IllegalArgumentException("Height must be positive");
        this.height = height;
    }

    private void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
