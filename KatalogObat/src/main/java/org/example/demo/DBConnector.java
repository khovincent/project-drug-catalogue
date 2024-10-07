package org.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Public\\Semester 4\\Prak RPLBO\\Proyek-Katalog-Obat\\KatalogObat\\dbObat.sqlite";

    public static Connection getConnect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace(); // Print stack trace for debugging
        }
        return conn;
    }
}
