package com.skol.fintech.logic;
import com.skol.fintech.access.api.IHogarAccess;
import com.skol.fintech.access.impl.HogarAccessImpl;
import com.skol.fintech.domain.Hogar;
import java.util.List;



public class HogarLogic {
    private final IHogarAccess HogarAccess;
    
    public HogarLogic(){
        this.HogarAccess = new HogarAccessImpl();
    }
    public String registrarHogar(Hogar hogar){
        if(hogar == null || hogar.getNombreHogar() == null || hogar.getNombreHogar().trim().isEmpty()){
            return "Error: El nombre de el parametro Hogar es Obligatorio";
        }
        boolean exito = HogarAccess.insertar(hogar);
        
        if(exito){
            return "Exito : Hogar " + hogar.getNombreHogar() + " registrado correctamente. ";
        
        }else{
            return "Error: No se pudo guardar el Hogar en el sistema. ";
        }
    }
    public Hogar buscarPorId(int idHogar){
        if(idHogar <= 0){
            System.out.println("[Logic] ID invalido proporcionado(" + idHogar + "). Retornando null. ");
            return null;
        }
           return HogarAccess.buscarPorId(idHogar);
    }
    public List<Hogar> listarTodos(){
        return HogarAccess.listarTodos();
    }
    
    
    
}
