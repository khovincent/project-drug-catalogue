package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class TambahJumlahObatController {

    @FXML
    private TextField jumlahField;

    private int jumlah;
    private boolean isConfirmed = false;
    private UserHomeController userHomeController;

    @FXML
    public void handleTambahButton(ActionEvent event) {
        try {
            jumlah = Integer.parseInt(jumlahField.getText());
            isConfirmed = true;

            // Close the dialog
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        }
    }

    public int getJumlah() {
        return jumlah;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setUserHomeController(UserHomeController userHomeController) {
        this.userHomeController = userHomeController;
    }
}
