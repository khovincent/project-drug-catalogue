package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    @FXML
    protected void onKeyPressEvent(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            btnLoginClick();
        }
    }

    @FXML
    protected void btnLoginClick() throws IOException {
        Alert alert;

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection connection = DBConnector.getConnect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Information");
                alert.setContentText("Login success!!");
                alert.showAndWait();

                // Load Home.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                Parent root = loader.load();

                // Get the stage from the current controller
                Stage stage = (Stage) txtUsername.getScene().getWindow();

                // Create a new scene with the Home.fxml content
                Scene scene = new Scene(root);

                // Set the new scene
                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Login failed!! Please check again.");
                alert.showAndWait();
                txtUsername.requestFocus();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error occurred while attempting to log in. Please try again later.");
            alert.showAndWait();
        }
    }
}
