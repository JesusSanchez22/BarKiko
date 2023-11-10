package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    ConexionClass conexionClass;

    public RegisterController() throws SQLException, IOException, ClassNotFoundException {
        conexionClass = new ConexionClass();
        conexionClass.conectar();
    }

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonContinuar;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label welcomeText;

    @FXML
    void goAtras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    void goContinuar(ActionEvent event) throws IOException, SQLException {

        guardarUsuario();

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    void guardarUsuario() throws SQLException {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();

        Usuario usuario = new Usuario(nombre, apellido, correo, contraseña);

        conexionClass.guardarUsuario(usuario);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("Usuario creado correctamente");
        alert.showAndWait();

    }

}
