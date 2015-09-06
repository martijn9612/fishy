package nl.github.martijn9612.fishy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Creates the fishy game and launches it
 */
public class Launcher extends Application {
    private Stage primaryStage;
    private Pane view;
    private FXMLLoader loader;

    private void loadScreen(String screen) {
        try {
            loader.setLocation(new URL("file:./src/resources/" + screen + ".fxml"));
            view = loader.load();
            Scene newScene = new Scene(view);
            newScene.setUserAgentStylesheet("file:./src/resources/" + screen + ".css");
            primaryStage.setScene(newScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
     * @param rootStage
     * @throws Exception
     */
    @Override
    public void start(Stage rootStage) throws Exception {
        primaryStage = rootStage;
        loader = new FXMLLoader();
        primaryStage.setTitle("Fishy");
        view = FXMLLoader.load(new URL("file:./src/resources/mainMenu.fxml"));
        Scene scene = new Scene(view, 300, 300);
        scene.getStylesheets().add("file:./src/resources/mainMenu.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}