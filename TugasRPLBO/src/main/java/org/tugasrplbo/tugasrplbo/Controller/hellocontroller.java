package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class hellocontroller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}