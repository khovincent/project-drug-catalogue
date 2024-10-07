package org.tugasrplbo.tugasrplbo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

import java.util.List;

public class BeliObatController {

    private UserHomeController userHomeController;

    public void setUserHomeController(UserHomeController userHomeController) {
        this.userHomeController = userHomeController;
    }

    @FXML
    private TextField namaobt;

    @FXML
    private TextField jumlahobt;

    @FXML
    private ChoiceBox<String> tipeobt;

    @FXML
    private Button beliobt;

    @FXML
    public void initialize() {
        populateKategoriChoiceBox();
    }

    private void populateKategoriChoiceBox() {
        List<String> categories = DatabaseConnection.getAllCategories();
        ObservableList<String> observableCategories = FXCollections.observableArrayList(categories);
        tipeobt.setItems(observableCategories);
    }
}
