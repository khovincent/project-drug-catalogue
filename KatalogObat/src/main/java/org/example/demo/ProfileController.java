package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ProfileController {

    @FXML
    private Button logoutbtn;
    private Stage homeStage; // variabel untuk menyimpan stage home

    // Metode untuk mengatur stage home
    public void setHomeControllerStage(Stage homeStage) {
        this.homeStage = homeStage;
    }

    @FXML
    protected void handleLogoutButtonClick(ActionEvent event) {
        // Close the current stage (window)
        Stage stage = (Stage) logoutbtn.getScene().getWindow();
        stage.close();

        // Menutup stage home
        if (homeStage != null) {
            homeStage.close();
        }

        // Load the Login.fxml file
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
