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

public class ModificarEmpleadoController{

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


    }


    /**

    public void ModificarEmpleadoVentana(Empleado empleado) {
        this.empleado = empleado;


        Button guardarButton = new Button("Guardar");
        guardarButton.setOnAction(event -> {
            empleado.setNombre(txtNombre.getText());
            empleado.setApellido1(txtPrimerApellido.getText());
            empleado.setApellido2(txtSegundoApellido.getText());




        });


    }
     **/
}
