package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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


    }

    @FXML
    void goAgregar(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agregarEmpleado.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void goEliminar(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = conexionClass.conectar();
        String sql = "Delete from empleados where codigo = ?";

        PreparedStatement sentencia = connection.prepareStatement(sql);

        Empleado empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();


        if (empleadoSeleccionado != null) {
            tablaEmpleados.setEditable(true);
            int codEmpleado = empleadoSeleccionado.getCodigo();

            sentencia.setString(1, String.valueOf(codEmpleado));
            sentencia.executeUpdate();

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
    void goModificar(ActionEvent event) {

    }

    private ObservableList<Empleado> infoEmpleados;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Apellido1Column.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        Apellido2Column.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        CodigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        NombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        PuestoColumn.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        SueldoColumn.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        cargarDatosTabla();

    }


    private void cargarDatosTabla() {
        try {

            Connection connection = conexionClass.conectar();

            infoEmpleados = FXCollections.observableArrayList();

            String query = "SELECT * FROM empleados";

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

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
            statement.close();
            connection.close();

            tablaEmpleados.setItems(infoEmpleados);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al cargar los datos desde la base de datos.");
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


