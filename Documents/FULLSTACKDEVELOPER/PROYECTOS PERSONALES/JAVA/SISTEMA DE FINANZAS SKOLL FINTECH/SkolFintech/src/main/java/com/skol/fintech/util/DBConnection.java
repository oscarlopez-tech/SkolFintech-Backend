package com.skol.fintech.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    //Paso 1 declaramos las variables utilizando final para indicar que la variable no cambiara en la aplicacion
    //Consta de 3 variables  La variable UEL la cual es la direccion Gps del arquivo MySQL es como la direccion GPS del archivo, nos indica donde esta la base de datos y como conectarse
    private static final String URL = "jdbc:mysql://localhost:3306/skol_fintech_db";
    //En la variable User indicamos el usuario que ya configuramos previamente en el sql y en la variable password indicamos la contraseña ya anteriormente configurada
    static final String USER = "root";
    static final String PASSWORD = "271902";
    
    //Estas variables son el corazon del patron sigleton, ya que estas variables guardan el estado de la aplicacion
    //Static, significa qu eesta variable es unica para toda la clase y en instancia guardamos la "llave maestra": si ya exite una, todos usan esta misma
    static DBConnection instancia;
    //Esta es la herramienta real, Es el canal abierto que transporta los datos
    //entre java y MySQL se usara en los metoos siguientes para hacer consultas
    Connection connection;
    
    //Aca se crea el constructor de la aplicacion que transporta el la direccion, usuario y contraseña para ingresar a la base de datos y esta es la que inicializa la conecxion
    private DBConnection(){
        try{
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexión exitosa a SkolFintech DB");
        }catch(SQLException e){
            System.err.println("Error al conectar:" + e.getMessage());
        }
   
    }
    //El metodo get instance es el que nos solicita o consulta en el sistema si ya existe la coneccion a la base de datos creada, si no es así esta funcion la crea una sola vez
    //Si la funcion ya existe, simplemente le entrega la que ya tenemos guardada. esto nos permite ahorrar masivamente recursos.
    public static DBConnection getInstance(){
        if (instancia == null){
            instancia = new DBConnection();
        }
        return instancia;
        
    }
    
    //Este es un metodo de acceso a la base de datos, una vez ya tenemos la coneccion a la base de datos o la instancia a la Db Connection 
    //Este metodo es el que nos brinda el objeto Connection real para que podamos escribir los SELECT, INSERT O UPDATE en las siguientes fases del proyecto
    
    public Connection getConnection(){
        return connection;
    }

}
