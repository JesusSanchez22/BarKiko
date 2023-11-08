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
            conexionClass.conectar();
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


        String nombre = txtNombre.getText();
        String primerApellido = txtPrimerApellido.getText();
        String segundoApellido = txtSegundoApellido.getText();
        double sueldo = Double.parseDouble(txtSueldo.getText());
        String puesto = cbPuesto.getValue();

        Empleado empleado = new Empleado(nombre, primerApellido, segundoApellido, sueldo, puesto);

        conexionClass.agregarEmpleado(empleado);

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
