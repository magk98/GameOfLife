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

public class GameOfLifeApplication extends Application {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int CELL_SIZE = 10;
    private static final int FREQUENCY = 30;
    private static final String TITLE = "GameOfLife";
    private static final String VIEW_RESOURCE_PATH = "/view.fxml";
    private static GameOfLife gameOfLife;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle(TITLE);
        primaryStage.setOnCloseRequest(event -> Platform.exit());
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameOfLifeApplication.class.getResource(VIEW_RESOURCE_PATH));
        Parent view = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.setCellSize(CELL_SIZE);
        controller.setGameOfLife(gameOfLife);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Grid grid = new Grid(WIDTH, HEIGHT);
            gameOfLife = new GameOfLife(grid, Pattern.RANDOM);
            launch(args);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static int getFREQUENCY() {
        return FREQUENCY;
    }

}
