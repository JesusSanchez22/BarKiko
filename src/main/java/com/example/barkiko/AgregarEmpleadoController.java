package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarEmpleadoController implements Initializable {


    private ConexionClass conexionClass;

    public AgregarEmpleadoController(){
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

    String arrayCb[] = {"Cocina", "Camarero", "Barra"};

    @FXML
    private ChoiceBox<String> cbPuesto;


    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrimerApellido;

    @FXML
    private TextField txtSegundoApellido;

    @FXML
    private TextField txtSueldo;

    @FXML
    private Label welcomeText;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = conexionClass.conectar();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cbPuesto.getItems().addAll(arrayCb);
        cbPuesto.setValue("Puesto");
    }


    @FXML
    void goAgregar(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = conexionClass.conectar();

        String sql = "INSERT INTO empleados (nombre, apellido1, apellido2, sueldo, puesto) VALUES (?,?,?,?,?)";

        PreparedStatement sentencia = connection.prepareStatement(sql);

        sentencia.setString(1, txtNombre.getText());
        sentencia.setString(2, txtPrimerApellido.getText());
        sentencia.setString(3, txtSegundoApellido.getText());
        sentencia.setString(4, txtSueldo.getText());
        sentencia.setString(5, cbPuesto.getValue());

        sentencia.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("El empleado ha sido agregado correctamente");

        alert.showAndWait();

    }

    @FXML
    void goCancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("empleados.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }


}
