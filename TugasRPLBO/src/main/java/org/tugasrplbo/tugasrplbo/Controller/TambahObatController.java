package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

public class TambahObatController {

    @FXML
    private TextField namaobt;

    @FXML
    private ChoiceBox<String> kategori;

    @FXML
    private TextArea deskripsi;

    @FXML
    private TextField merekobt; // Ubah FX ID ke merekobt

    @FXML
    private TextField hargaobt; // Ubah FX ID ke hargaobt

    private HomeController homeController;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    @FXML
    public void initialize() {
        // Mengisi ChoiceBox dengan opsi yang sama
        kategori.getItems().addAll("Batuk & Flu", "Demam & Nyeri", "Masalah Pencernaan","Alergi","THT");
    }

    @FXML
    protected void tambahObatClick() {
        String namaObat = namaobt.getText();
        String selectedKategori = kategori.getValue();
        String description = deskripsi.getText();
        String brand = merekobt.getText(); // Menggunakan merekobt
        double price = Double.parseDouble(hargaobt.getText()); // Menggunakan hargaobt

        // Tambahkan obat baru ke database
        DatabaseConnection.addNewObat(namaObat, selectedKategori, brand, price, description);

        // Refresh tabel di HomeController
        homeController.refreshTable();

        // Tutup stage
        Stage stage = (Stage) namaobt.getScene().getWindow();
        stage.close();
    }
}
