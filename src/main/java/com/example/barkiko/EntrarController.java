package com.example.barkiko;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EntrarController {

    @FXML
    private Button buttonAtras;

    @FXML
    private Button buttonContinuar;

    @FXML
    private TextField txtContraseña;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Label welcomeText;
    ConexionClass conexionClass;

    public static String correo;
    public static String contraseña;

    public EntrarController() throws SQLException, IOException, ClassNotFoundException {
        conexionClass = new ConexionClass();
        conexionClass.conectar();
    }


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
        /**
        correo = txtCorreo.getText();
        contraseña = txtContraseña.getText();
        Usuario usuario = new Usuario(correo, contraseña);

        conexionClass.contraseñaCorrecta(usuario);**/

    }


    private boolean contraseñaCorrecta(){

        correo = txtCorreo.getText();
        contraseña = txtContraseña.getText();

        Usuario usuario = new Usuario(correo, contraseña);


        return true;

    }

}
