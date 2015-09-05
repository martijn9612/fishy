package nl.github.martijn9612.fishy;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Creates the fishy game and launches it
 */
public class Launcher extends Application {
    /**
     * Starts the game
     *
     * @param args Are not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the initial screen for the game, the welcome screen
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Fishy");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 300);
        root.getChildren().add(canvas);
        Scene startScreen = new Scene(root);
        primaryStage.setScene(startScreen);
        primaryStage.show();
    }
}