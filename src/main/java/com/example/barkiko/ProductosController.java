package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ProductosController {

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonModificar;

    @FXML
    private ListView<Producto> ListProductos;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCompra;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtVenta;

    private ConexionClass conexionClass;
    private Producto productoSeleccionado;

    private enum Accion{
        NUEVO, MODIFICAR
    }

    private Accion accion;

    public ProductosController(){
        conexionClass = new ConexionClass();

        try {
            conexionClass.conectar();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos(){

    }

    @FXML
    void goAgregar(ActionEvent event) {

    }

    @FXML
    void goAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    void goEliminar(ActionEvent event) {

    }

    @FXML
    void goGuardar(ActionEvent event) {

    }

    @FXML
    void goModificar(ActionEvent event) {

    }

    @FXML
    void seleccionarProducto(MouseEvent event) {

    }

}
