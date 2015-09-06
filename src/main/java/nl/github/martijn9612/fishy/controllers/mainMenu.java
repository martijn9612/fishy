package nl.github.martijn9612.fishy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by martijn on 6-9-15.
 */
public class mainMenu {
    @FXML
    private Button exit;

    @FXML
    private void initialize() {

        exit.setOnAction((event) -> {
            System.exit(0);
        });

    }
}
