package org.tugasrplbo.tugasrplbo.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tugasrplbo.tugasrplbo.Apps;
import org.tugasrplbo.tugasrplbo.Model.Obat;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

import java.io.IOException;
import java.util.List;

public class HomeController {
    @FXML
    private TableView<Obat> tableView;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button CrButton;
    @FXML
    private TableColumn<Obat, Integer> kodeObatColumn;
    @FXML
    private TableColumn<Obat, String> namaObatColumn;
    @FXML
    private TableColumn<Obat, String> kategoriColumn;
    @FXML
    private TableColumn<Obat, String> deskripsiColumn;
    @FXML
    private ChoiceBox<String> kategoriChoiceBox;
    @FXML
    private TableColumn<Obat, String> merekObatColumn;
    @FXML
    private TableColumn<Obat, Double> hargaColumn;

    @FXML
    private TextField searchField;
    @FXML
    private TextField filterNamaObat;
    @FXML
    private TextField filterMerekObat;
    @FXML
    private TextField filterMinimumPrice;
    @FXML
    private TextField filterMaximumPrice;
    @FXML
    private ComboBox<String> filterChoiceBox;

    private ObservableList<Obat> obats;
    @FXML
    private void handleSearchButton(ActionEvent event) {
        String keyword = searchField.getText().trim();
        if (!keyword.isEmpty()) {
            obats.setAll(DatabaseConnection.searchObat(keyword));
        } else {
            // Reload all obat if search field is empty
            refreshTable();
        }
    }

    @FXML
    protected void handleFilterSearchButton() {
        String namaObat = filterNamaObat.getText();
        String kategori = kategoriChoiceBox.getValue();
        String merekObat = filterMerekObat.getText();
        double minPrice = filterMinimumPrice.getText().isEmpty() ? 0 : Double.parseDouble(filterMinimumPrice.getText());
        double maxPrice = filterMaximumPrice.getText().isEmpty() ? 0 : Double.parseDouble(filterMaximumPrice.getText());
        String sortBy = filterChoiceBox.getValue();

        List<Obat> filteredObats = DatabaseConnection.filterSearch(namaObat, kategori, merekObat, minPrice, maxPrice, sortBy);
        tableView.getItems().setAll(filteredObats);
    }


    @FXML
    public void initialize() {
        kodeObatColumn.setCellValueFactory(new PropertyValueFactory<>("kodeObat"));
        namaObatColumn.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        merekObatColumn.setCellValueFactory(new PropertyValueFactory<>("merekObat"));
        hargaColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        // Mengisi ChoiceBox dengan opsi yang sama
        kategoriChoiceBox.getItems().addAll("","Batuk & Flu", "Demam & Nyeri", "Masalah Pencernaan","Alergi","THT");
        filterChoiceBox.getItems().addAll("Alphabetical", "Reverse Alphabetical", "Harga Tertinggi", "Harga Terendah");

        // Bind table columns to Obat properties
        kodeObatColumn.setCellValueFactory(cellData -> cellData.getValue().kodeObatProperty().asObject());
        namaObatColumn.setCellValueFactory(cellData -> cellData.getValue().namaObatProperty());
        kategoriColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        merekObatColumn.setCellValueFactory(cellData -> cellData.getValue().merekObatProperty());
        hargaColumn.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());
        deskripsiColumn.setCellValueFactory(cellData -> cellData.getValue().deskripsiProperty());


        obats = FXCollections.observableArrayList(DatabaseConnection.getAllObat());
        tableView.setItems(obats);
    }

    @FXML
    private void handleTambahObatButton(ActionEvent event) throws IOException {
        Apps.openTambahObatView("tambah-obat-view", "Tambah Obat", false, this);
    }
    @FXML
    private void handleHapusObatButton(ActionEvent event) {
        Obat selectedObat = tableView.getSelectionModel().getSelectedItem();
        if (selectedObat != null) {
            // Hapus obat dari database
            DatabaseConnection.deleteObat(selectedObat.getKodeObat());

            // Hapus obat dari TableView
            obats.remove(selectedObat);
            tableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Pilih obat yang ingin dihapus.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handlePerbaruiObatButton(ActionEvent event) throws IOException {
        Obat selectedObat = tableView.getSelectionModel().getSelectedItem();
        if (selectedObat != null) {
            Apps.openViewWithModal("perbarui-obat-view", "Perbarui Obat", false, selectedObat, this);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Pilih obat yang ingin diperbarui.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleProfileButton(ActionEvent event) {
        try {
            Apps.openProfileAdminHomeViewWithController("profile-view-admin", "Tampilan Profile", false, this);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not open the profile view.");
            alert.setContentText("An error occurred while trying to open the profile view.");
            alert.showAndWait();
        }
    }

//
    @FXML
    private void onActionCredit(ActionEvent event)  {
        try {
            Apps.opencreditHomeViewWithController("credit", "Tampilan credit", false, this);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not open the credit");
            alert.setContentText("An error occurred while trying to open the credit");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowAllItemsButton(ActionEvent event) {
        refreshTable();
    }

    public void refreshTable() {
        obats.setAll(DatabaseConnection.getAllObat());
    }
}
