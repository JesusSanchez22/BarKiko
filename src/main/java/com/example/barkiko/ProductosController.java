package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {


    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonModificar;


    @FXML
    private Label welcomeText;

    @FXML
    private TableColumn<String, String> CodigoColumn;

    @FXML
    private TableView<String> tablaProductos;

    @FXML
    void goAgregar(ActionEvent event) {

    }

    @FXML
    void goEliminar(ActionEvent event) {

    }

    @FXML
    void goModificar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CodigoColumn = new TableColumn<>("Item1");

        tablaProductos.getColumns().add(CodigoColumn);

        tablaProductos.getItems().addAll("Data1");


    }
}
