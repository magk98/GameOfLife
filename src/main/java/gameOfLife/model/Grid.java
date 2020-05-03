package gameOfLife.model;

public class Grid {
    private int width;
    private int height;
    private Cell[][] cells;

    /**
     * Creates single game of life grid instance and sets its size (width and height)
     * @param width positive width of the grid
     * @param height positive height of the grid
     */
    public Grid(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
        this.setCells(setInitialCellsFalse());
    }

    void setCellIsAlive(int x, int y, boolean isAlive){
        this.cells[x][y].setIsAlive(isAlive);
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    private Cell[][] setInitialCellsFalse(){
        Cell[][] cells = new Cell[getWidth()][getHeight()];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                cells[i][j] = new Cell();
                cells[i][j].setIsAlive(false);
            }
        }
        return cells;
    }


    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private void setCells(Cell[][] cells) {
        this.cells = cells;
    }

}
