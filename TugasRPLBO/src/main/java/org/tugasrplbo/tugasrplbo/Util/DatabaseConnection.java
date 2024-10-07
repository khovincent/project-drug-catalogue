package org.tugasrplbo.tugasrplbo.Util;

import org.tugasrplbo.tugasrplbo.Model.Obat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:katalogObatDB.sqlite";

    public static List<Obat> getAllObat() {
        List<Obat> obats = new ArrayList<>();
        String query = "SELECT * FROM daftarObat";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int kodeObat = resultSet.getInt("kodeObat");
                String namaObat = resultSet.getString("namaObat");
                String kategori = resultSet.getString("kategori");
                String merekObat = resultSet.getString("merekObat");
                double harga = resultSet.getDouble("harga");
                String deskripsi = resultSet.getString("deskripsi");

                Obat obat = new Obat(kodeObat, namaObat, kategori, merekObat, harga, deskripsi);
                obats.add(obat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obats;
    }

    // Example implementation of verifyCredentials method
    public static boolean verifyCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? AND Password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // New method to get user status
    public static String getUserStatus(String username) {
        String query = "SELECT Status FROM User WHERE Username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addNewObat(String namaObat, String kategori, String merekObat, double harga, String deskripsi) {
        String query = "INSERT INTO daftarObat (namaObat, kategori, merekObat, harga, deskripsi) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, namaObat);
            statement.setString(2, kategori);
            statement.setString(3, merekObat);
            statement.setDouble(4, harga);
            statement.setString(5, deskripsi);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteObat(int kodeObat) {
        String query = "DELETE FROM daftarObat WHERE kodeObat = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, kodeObat);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateObat(int kodeObat, String namaObat, String kategori, String deskripsi, String merekObat, double harga) {
        String query = "UPDATE daftarObat SET namaObat = ?, kategori = ?, merekObat = ?, harga = ?, deskripsi = ? WHERE kodeObat = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, namaObat);
            statement.setString(2, kategori);
            statement.setString(3, merekObat);
            statement.setDouble(4, harga);
            statement.setString(5, deskripsi);
            statement.setInt(6, kodeObat);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Obat> searchObat(String keyword) {
        List<Obat> obats = new ArrayList<>();
        String query = "SELECT * FROM daftarObat WHERE namaObat LIKE ? OR kategori LIKE ? OR deskripsi LIKE ? OR merekObat LIKE ? OR harga LIKE ? OR kodeObat LIKE ?";
        keyword = "%" + keyword + "%";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 1; i <= 6; i++) {
                statement.setString(i, keyword);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int kodeObat = resultSet.getInt("kodeObat");
                    String namaObat = resultSet.getString("namaObat");
                    String kategori = resultSet.getString("kategori");
                    String merekObat = resultSet.getString("merekObat");
                    double harga = resultSet.getDouble("harga");
                    String deskripsi = resultSet.getString("deskripsi");

                    Obat obat = new Obat(kodeObat, namaObat, kategori, merekObat, harga, deskripsi);
                    obats.add(obat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obats;
    }

    public static List<Obat> filterSearch(String namaObat, String kategori, String merekObat, double minPrice, double maxPrice, String sortBy) {
        List<Obat> obats = new ArrayList<>();
        String query = "SELECT * FROM daftarObat WHERE 1=1";

        if (namaObat != null && !namaObat.isEmpty()) {
            query += " AND namaObat LIKE ?";
        }
        if (kategori != null && !kategori.isEmpty()) {
            query += " AND kategori = ?";
        }
        if (merekObat != null && !merekObat.isEmpty()) {
            query += " AND merekObat LIKE ?";
        }
        if (minPrice > 0) {
            query += " AND harga >= ?";
        }
        if (maxPrice > 0) {
            query += " AND harga <= ?";
        }
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy) {
                case "Alphabetical":
                    query += " ORDER BY namaObat ASC";
                    break;
                case "Reverse Alphabetical":
                    query += " ORDER BY namaObat DESC";
                    break;
                case "Harga Tertinggi":
                    query += " ORDER BY harga DESC";
                    break;
                case "Harga Terendah":
                    query += " ORDER BY harga ASC";
                    break;
            }
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;

            if (namaObat != null && !namaObat.isEmpty()) {
                statement.setString(paramIndex++, "%" + namaObat + "%");
            }
            if (kategori != null && !kategori.isEmpty()) {
                statement.setString(paramIndex++, kategori);
            }
            if (merekObat != null && !merekObat.isEmpty()) {
                statement.setString(paramIndex++, "%" + merekObat + "%");
            }
            if (minPrice > 0) {
                statement.setDouble(paramIndex++, minPrice);
            }
            if (maxPrice > 0) {
                statement.setDouble(paramIndex++, maxPrice);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int kodeObat = resultSet.getInt("kodeObat");
                    String namaObatRes = resultSet.getString("namaObat");
                    String kategoriRes = resultSet.getString("kategori");
                    String merekObatRes = resultSet.getString("merekObat");
                    double harga = resultSet.getDouble("harga");
                    String deskripsi = resultSet.getString("deskripsi");

                    Obat obat = new Obat(kodeObat, namaObatRes, kategoriRes, merekObatRes, harga, deskripsi);
                    obats.add(obat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obats;
    }
    public static List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT DISTINCT namaObat FROM daftarObat";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                categories.add(resultSet.getString("namaObat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO User (Username, Password, Status) VALUES (?, ?, 'user')";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
