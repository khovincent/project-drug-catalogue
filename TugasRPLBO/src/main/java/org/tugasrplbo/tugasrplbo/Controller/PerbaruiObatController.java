package org.tugasrplbo.tugasrplbo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tugasrplbo.tugasrplbo.Model.Obat;
import org.tugasrplbo.tugasrplbo.Util.DatabaseConnection;

public class PerbaruiObatController {
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
    private Obat obat;

    public void initData(HomeController homeController, Obat obat) {
        this.homeController = homeController;
        this.obat = obat;

        namaobt.setText(obat.getNamaObat());
        kategori.setValue(obat.getKategori());
        deskripsi.setText(obat.getDeskripsi());
        merekobt.setText(obat.getMerekObat()); // Set nilai merekObat
        hargaobt.setText(String.valueOf(obat.getHarga())); // Set nilai harga
    }

    @FXML
    protected void perbaruiObatClick() {
        String namaObat = namaobt.getText();
        String selectedKategori = kategori.getValue();
        String description = deskripsi.getText();
        String brand = merekobt.getText(); // Ambil merekObat dari TextField
        double price = Double.parseDouble(hargaobt.getText()); // Ambil harga dari TextField

        // Update obat di database
        DatabaseConnection.updateObat(obat.getKodeObat(), namaObat, selectedKategori, description, brand, price);

        // Refresh tabel di HomeController
        homeController.refreshTable();

        // Tutup stage
        Stage stage = (Stage) namaobt.getScene().getWindow();
        stage.close();
    }
}
