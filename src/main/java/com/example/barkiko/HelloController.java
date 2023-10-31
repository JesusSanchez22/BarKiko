package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button buttonEmpleados;

    @FXML
    private Button buttonProductos;

    @FXML
    private Label welcomeText;

    @FXML
    void goEmpleados(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("empleados.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    void goProductos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("productos.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

}
