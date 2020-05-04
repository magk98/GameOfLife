package gameOfLife.controller;

import gameOfLife.model.GameOfLife;
import gameOfLife.model.Grid;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Class responsible for initializing JavaFX components, controlling the game flow on the JavaFX scene
 * and responding to user actions.
 */
public class Controller {
    private GameOfLife gameOfLife;
    private int cellSize;
    private boolean isRunning = false;

    @FXML
    private GridPane gridPane;
    @FXML
    private ToggleButton playButton;

    /**
     * Method setting game of life and initializing Screen.
     * @param gameOfLife Model of the game
     */
    public void setGameOfLifeScreen(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;
        initializeScreen();
    }

    /**
     * Setter setting size of the cells displayed on the JavaFX scene.
     * @param CellSize - size of the cell in pixels.
     */
    public void setCellSize(int CellSize){
        this.cellSize = CellSize;
    }

    private void initializeScreen() {
        Grid grid = getGameOfLife().getGrid();
        int width = grid.getWidth();
        int height = grid.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                addCellPane(x, y);
            }
        }
    }

    private void addCellPane(int x, int y) {
        Pane cellPane = new Pane();

        addCellPaneStyle(cellPane);
        addAlivePropertyListener(x, y, cellPane);
        setAliveStyle(cellPane, getGameOfLife().getGrid().getCell(x, y).isAlive());

        gridPane.add(cellPane, x, y);
    }

    private void addCellPaneStyle(Pane cellPane) {
        cellPane.setPrefSize(getCellSize(), getCellSize());
        GridPane.setFillHeight(cellPane, true);
        GridPane.setFillWidth(cellPane, true);
        cellPane.getStyleClass().add("cell-pane");
    }

    private void addAlivePropertyListener(int rowIndex, int columnIndex, Pane cellPane) {
        BooleanProperty aliveProperty = getGameOfLife().getGrid().getCell(rowIndex, columnIndex)
                .getAliveProperty();
        aliveProperty.addListener((observable, oldValue, newValue) ->
                setAliveStyle(cellPane, newValue));

    }

    private void setAliveStyle(Pane cellPane, boolean isAlive) {
        ObservableList<String> styleClass = cellPane.getStyleClass();
        if (isAlive) {
            styleClass.add("alive");
        } else {
            styleClass.remove("alive");
        }
    }

    @FXML
    private void onEnter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            setRunning(false);
            gameOfLife.getChangePattern().call();
        }
    }

    @FXML
    private void onPlayPause(ActionEvent event) {
        setRunning(!isRunning());
        if(isRunning) {
            getGameOfLife().play();
            playButton.setText("PAUSE");
        }
        else {
            getGameOfLife().pause();
            playButton.setText("PLAY");
        }
    }

    private GameOfLife getGameOfLife() {
        return gameOfLife;
    }

    private int getCellSize() {
        return cellSize;
    }

    private boolean isRunning() {
        return isRunning;
    }

    private void setRunning(boolean running) {
        isRunning = running;
    }


}
