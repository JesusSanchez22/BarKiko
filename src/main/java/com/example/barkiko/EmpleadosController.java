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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable{

    public static Empleado empleadoSeleccionado;
    public static int codigoglobal;

    @FXML
    TableColumn<Empleado, String> NombreColumn;

    @FXML
    TableColumn<Empleado, String> Apellido1Column;

    @FXML
    TableColumn<Empleado, String> Apellido2Column;

    @FXML
    TableColumn<Empleado, Integer> CodigoColumn;


    @FXML
    TableColumn<Empleado, String> PuestoColumn;

    @FXML
    TableColumn<Empleado, Double> SueldoColumn;

    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonModificar;

    @FXML
    private Label welcomeText;

    private final ConexionClass conexionClass;


    public EmpleadosController() {
        conexionClass = new ConexionClass();

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
    void goAgregar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("agregarEmpleado.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    void goEliminar(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        Empleado empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado != null) {

            tablaEmpleados.setEditable(true);


            conexionClass.eliminarEmpleado(empleadoSeleccionado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("Empleado borrado correctamente");
            alert.showAndWait();

            infoEmpleados.remove(empleadoSeleccionado);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha seleccionado ningún empleado");

            alert.showAndWait();
        }

    }

    @FXML
    void goModificar(ActionEvent event) throws IOException {

        empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();
        codigoglobal = empleadoSeleccionado.getCodigo();

        if (empleadoSeleccionado != null) {
            Parent root = FXMLLoader.load(getClass().getResource("ModificarEmpleado.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.toFront();
            appStage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha seleccionado ningún empleado");

            alert.showAndWait();
        }

    }


    private ObservableList<Empleado> infoEmpleados;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        NombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Apellido1Column.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        Apellido2Column.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        CodigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        PuestoColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        SueldoColumn.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        try {
            conexionClass.conectar();
            cargarDatosTabla();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cargarDatosTabla() {
        try {

            infoEmpleados = FXCollections.observableArrayList();

            ResultSet rs = conexionClass.cargarDatosTabla();

            while (rs.next()) {
                Empleado empleado = new Empleado(

                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getDouble("sueldo"),
                        rs.getString("puesto")

                );
                infoEmpleados.add(empleado);
            }

            rs.close();

            tablaEmpleados.setItems(infoEmpleados);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al cargar los datos desde la base de datos.");
            alert.showAndWait();
        }
    }




}


