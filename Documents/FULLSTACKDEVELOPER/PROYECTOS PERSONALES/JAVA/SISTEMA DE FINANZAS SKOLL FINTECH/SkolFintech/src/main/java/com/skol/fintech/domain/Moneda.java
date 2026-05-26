package com.skol.fintech.domain;

//al cambiar la palabra class por enum, Java entiende que este archivo no servira para crear objetos libres, si no que es un alista cerrada de opciones fijas
public enum Moneda {
    CRC("₡", "Colón Costarricense"),
    USD("$", "Dólar Estadounidense");

    //Definimos la variable que guardara el incono de la moneda. Al usar final, le aseguramos a la aplicacion que una vez compliado, nadie podra alterar el simbolo en memoria por seguridad
    private final String simbolo;
    //Igual para la variable nombreCompleto, se usa final para que el nombre de la variable no pueda ser alterado
    private final String nombreCompleto;

    //Este es el constructor de la calase enum, en java los constructores enum tienen prohibido ser publicos. Java se encarga de ejecutar este constructor automaticamente al iniciar la aplicacion para cerar los simpbolos
    //Nadie desde fuera puede crear un archivo new Moneda()
    private Moneda(String simbolo, String nombreCompleto) {
        this.simbolo = simbolo;
        this.nombreCompleto = nombreCompleto;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

}
