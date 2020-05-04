package gameOfLife;

import gameOfLife.controller.Controller;
import gameOfLife.model.GameOfLife;
import gameOfLife.model.Grid;
import gameOfLife.model.Pattern;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Game of Life class launching the game in new JavaFX window.
 */
public class GameOfLifeApplication extends Application {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final Pattern INITIAL_PATTERN = Pattern.RANDOM;
    private static final int CELL_SIZE = 10;
    private static final int FREQUENCY = 30;
    private static final String TITLE = "GameOfLife";
    private static final String VIEW_RESOURCE_PATH = "/view.fxml";
    private static GameOfLife gameOfLife;
    private static Parent view;
    private static FXMLLoader fxmlLoader;

    /**
     * Start extended from the JavaFX Application class, setting all the necessary functionality for JavaFX components,
     * i. e. stage title, fxml resources and controllers, and then displaying the game.
     * @param primaryStage Stage used to display the game
     * @throws IOException When path to the fxml is invalid
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        initPrimaryStage(primaryStage);
        initFXMLLoader();
        initController();
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    /**
     * Main application method, setting grid, Game of life instance and launching the game.
     * @param args command line arguments, used by launch method, but currently empty
     */
    public static void main(String[] args) {
        try {
            Grid grid = new Grid(WIDTH, HEIGHT);
            gameOfLife = new GameOfLife(grid, INITIAL_PATTERN);
            launch(args);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Getter returning frequency (number of ticks per second)
     * @return frequency
     */
    public static int getFREQUENCY() {
        return FREQUENCY;
    }

    private void initPrimaryStage(Stage primaryStage){
        primaryStage.setTitle(TITLE);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
    }

    private void initFXMLLoader() throws IOException{
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameOfLifeApplication.class.getResource(VIEW_RESOURCE_PATH));
        view = fxmlLoader.load();

    }

    private void initController(){
        Controller controller = fxmlLoader.getController();
        controller.setCellSize(CELL_SIZE);
        controller.setGameOfLifeScreen(gameOfLife);
    }
}
