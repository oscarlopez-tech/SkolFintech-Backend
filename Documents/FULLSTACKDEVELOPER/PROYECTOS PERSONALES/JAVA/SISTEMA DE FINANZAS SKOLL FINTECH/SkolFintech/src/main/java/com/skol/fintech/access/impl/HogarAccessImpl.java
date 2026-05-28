package com.skol.fintech.access.impl;
import com.skol.fintech.access.api.IHogarAccess;
import com.skol.fintech.domain.Hogar;
import com.skol.fintech.domain.Moneda;
import com.skol.fintech.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HogarAccessImpl implements IHogarAccess {
        
    @Override
    public boolean insertar(Hogar hogar){
        String sql = "INSERT INTO hogares (nombre_hogar) VALUES (?)";
        
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, hogar.getNombreHogar());
            
           
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        
        }catch (SQLException e){
            System.out.println("[Access] Error al Insertar nombre Hogar: " + e.getMessage());
        }
        return false;
    
    }
    
    @Override
    public Hogar buscarPorId(int idHogar){
        String sql = "SELECT * FROM hogares WHERE id_hogar = ?";
        
        Connection con = DBConnection.getInstance().getConnection();
        
        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, idHogar);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return mapearHogares(rs);
                }
            }
            
                }catch (SQLException e){
                    System.err.println("[Access] Error al buscar por ID: " + e.getMessage());
                }
                return null;
            }
     
    @Override
    public List<Hogar> listarTodos(){
        List<Hogar> lista = new ArrayList<>();
        String sql = "SELECT * FROM hogares";
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearHogares(rs));
            }
                
        
        }catch (SQLException e) {
            System.err.println("[Access] Error al listar Hogares: " + e.getMessage());
        }
        return lista;
    }
    
    
    private Hogar mapearHogares(ResultSet rs) throws SQLException {
        int idHogar = rs.getInt("id_hogar");
        String nombreHogar = rs.getString("nombre_hogar");
        String monedaStr = rs.getString("moneda_base");
        
        Moneda monedaEnum = Moneda.CRC; 
        
        try {
            if(monedaStr != null && !monedaStr.trim().isEmpty()){
                monedaEnum = Moneda.valueOf(monedaStr.toUpperCase().trim()); 
            }
        }catch (IllegalArgumentException e){
            System.err.println("[Access] Moneda Invalida en BD " + monedaStr + ",  Usando CRC por defecto. ");       
        }
        return new Hogar(idHogar, nombreHogar, monedaEnum);
    
    
    
    }
}

