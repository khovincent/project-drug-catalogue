package org.tugasrplbo.tugasrplbo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tugasrplbo.tugasrplbo.Apps;
import org.tugasrplbo.tugasrplbo.Util.CurrentUser;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Label userName;

    @FXML
    private Button userhomeButton;

    @FXML
    private Button keranjangviewButton;

    @FXML
    private Button logoutButton;

    private Stage homeStage;

    @FXML
    public void initialize() {
        // Initialize the profile view with user data
        userName.setText(CurrentUser.getUsername());
    }

    public void setHomeStage(Stage homeStage) {
        this.homeStage = homeStage;
    }

    @FXML
    private void handleUserhome(ActionEvent event) throws IOException {
        Apps.openUserhomeViewWithController("user-home", "Home User", false, this);
    }

    @FXML
    private void handleKeranjangView(ActionEvent event) throws IOException {
        Apps.openKeranjangViewWithController("keranjang-view", "Keranjang Pembelian", false, this);
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws IOException {
        Stage profileStage = (Stage) logoutButton.getScene().getWindow();
        Apps.openLogOutViewWithController("login-view", "Login", false, this);
        profileStage.close(); // Close the profile stage
        if (homeStage != null) {
            homeStage.close(); // Close the home stage
        }
    }
}
