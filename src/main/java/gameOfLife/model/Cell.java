package gameOfLife.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Basic element of the Game of Life, represents single cell on the grid
 */
public class Cell {
    private final BooleanProperty AliveProperty = new SimpleBooleanProperty();

    /**
     * Returns isAlive Property of the current cell
     * @return property responsible for state of the cell
     */
    public BooleanProperty getAliveProperty() {
        return AliveProperty;
    }

    /**
     * Returns whether current cell is alive or not
     * @return true if the cell is alive, false if it's dead
     */
    public boolean isAlive(){
        return AliveProperty.get();
    }

    /**
     * Sets current cell alive property
     * @param isAlive if the cell is alive, false if it's dead
     */
    void setAlive(boolean isAlive){
        AliveProperty.setValue(isAlive);
    }
}
