package com.example.barkiko;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ConexionClass {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
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

    public List<Empleado> obtenerEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Empleado empleado = new Empleado();
            empleado.setCodigo(resultado.getInt(1));
            empleado.setNombre(resultado.getString(2));
            empleado.setApellido1(resultado.getString(3));
            empleado.setApellido2(resultado.getString(4));
            empleado.setSueldo(resultado.getDouble(5));
            empleado.setPuesto(resultado.getString(6));

            empleados.add(empleado);
        }

        return empleados;
    }



}
