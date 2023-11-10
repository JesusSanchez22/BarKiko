package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    void goNuevo(ActionEvent event) {

        vaciar();

        modoEdicion(true);

        accion = Accion.NUEVO;
    }

    void vaciar(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtStock.setText("");
        txtCompra.setText("");
        txtVenta.setText("");
    }
    @FXML
    void goCancelar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de cancelar?");

        Optional<ButtonType> respuesta = alert.showAndWait();

        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            return;

        modoEdicion(false);
        cargarProducto(productoSeleccionado);
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

        Producto producto = ListProductos.getSelectionModel().getSelectedItem();

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar coche");
        confirmacion.setContentText("¿Estás seguro?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            return;

        try {

            conexionClass.eliminarProducto(producto);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Se ha borrado el producto con éxito");

            vaciar();

            cargarDatos();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void goGuardar(ActionEvent event) {


        int codigo = Integer.parseInt(txtCodigo.getText());
        String nombre = txtNombre.getText();
        int stock = Integer.parseInt(txtStock.getText());
        double venta = Double.parseDouble(txtVenta.getText());
        double compra = Double.parseDouble(txtCompra.getText());


        Producto producto = new Producto(codigo, nombre, stock, venta, compra);

        try {
            switch (accion) {
                case NUEVO:
                    conexionClass.guardarProducto(producto);
                    break;
                case MODIFICAR:
                    conexionClass.modificarProducto(productoSeleccionado, producto);
                    break;
            }
        } catch (SQLException sqle) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al guardar el coche");
        }


        cargarDatos();

        modoEdicion(false);
    }

    @FXML
    void goModificar(ActionEvent event) {

        modoEdicion(true);
        accion = Accion.MODIFICAR;

    }

    private void cargarProducto(Producto producto) {
        txtCodigo.setText(String.valueOf(producto.getCodigo()));
        txtNombre.setText(producto.getNombre());
        txtStock.setText(String.valueOf(producto.getCantidadEnStock()));
        txtVenta.setText(String.valueOf(producto.getPrecioVenta()));
        txtCompra.setText(String.valueOf(producto.getPrecioCompra()));
    }

    @FXML
    void seleccionarProducto(MouseEvent event) {
        productoSeleccionado = ListProductos.getSelectionModel().getSelectedItem();

        if(productoSeleccionado == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Esa casilla está vacía");
        } else {

            cargarProducto(productoSeleccionado);

        }

    }

   private void modoEdicion(boolean activar) {
        buttonNuevo.setDisable(activar);
        buttonGuardar.setDisable(!activar);
        buttonModificar.setDisable(activar);
        buttonEliminar.setDisable(activar);
        buttonCancelar.setDisable(!activar);

        txtCodigo.setEditable(activar);
        txtNombre.setEditable(activar);
        txtStock.setEditable(activar);
        txtVenta.setEditable(activar);
        txtCompra.setEditable(activar);


        ListProductos.setDisable(activar);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ListProductos.getItems().clear();
        modoEdicion(false);

        try {

            List<Producto> productos = conexionClass.datosProductos();

            ListProductos.setItems(FXCollections.observableList(productos));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
