package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TambahController {

    @FXML
    private TextArea deskripsi;

    @FXML
    private DatePicker expdate;

    @FXML
    private TextField kategori;

    @FXML
    private TextField kodeobt;

    @FXML
    private TextField namaobt;

    @FXML
    private Button tambahobt;

    @FXML
    void tambahbtn(ActionEvent event) {
        String url = "jdbc:sqlite:dbObat.sqlite";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO obat (kode_obat, nama_obat, kategori, deskripsi, tanggal_kadaluarsa) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, kodeobt.getText());
            statement.setString(2, namaobt.getText());
            statement.setString(3, kategori.getText());
            statement.setString(4, deskripsi.getText());
            statement.setString(5, expdate.getValue().toString());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data berhasil ditambahkan!");
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
