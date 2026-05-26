package com.skol.fintech.domain;


public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String email; 
    private String passwordHash;
    private Rol rol;
    private Hogar hogar; 
    
     
    public void validarDatos(String nombre, String email, String passwordHash, Rol rol, Hogar hogar){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar el Nombre");
        }
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("El correo electronico es obligatorio");
        }
        if(passwordHash == null || passwordHash.trim().isEmpty()){
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if(rol == null){
            throw new IllegalArgumentException("El usuario debe tener un rol asignado");
        }
        if (hogar == null){
            throw new IllegalArgumentException("El usuario debe pertenecer a un hogar obligatoriamente");
        }
    }
    
    public Usuario(String nombre, String apellidos, String email, String passwordHash, Rol rol, Hogar hogar){
        validarDatos(nombre,email,passwordHash,rol,hogar);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.passwordHash = passwordHash; 
        this.rol = rol;
        this.hogar = hogar;    
    }
    
    public Usuario(String nombre, String apellidos, String email, String passwordHash, Rol rol, Hogar hogar, int idUsuario){
        this(nombre,apellidos,email, passwordHash, rol,hogar);
        this.idUsuario = idUsuario;
    }
    
    public int getIdUsuario(){
        return idUsuario; 
    };
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar el Nombre");
        }
        this.nombre = nombre; 
    }
    public String getApellidos(){
        return apellidos;
    }
    
    public void setApellidos(String apellidos){
        this.apellidos = apellidos; 
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar el correo electronico");
        }
        this.email = email; 
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash){
        if(passwordHash == null || passwordHash.trim().isEmpty()){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar la contraseña del usuario");
        }
        this.passwordHash = passwordHash; 
    }
    
    public Rol getRol(){
        return rol;
    }
    
    public void setRol(Rol rol){
        if(rol == null){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar el rol del usuario");
        }
        this.rol = rol; 
    }
    public Hogar getHogar(){
        return hogar;
    }
    
    public void setHogar(Hogar hogar){
        if(hogar == null){
            throw new IllegalArgumentException("Error: Es Obligatorio ingresar campo hogar");
        }
        this.hogar = hogar; 
    }
       
}
