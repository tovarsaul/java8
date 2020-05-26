package com.udemy.optional;

import com.udemy.optional.exception.OptionalException;

import java.util.Optional;

public class OptionalPrueba {

    public static void main(String args[]) {
//        OptionalPrueba.probarOptiona("saul");
//        orElseOptional(null);
//        orElseOptional("Saul");
//        orElseThrow("Saul");
//        orElseThrow(null);
        isPresent("saul");
        isPresent(null);
    }

    public static void probarOptiona(String nombre) {
        System.out.println(nombre.length());
    }

    public static void crearOptional() {
        Optional<String> optional = Optional.empty();
        optional.get();
    }

    public static void orElseOptional(String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
//        Optional<String> optional1 = Optional.of(nombre);

        String nombreOfNullable = optional.orElse("empty");
//        String nombreOf = optional1.orElse("vacio");
        System.out.println(nombreOfNullable);
//        System.out.println(nombreOf);
    }

    public static void orElseThrow(String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
        optional.orElseThrow(OptionalException::new);
        String nombre1 = optional.get();
        System.out.println(nombre1);
    }

    public static void isPresent(String nombre) {
        Optional<String> optional = Optional.ofNullable(nombre);
        System.out.println(optional.isPresent());
    }
}
