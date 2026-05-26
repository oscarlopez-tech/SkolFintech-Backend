package com.skol.fintech.domain;


public enum Rol {
    ADMIN ("Administrador del Hogar"),
    MIEMBRO ("Miembro Familiar");
    
     private final String descripcion;
     
    private Rol(String descripcion){
             this.descripcion = descripcion;
         }
    public String getDescripcion() {
        return descripcion;
    }
}
