package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonNuevo;

    @FXML
    private Button buttonCancelar;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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

        modoEdicion(false);

        ListProductos.getItems().clear();

        try {
            List<Producto> productos = conexionClass.datosProductos();
            ListProductos.setItems(FXCollections.observableList(productos));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    void goCancelar(ActionEvent event) {

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

        modoEdicion(true);
        accion = Accion.MODIFICAR;

    }

    @FXML
    void goNuevo(ActionEvent event) {
        cargarDatos();
    }

    private void cargarProducto(Producto producto) {
        txtCodigo.setText(coche.getMatricula());
        txtNombre.setText(coche.getMarca());
        txtStock.setText(coche.getModelo());
        txtVenta.setValue(coche.getTipo());
        txtCompra.setValue(coche.getTipo());
    }

    @FXML
    void seleccionarProducto(MouseEvent event) {
        productoSeleccionado = ListProductos.getSelectionModel().getSelectedItem();
        cargarProducto(productoSeleccionado);
    }

   private void modoEdicion(boolean activar) {
        buttonNuevo.setDisable(activar);
        buttonGuardar.setDisable(!activar);
        buttonModificar.setDisable(activar);
        buttonEliminar.setDisable(activar);

        txtCodigo.setEditable(activar);
        txtNombre.setEditable(activar);
        txtStock.setEditable(activar);
        txtVenta.setEditable(activar);
        txtCompra.setEditable(activar);


        ListProductos.setDisable(activar);
    }

}
