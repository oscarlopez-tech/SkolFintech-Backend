package com.skol.fintech.domain;

public class Hogar {
    //Private int idHogar es el autoincrementado implementado en las tables Mysql en el cual mapea el id_Hogar planteado
    private int idHogar;
    private String nombreHogar;
    private Moneda monedaBase;
    
    //Este primer constructor esta hecho para los datos recibidos por primera vez y que no el sistema no posee un idHogar asignado por el momento
    public Hogar(String nombreHogar, Moneda monedaBase) {
        //En esta linea de codigo de defensa. el sistema pregunta si el nombre es nulo y el metodo .trim() elimina espacos en blanco fantasmas (como cuando solo se digitan barras espaciadoras
        //el metodo .isEmpty() valida si su longitud es cero
        if (nombreHogar == null || nombreHogar.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre del Hogar es obligatorio y no puede estar vacio");
            //Si la validacion anterior se cumnple el sistem frena en seco en flujo lanzando un error descriptivo. asi no registramos datos basura en la memoria del servidor
        }
        if (monedaBase == null) {
            throw new IllegalArgumentException("Error: Debe escoger una moneda base obligatoriamente");
        }
        this.nombreHogar = nombreHogar;
        this.monedaBase = monedaBase;
    }
    //Este constructor recibe tres parametros, incluyendo el id de labase de datos, en funciona para los usuarios que ya tienen cuenta y id asignado
    public Hogar(int idHogar, String nombreHogar, Moneda monedaBase){
        this(nombreHogar, monedaBase);
        this.idHogar = idHogar;
    }
    
    public int getIdHogar(){
        return idHogar;
    }
    
    public void setIdHogar(int idHogar){
        this.idHogar = idHogar;     
    }
    
    public String getNombreHogar(){
        return nombreHogar;
    }
    //Nota importante siempre hay que realizar doble validacion en el codigo, no solamente en la parte de el constructor, si no que hay que validar directamente en el seter y getter, que ya que si los dejamos libres
    //alguien puede modificar el nombre del hogar despues de haber sido creado, siempre debe pasar por una doble validacion de seguridad
    public void setNombreHogar(String nombreHogar){
        if(nombreHogar == null || nombreHogar.trim().isEmpty()){
            throw new IllegalArgumentException("Error: El nombre modificado no puede estar vacio.");
        }
        this.nombreHogar = nombreHogar;
    }
    public Moneda getMonedaBase(){
        return monedaBase;
    }
    
    public void setMonedaBase(Moneda monedaBase){
        if(monedaBase == null){
            throw new IllegalArgumentException("Error: La moneda Bse no puede ser nula");
        }
        this.monedaBase = monedaBase;
        
    }
    
    
    
    

}
