package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModificarEmpleadoController {

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private ChoiceBox<?> cbPuesto;

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

        ModificarEmpleadoVentana(empleado);
    }

    private Empleado empleado;

    public void ModificarEmpleadoVentana(Empleado empleado) {
        this.empleado = empleado;

        TextField nombreField = new TextField(empleado.getNombre());
        TextField apellidoField1 = new TextField(empleado.getApellido1());
        TextField apellidoField2 = new TextField(empleado.getApellido2());
        TextField puestoField = new TextField(empleado.getPuesto());
        TextField sueldoField = new TextField(Double.toString(empleado.getSueldo()));


        Button guardarButton = new Button("Guardar");
        guardarButton.setOnAction(event -> {
            empleado.setNombre(nombreField.getText());
            empleado.setApellido1(apellidoField1.getText());
            empleado.setApellido2(apellidoField2.getText());
            empleado.setPuesto(puestoField.getText());



        });


    }
}
