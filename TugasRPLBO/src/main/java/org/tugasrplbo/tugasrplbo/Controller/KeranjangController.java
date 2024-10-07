package org.tugasrplbo.tugasrplbo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.tugasrplbo.tugasrplbo.Model.Obat;

public class KeranjangController {
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
    private TableColumn<Obat, Integer> jumlahColumn;
    @FXML
    private Button hapusButton;

    private ObservableList<Obat> keranjangObats;

    @FXML
    public void initialize() {
        kodeObatColumn.setCellValueFactory(new PropertyValueFactory<>("kodeObat"));
        namaObatColumn.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        merekObatColumn.setCellValueFactory(new PropertyValueFactory<>("merekObat"));
        hargaColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        jumlahColumn.setCellValueFactory(new PropertyValueFactory<>("jumlah"));

        keranjangObats = FXCollections.observableArrayList();
        tableView.setItems(keranjangObats);
    }

    public void addToKeranjang(Obat obat) {
        keranjangObats.add(obat);
    }

    public void setKeranjangItems(ObservableList<Obat> items) {
        keranjangObats.setAll(items);
    }

    @FXML
    private void handleHapusButton(ActionEvent event) {
        Obat selectedObat = tableView.getSelectionModel().getSelectedItem();
        if (selectedObat != null) {
            keranjangObats.remove(selectedObat);
        }
    }
}
