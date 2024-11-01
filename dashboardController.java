package com.example.hrmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardController {


    public Label goback;



    public void logoutclick(ActionEvent actionEvent) {
    }

    public void adminclick(ActionEvent actionEvent) { try {
        Parent secondScene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Stage secondStage = new Stage();
        secondStage.setTitle("Test2");
        secondStage.setScene(new Scene(secondScene));
        Stage firstSceneStage = (Stage)goback .getScene().getWindow();

        firstSceneStage.close();


        secondStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    }

    public void employeeclick(ActionEvent actionEvent) {
    }

    public void exitclick(ActionEvent actionEvent) {
    }
}
