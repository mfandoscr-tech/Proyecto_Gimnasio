package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import modelo.Usuario;

public class UsuarioDAO {

    private ConexionBD conexionBD;

    public UsuarioDAO() {
        conexionBD = new ConexionBD();
    }

    // INSERTAR USUARIO
    public boolean insertarUsuario(Usuario usuario) {
        boolean insertado = false;
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, telefono) VALUES (?, ?, ?, ?)";

        Connection conexion = conexionBD.conectar();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getTelefono());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                insertado = true;
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        }

        return insertado;
    }

    // BUSCAR USUARIO POR ID
    public Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        Connection conexion = conexionBD.conectar();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefono(rs.getString("telefono"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }

        return usuario;
    }

    // LISTAR TODOS LOS USUARIOS (EXTRA PARA SUBIR NOTA)
    public ResultSet obtenerTodosLosUsuarios() {
        String sql = "SELECT * FROM usuarios";
        Connection conexion = conexionBD.conectar();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }

        return null;
    }
}