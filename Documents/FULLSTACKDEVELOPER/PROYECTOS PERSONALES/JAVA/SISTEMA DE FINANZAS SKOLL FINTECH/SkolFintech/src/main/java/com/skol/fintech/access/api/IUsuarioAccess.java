package com.skol.fintech.access.api;
import com.skol.fintech.domain.Usuario;
import java.util.List;  

public interface IUsuarioAccess {
    //Este metodo se encarga de insertar la informacion del usuario del paquete domain Usuario
    boolean insertar(Usuario usuario);
    //Buscar un usuario usando su ID unico
    Usuario buscarPorId (int idUsuario);
    
    Usuario buscarPorEmail (String email);
    
    List<Usuario> listarTodos(); 
    
}
