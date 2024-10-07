package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

public class RegisterController {
    @FXML
    private TextField txtNewUsername;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    protected void btnRegisterClick() {
        String username = txtNewUsername.getText();
        String password = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Passwords do not match. Please try again.");
            alert.showAndWait();
            return;
        }

        if (DatabaseConnection.registerUser(username, password)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information");
            alert.setContentText("Registration successful! You can now log in.");
            alert.showAndWait();
            txtNewUsername.clear();
            txtNewPassword.clear();
            txtConfirmPassword.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Registration failed. Username may already be taken.");
            alert.showAndWait();
        }
    }
}
