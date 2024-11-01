package com.example.hrmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class employeeController {
    @FXML
    private Label username;


    public void login(ActionEvent actionEvent) {try {
        Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

        Stage secondStage = new Stage();
        secondStage.setTitle("Dashboard");
        secondStage.setScene(new Scene(secondScene));
        Stage firstSceneStage = (Stage) username.getScene().getWindow();

        firstSceneStage.close();


        secondStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void logout(ActionEvent actionEvent) {
    }
}