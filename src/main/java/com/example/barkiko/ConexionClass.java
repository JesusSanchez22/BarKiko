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

import static com.example.barkiko.EntrarController.contraseña;


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

    public void modificarEmpleado(Empleado empleado) throws SQLException {

        String sql = "UPDATE empleados SET nombre = ?, apellido1 = ?, apellido2 = ?, sueldo = ?, puesto = ? WHERE codigo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setInt(6, empleado.getCodigo());
        sentencia.setString(1, empleado.getNombre());
        sentencia.setString(2, empleado.getApellido1());
        sentencia.setString(3, empleado.getApellido2());
        sentencia.setDouble(4, empleado.getSueldo());
        sentencia.setString(5, empleado.getPuesto());

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

    public List<Producto> datosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();


        while (resultado.next()) {
            Producto producto1 = new Producto();
            producto1.setCodigo(resultado.getInt(1));
            producto1.setNombre(resultado.getString(2));
            producto1.setCantidadEnStock(resultado.getInt(3));
            producto1.setPrecioVenta(resultado.getDouble(4));
            producto1.setPrecioCompra(resultado.getDouble(5));


            productos.add(producto1);
        }

        return productos;
    }


    public void guardarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (codigo,nombre,cantidadEnStock,PrecioVenta,PrecioCompra) VALUES (?, ?, ?, ?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, String.valueOf(producto.getCodigo()));
        sentencia.setString(2, producto.getNombre());
        sentencia.setString(3, String.valueOf(producto.getCantidadEnStock()));
        sentencia.setString(4, String.valueOf(producto.getPrecioVenta()));
        sentencia.setString(5, String.valueOf(producto.getPrecioCompra()));

        sentencia.executeUpdate();
    }

    public void eliminarProducto(Producto producto) throws SQLException {

        String sql = "DELETE FROM productos WHERE codigo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, producto.getCodigo());
        sentencia.executeUpdate();

    }

    public void modificarProducto(Producto productoAntiguo, Producto productoNuevo){

        String sql = "UPDATE productos SET nombre = ?, cantidadEnStock = ?, PrecioVenta = ?, PrecioCompra = ? WHERE codigo = ?";

        try {

            PreparedStatement sentencia = conexion.prepareStatement(sql);

            sentencia.setInt(5, productoNuevo.getCodigo());
            sentencia.setString(1, productoNuevo.getNombre());
            sentencia.setInt(2, productoNuevo.getCantidadEnStock());
            sentencia.setDouble(3, productoNuevo.getPrecioVenta());
            sentencia.setDouble(4, productoNuevo.getPrecioCompra());

            sentencia.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void guardarUsuario(Usuario usuario) throws SQLException {

        String sql = "Insert into usuarios values(?,?,?,SHA(?));";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, usuario.getCorreo());
        sentencia.setString(2, usuario.getNombre());
        sentencia.setString(3, usuario.getApellido());
        sentencia.setString(4, usuario.getCorreo());


        sentencia.executeUpdate();

    }

    public void contraseñaCorrecta(Usuario usuario) throws SQLException {

        String sql = "Select contraseña from usuarios where correo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);

        sentencia.setString(1, usuario.getContraseña());
        sentencia.setString(1, usuario.getCorreo());


        System.out.println(sentencia.executeUpdate());

    }


    public boolean usuarioExiste() throws SQLException {

        String query = "Select * from usuarios where correo = ?";

        PreparedStatement sentencia = conexion.prepareStatement(query);

        sentencia.setString(1, contraseña);


        return true;
    }


    public ResultSet cargarDatosTabla() throws SQLException {

        String query = "SELECT * FROM empleados";

        PreparedStatement sentencia = conexion.prepareStatement(query);

        ResultSet rs = sentencia.executeQue ry(query);

        return rs;
    }


}
