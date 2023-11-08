package com.example.barkiko;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConexionClass {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();

        String host = "127.0.0.1";
        String port = "3306";
        String name = "bar_kiko";
        String username = "root";
        String password = "toor";


        try{
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));

            //System.out.println(properties.get("driver"));
            host=String.valueOf(properties.get("host"));
            port=String.valueOf(properties.get("port"));
            name=String.valueOf(properties.get("name"));
            username=String.valueOf(properties.get("username"));
            password=String.valueOf(properties.get("password"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                    username, password);


        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        }


    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void agregarEmpleado(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados (codigo, nombre, apellido1, apellido2, sueldo, puesto) VALUES (?, ?, ?, ?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, empleado.getCodigo());
        sentencia.setString(2, empleado.getNombre());
        sentencia.setString(3, empleado.getApellido1());
        sentencia.setString(4, empleado.getApellido2());
        sentencia.setDouble(5, empleado.getSueldo());
        sentencia.setString(6, empleado.getPuesto());
        sentencia.executeUpdate();
    }

    public void eliminarEmpleado(Empleado empleado) throws SQLException {
        String sql = "DELETE FROM empleados WHERE codigo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, empleado.getCodigo());
        sentencia.executeUpdate();
    }


    public ResultSet cargarDatosTabla() throws SQLException {
        String query = "SELECT * FROM empleados";

        PreparedStatement sentencia = conexion.prepareStatement(query);

        ResultSet rs = sentencia.executeQuery(query);

        return rs;
    }





}
