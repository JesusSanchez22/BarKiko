package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable{

    @FXML
    private TableColumn<Empleado, String> Apellido1Column;

    @FXML
    private TableColumn<Empleado, String> Apellido2Column;

    @FXML
    private TableColumn<Empleado, Integer> CodigoColumn;

    @FXML
    private TableColumn<Empleado, String> NombreColumn;

    @FXML
    private TableColumn<Empleado, String> PuestoColumn;

    @FXML
    private TableColumn<Empleado, Double> SueldoColumn;

    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonModificar;

    @FXML
    private Label welcomeText;

    private ConexionClass conexionClass;

    public EmpleadosController() {
        conexionClass = new ConexionClass();

        try {
            conexionClass.conectar();
        } catch (SQLException sqle) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error al conectar con la base de datos");
            alert.showAndWait();
        } catch (ClassNotFoundException cnfe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error al iniciar la aplicación");
            alert.showAndWait();
        } catch (IOException ioe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error al cargar la configuración");
            alert.showAndWait();
        }

        System.out.println(System.getProperty("user.home"));
    }

    @FXML
    void goAgregar(ActionEvent event) {

    }

    @FXML
    void goEliminar(ActionEvent event) {

    }

    @FXML
    void goModificar(ActionEvent event) {

    }

    private ObservableList<Empleado> infoEmpleados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            conexionClass.conectar();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            List<Empleado> empleados = conexionClass.obtenerEmpleados();
            tablaEmpleados.setItems(FXCollections.observableList(empleados));

        } catch (SQLException sqle) {
        }


    }

}
