package com.skol.fintech.access.impl;

import com.skol.fintech.access.api.IUsuarioAccess;
import com.skol.fintech.domain.Usuario;
import com.skol.fintech.domain.Hogar;
import com.skol.fintech.domain.Rol;
import com.skol.fintech.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAccessImpl implements IUsuarioAccess {

    @Override
    public boolean insertar(Usuario usuario) {
        // SQL adaptado a tus campos. Si en tu tabla agregaste la columna apellidos, 
        // recuerda sumarla en el INSERT y en el ps.setString correspondientemente.
        String sql = "INSERT INTO usuarios (nombre, email, password_hash, id_hogar, rol) VALUES (?,?,?,?,?)";

        Connection con = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPasswordHash());

            // Validación defensiva basada en tu diseño relacional
            if (usuario.getHogar() != null && usuario.getHogar().getIdHogar() > 0) {
                ps.setInt(4, usuario.getHogar().getIdHogar());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            // Convertimos el enum Rol a String para guardarlo en MySQL de forma segura
            ps.setString(5, usuario.getRol().name());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("[Access] Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Connection con = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[Access] Error al buscar por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Connection con = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[Access] Error al buscar por Email: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection con = DBConnection.getInstance().getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            System.err.println("[Access] Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        int idUsuario = rs.getInt("id_usuario");
        String nombre = rs.getString("nombre");
        
        // Manejo defensivo para la columna 'apellidos' por si no está en tu tabla DB actual
        String apellidos = "";
        try {
            apellidos = rs.getString("apellidos");
        } catch (SQLException e) {
            // Si la columna no existe en la base de datos, se mantiene vacía sin romper el flujo
            apellidos = "No registrado";
        }
        
        String email = rs.getString("email");
        String passwordHash = rs.getString("password_hash");
        
        // Conversión del String de la base de datos al Enum robusto 'Rol' que programaste
        Rol rolEnum = Rol.valueOf(rs.getString("rol").toUpperCase().trim());
        
        // Construimos el cascarón de Hogar respetando sus constructores defensivos
        int idHogar = rs.getInt("id_hogar");
        Hogar hogarAsociado = new Hogar(idHogar, "Hogar Auxiliar", null);

        // Instanciamos usando tu constructor exacto de 7 parámetros:
        // Usuario(nombre, apellidos, email, passwordHash, rol, hogar, idUsuario)
        Usuario u = new Usuario(nombre, apellidos, email, passwordHash, rolEnum, hogarAsociado, idUsuario);
        
        return u;
    }
}