
package com.skol.fintech.logic;
import com.skol.fintech.access.api.IUsuarioAccess;
import com.skol.fintech.access.impl.UsuarioAccessImpl;
import com.skol.fintech.domain.Usuario;
import java.util.List;



public class UsuarioLogic {
    private final IUsuarioAccess usuarioAccess;
    
    public UsuarioLogic(){
        this.usuarioAccess = new UsuarioAccessImpl();
    }
    public String registrarUsuario(Usuario nuevoUsuario){
        //estas dos siguientes reglas son para validar los campos obligatorios
        if (nuevoUsuario.getNombre() == null || nuevoUsuario.getNombre().trim().isEmpty()) {
            return "Error: El nombre es obligatorio.";
        }
        if (nuevoUsuario.getEmail() == null || nuevoUsuario.getEmail().trim().isEmpty()){
            return "Error: El correo electronico es obligatorio. "; 
        }
        //la regla evita correos duplicados en el sistema
        Usuario usuarioExistente = usuarioAccess.buscarPorEmail(nuevoUsuario.getEmail());
        if (usuarioExistente != null){
            return "Error: Ya existe un usuario registrado con el correo " + nuevoUsuario.getEmail();      
        }
        //Si pasa los filtros, se manda a guardar
        boolean exito = usuarioAccess.insertar(nuevoUsuario);
        if(exito){
            return "Exito : Usuario " + nuevoUsuario.getNombre() + "registrado correctamente. ";
        }else {
            return "Error: No se pudo guardar el usuario en el sistema. ";
        }

    }
    
    public List<Usuario> obtenerTodosLosUsuarios(){
        return usuarioAccess.listarTodos();
    }
    
}
