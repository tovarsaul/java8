package com.udemy.lambda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String nombre;


    public static void referenciaAMetodoEstatico(){
        System.out.println("Probando referencia a metodo estatico");
    }

    public void referenciaAMetodoParticular(){
        System.out.println("Probando referencia a metodo de objeto particular");
    }

    public void mostrarNombre(){
        System.out.println(nombre);
    }
}
