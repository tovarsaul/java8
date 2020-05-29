package com.udemy.highorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HighOrderFunctions implements SumarInterfaz{

    public static void main(String[] args) {
        HighOrderFunctions highOrderFunctions = new HighOrderFunctions();
        System.out.println(highOrderFunctions.suma(2,3 ));
        System.out.println(highOrderFunctions.apply(2,4 ));
        SumarInterfaz sumarInterfaz = Integer::sum;
        System.out.println(highOrderFunctions.sumnarHighOrderFunction(sumarInterfaz,2,5));

        highOrderFunctions.imprimirMayuscula(String::toUpperCase, "saul");
        List<Integer> numeros = Arrays.asList(6,23,-5,45,-56);
        BiFunction<List<Integer>, Predicate<Integer>,List<Integer>>filtrar;
        filtrar = (lista, predicado) -> lista.stream().filter(predicado).collect(Collectors.toList());
        System.out.println(filtrar.apply(numeros,e ->e>0));
        List<String> nombres = new ArrayList<>();
        nombres.add("Saul");
        nombres.add("nombreLargo");
        nombres.add("paco");
        highOrderFunctions.filtrar(nombres, System.out::println, 5);
    }
    public void filtrar(List<String> lista, Consumer<String> consumer, int maximoCaracteres){
        lista.stream().filter(logicaPredicado(maximoCaracteres)).forEach(consumer);
    }
    public Predicate<String> logicaPredicado(int maximoCaracteres){
        return e -> e.length()<maximoCaracteres;
    }

    public int suma(int a, int b){
        return a+b;
    }

    @Override
    public int apply(int a, int b) {
        return a+b;
    }
    public int sumnarHighOrderFunction(SumarInterfaz sumar, int a, int b){
        return sumar.apply(a,b);
    }

    public void imprimirMayuscula(Function<String, String> function, String nombre){
        System.out.println(function.apply(nombre));

    }
}
