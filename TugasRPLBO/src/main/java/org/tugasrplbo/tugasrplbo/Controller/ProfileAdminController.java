package org.tugasrplbo.tugasrplbo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.tugasrplbo.tugasrplbo.Apps;
import org.tugasrplbo.tugasrplbo.Util.CurrentUser;

import java.io.IOException;

public class ProfileAdminController {
    @FXML
    private ImageView AdminProfile;

    @FXML
    private Button adminhomeButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Label userName;

    private Stage homeStage;

    @FXML
    public void initialize() {
        userName.setText(CurrentUser.getUsername());
    }

    public void setHomeStage(Stage homeStage) {
        this.homeStage = homeStage;
    }

    @FXML
    void handleAdminhome(ActionEvent event) throws IOException {
        Apps.openAdminHomeViewWithController("home", "Admin Home", false, this);
    }


    @FXML
    void handleLogOut(ActionEvent event) throws IOException {
        Stage profileStage = (Stage) logoutButton.getScene().getWindow();
        Apps.openLogOutAdminViewWithController("login-view", "Login", false, this);
        profileStage.close(); // Close the profile stage
        if (homeStage != null) {
            homeStage.close(); // Close the home stage
        }
    }
}
