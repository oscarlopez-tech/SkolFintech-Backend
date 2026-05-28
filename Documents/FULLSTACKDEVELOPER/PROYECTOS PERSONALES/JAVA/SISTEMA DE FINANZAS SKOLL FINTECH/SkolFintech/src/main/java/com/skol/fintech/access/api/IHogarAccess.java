package com.skol.fintech.access.api;
import com.skol.fintech.domain.Hogar;
import java.util.List; 

public interface IHogarAccess {
    
    boolean insertar(Hogar hogar);
    
    Hogar buscarPorId (int idHogar);
    
    List<Hogar> listarTodos();
}
