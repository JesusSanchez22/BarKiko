package com.example.barkiko;

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

import static com.example.barkiko.EmpleadosController.codigoglobal;

public class ModificarEmpleadoController implements Initializable {

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ChoiceBox<String> cbPuesto;

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
    private ConexionClass conexionClass;

    public ModificarEmpleadoController(){

        conexionClass = new ConexionClass();

    }

    String arrayCb[] = {"Cocina", "Camarero", "Barra"};

    @FXML
    void goCancelar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("empleados.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    void goConfirmar(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {

        int codigo = codigoglobal;
        String nombre = txtNombre.getText();
        String apellido1 = txtPrimerApellido.getText();
        String apellido2 = txtSegundoApellido.getText();
        Double sueldo = Double.parseDouble(txtSueldo.getText());
        String puesto = cbPuesto.getSelectionModel().getSelectedItem();

        Empleado empleado = new Empleado(codigo, nombre, apellido1, apellido2, sueldo, puesto);

        conexionClass.modificarEmpleado(empleado);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("El empleado ha sido modificado correctamente");

        alert.showAndWait();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

}
