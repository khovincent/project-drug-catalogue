package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HomeController {

    private Stage stage; // variabel untuk menyimpan stage home

    // Metode untuk mengatur stage home
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void tambahObat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tmbhobt.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 407, 305);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleProfileButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Mengatur HomeController saat memuat ProfileController
        ProfileController profileController = loader.getController();
        profileController.setHomeControllerStage(this.stage);

        stage.show();
    }
}
