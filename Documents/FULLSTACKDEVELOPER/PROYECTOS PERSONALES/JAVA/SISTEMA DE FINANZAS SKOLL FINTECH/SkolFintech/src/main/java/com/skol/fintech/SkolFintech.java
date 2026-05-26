package com.skol.fintech;
import com.skol.fintech.util.DBConnection;
import java.sql.Connection;


public class SkolFintech {

    public static void main(String[] args) {
        System.out.println("---- Iniciando Prueba de Conexion SkolFintech ----");
        //Con esta linea de codigo intentamos obtener la instancia unica. es decir la unica conexion con la base de datos
        DBConnection db = DBConnection.getInstance();
        //Con esta linea de codigo obtenemos el objeto conection real
        Connection connection = db.getConnection();
        
        if(connection  != null){
            System.out.println("RESULTADO: ¡La conexión se estableció correctamente!");
        } else{
            System.out.println("RESULTADO: La conexión falló. Revisa tus credenciales.");
        }
    }
}
