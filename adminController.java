package com.example.hrmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class adminController {

    @FXML
    private TableView<Admin> tableView; // Assuming you have an Admin class
    @FXML
    private TableColumn<Admin, Integer> idColumn;
    @FXML
    private TableColumn<Admin, String> usernameColumn;
    @FXML
    private TableColumn<Admin, String> passwordColumn;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private ObservableList<Admin> adminList = FXCollections.observableArrayList();

    // Database connection details
    private String jdbcUrl = "jdbc:mysql://localhost:3306/hr_management"; // Replace with your database name
    private String dbUser = "root"; // Replace with your DB username
    private String dbPassword = ""; // Replace with your DB password

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadAdminData();
    }

    private void loadAdminData() {
        adminList.clear();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM admin"; // Assuming your admin table is named 'admin'
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                adminList.add(new Admin(id, username, password)); // Assuming you have an Admin class
            }
            tableView.setItems(adminList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            loadAdminData(); // Refresh the table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ActionEvent actionEvent) {
        Admin selectedAdmin = tableView.getSelectionModel().getSelectedItem();
        if (selectedAdmin == null) {
            showAlert("No selection", "Please select an admin record to update.");
            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE admin SET username = ?, password = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, selectedAdmin.getId());
            preparedStatement.executeUpdate();
            loadAdminData(); // Refresh the table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent actionEvent) {
        Admin selectedAdmin = tableView.getSelectionModel().getSelectedItem();
        if (selectedAdmin == null) {
            showAlert("No selection", "Please select an admin record to delete.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM admin WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, selectedAdmin.getId());
            preparedStatement.executeUpdate();
            loadAdminData(); // Refresh the table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void view(ActionEvent actionEvent) {
    }
}
