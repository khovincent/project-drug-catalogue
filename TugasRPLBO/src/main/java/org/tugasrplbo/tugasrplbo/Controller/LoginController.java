package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.tugasrplbo.tugasrplbo.Apps;
import org.tugasrplbo.tugasrplbo.Util.CurrentUser;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Hyperlink lblRegister;

    @FXML
    protected void btnLoginClick() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (DatabaseConnection.verifyCredentials(username, password)) {
            String status = DatabaseConnection.getUserStatus(username);

            CurrentUser.setUsername(username);
            CurrentUser.setStatus(status);

            // Login successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Information");
            alert.setContentText("Login successful!");
            alert.showAndWait();

            try {
                if ("admin".equalsIgnoreCase(status)) {
                    Apps.setRoot("home", "Admin Home", false);
                } else if ("user".equalsIgnoreCase(status)) {
                    Apps.setRoot("user-home", "User Home", false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
            txtUsername.clear();
            txtPassword.clear();
        }
    }

    @FXML
    protected void lblRegisterClick() {
        try {
            Apps.openViewWithModal("register-view", "Register New User", false, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
