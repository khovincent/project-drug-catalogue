package org.tugasrplbo.tugasrplbo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.tugasrplbo.tugasrplbo.Apps;
import org.tugasrplbo.tugasrplbo.Model.Obat;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserHomeController {
    @FXML
    private Button CrButton;
    @FXML
    private TableView<Obat> tableView;
    @FXML
    private TableColumn<Obat, Integer> kodeObatColumn;
    @FXML
    private TableColumn<Obat, String> namaObatColumn;
    @FXML
    private TableColumn<Obat, String> kategoriColumn;
    @FXML
    private TableColumn<Obat, String> merekObatColumn;
    @FXML
    private TableColumn<Obat, Double> hargaColumn;
    @FXML
    private TableColumn<Obat, String> deskripsiColumn;
    @FXML
    private TextField searchField;
    private ObservableList<Obat> obats;
    private ObservableList<Obat> keranjangObats;

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
    @FXML
    private ChoiceBox<String> kategoriChoiceBox;


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
    public ObservableList<Obat> getKeranjangObats() {
        return keranjangObats;
    }


    @FXML
    public void initialize() {
        kodeObatColumn.setCellValueFactory(new PropertyValueFactory<>("kodeObat"));
        namaObatColumn.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        merekObatColumn.setCellValueFactory(new PropertyValueFactory<>("merekObat"));
        hargaColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        kategoriChoiceBox.getItems().addAll("","Batuk & Flu", "Demam & Nyeri", "Masalah Pencernaan","Alergi","THT");
        filterChoiceBox.getItems().addAll("Alphabetical", "Reverse Alphabetical", "Harga Tertinggi", "Harga Terendah");

        // Initialize the ObservableList
        obats = FXCollections.observableArrayList(DatabaseConnection.getAllObat());
        keranjangObats = FXCollections.observableArrayList();
        tableView.setItems(obats);
    }

    @FXML
    private void handleSearchButton(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();

        if (searchText == null || searchText.trim().isEmpty()) {
            // If the search field is empty, show all items
            tableView.setItems(obats);
        } else {
            List<Obat> filteredObats = obats.stream()
                    .filter(obat -> obat.getNamaObat().toLowerCase().contains(searchText) ||
                            obat.getKategori().toLowerCase().contains(searchText) ||
                            obat.getMerekObat().toLowerCase().contains(searchText) ||
                            String.valueOf(obat.getHarga()).contains(searchText) ||
                            obat.getDeskripsi().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            ObservableList<Obat> observableList = FXCollections.observableArrayList(filteredObats);
            tableView.setItems(observableList);

            if (filteredObats.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Information");
                alert.setContentText("No items found matching the search criteria.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleBeliObatButton(ActionEvent event) throws IOException {
        Obat selectedObat = tableView.getSelectionModel().getSelectedItem();
        if (selectedObat != null) {
            int jumlah = Apps.openTambahJumlahObatView("tambah-jumlah-obat", "Tambah Jumlah Obat", false, this);
            if (jumlah > 0) {
                selectedObat.setJumlah(jumlah);
                keranjangObats.add(selectedObat);  // Assuming keranjangObats is an ObservableList in KeranjangController
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Obat Selected");
            alert.setContentText("Please select an obat in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleKeranjangButton(ActionEvent event) throws IOException {
        Apps.openKeranjangViewWithController("keranjang-view", "Keranjang Pembelian", false, this);
    }
    @FXML
    private void handleProfileButton(ActionEvent event) {
        try {
            Apps.openProfileViewWithController("profile-view", "Tampilan Profile", false, this);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not open the profile view.");
            alert.setContentText("An error occurred while trying to open the profile view.");
            alert.showAndWait();
        }
    }


    @FXML
    protected void onActionCredit(ActionEvent event)  {
        try {
            Apps.opencreditUserViewWithController("credit", "Tampilan credit", false, this);
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
