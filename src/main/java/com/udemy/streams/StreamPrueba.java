package com.udemy.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamPrueba {

    private static List<User> users;

    public static void main(String[] args) {
        System.out.println("*****************for each*****************");
        setUpUser();
        users.forEach(user -> user.setNombre(user.getNombre() + " apellido"));
        imprimirlista();
        System.out.println("*****************map y collector*********************");
        List<String> listaString = users.stream().map(User::getNombre).collect(Collectors.toList());
        listaString.forEach(System.out::println);
        System.out.println("*****************filter****************");
        setUpUser();
        List<User> listaFiltrada = users.stream().filter(user -> user.getId() > 2).collect(Collectors.toList());
        listaFiltrada.forEach(System.out::println);

        System.out.println("*****************find fist****************");
        setUpUser();
        User userFindFirst = users.stream()
            .filter(user -> user.getNombre().equalsIgnoreCase("saul"))
            .findFirst()
            .orElse(null);
        System.out.println(userFindFirst);
        System.out.println("*****************flap map****************");
        List<List<String>> nombresVariasListas = new ArrayList<>(
            Arrays.asList(
                new ArrayList<>(Arrays.asList("1", "2", "3")),
                new ArrayList<>(Arrays.asList("4", "5"))
            ));
        List<String> nombreLista = nombresVariasListas.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        nombreLista.forEach(System.out::println);
        System.out.println("*****************peek****************");
        // peek es como foreach pero no es final, puedes ejecutar otras ordenes despues
        setUpUser();
        List<User> userPeek = users.stream()
            .peek(user -> user.setNombre(user.getNombre() + " Apellido"))
            .collect(Collectors.toList());
        userPeek.forEach(System.out::println);
        System.out.println("*****************count****************");
        setUpUser();
        long numeroFlitrado = users.stream().filter(user -> user.getId() < 3).count();
        System.out.println(numeroFlitrado);

        System.out.println("*****************Skip y limit****************");
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        Arrays.stream(abc).skip(2).limit(4).forEach(System.out::println);

        System.out.println("*****************Sorted****************");
        setUpUser();
        users.stream()
            .filter(user -> user.getNombre().startsWith("s"))
            .sorted(Comparator.comparing(User::getId).reversed())
            .forEach(System.out::println);
        System.out.println("*****************Min y max****************");
        setUpUser();
        User userMin = users.stream()
            .min(Comparator.comparing(User::getId))
            .orElse(null);
        System.out.println(userMin);

        User userMax = users.stream()
            .max(Comparator.comparing(User::getId))
            .orElse(null);
        System.out.println(userMax);

        System.out.println("++++++++++++++++++++++++++++++++++DISTINCT*************************");
        String[] abc1 = {"a","b","c","d","e","f","a","g","b"};
        List<String> abcFilter = Arrays.stream(abc1)
            .distinct().collect(Collectors.toList());
        abcFilter.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++All match, any match nonematch*************************");
        List<Integer> listaNumeros = Arrays.asList(100,300,900,5000);
        boolean allMAtch = listaNumeros.stream().allMatch(e -> e >301);
        System.out.println("allmatch :: " + allMAtch);
        boolean anyMAtch = listaNumeros.stream().anyMatch(e -> e >301);
        System.out.println("anyMAtch " + anyMAtch);
        boolean noneMatch = listaNumeros.stream().noneMatch(e -> e >30001);
        System.out.println("noneMatch :: " + noneMatch);
        System.out.println("++++++++++++++++++++++++++++++++++sum average range*************************");
        setUpUser();
        double result = users.stream()
            .mapToInt(User::getId)
            .average()
            .orElse(0);
        System.out.println("Average :: " + result);

        result = users.stream()
            .mapToInt(User::getId)
            .sum();
        System.out.println("Sum :: " + result);
        System.out.println("range :: " + IntStream.range(0,100).sum());

        System.out.println("++++++++++++++++++++++++++++++++++reduce*************************");
        setUpUser();
        int suma = users.stream()
            .map(User::getId)
            .reduce(0,Integer::sum);
        System.out.println("reduce :: " + suma);
        System.out.println("++++++++++++++++++++++++++++++++++Join*************************");
        setUpUser();
        String names = users.stream()
            .map(User::getNombre)
            .collect(Collectors.joining(" - "));
        System.out.println("join :: "+ names);
        System.out.println("++++++++++++++++++++++++++++++++++To set*************************");
        setUpUser();
        Set<String> setNames = users.stream().map(User::getNombre)
            .collect(Collectors.toSet());
        setNames.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++Summarizing double*************************");
        setUpUser();
        DoubleSummaryStatistics statistics = users.stream().collect(Collectors.summarizingDouble(User::getId));
        System.out.println(statistics);
        statistics = users.stream()
            .mapToDouble(User::getId)
            .summaryStatistics();
        System.out.println(statistics);
        System.out.println("++++++++++++++++++++++++++++++++++partition by*************************");
        setUpUser();
        List<Integer> numeros = Arrays.asList(1,4,7,5,64,36,7,86,8,5,3,45);
        Map<Boolean, List<Integer>> esMayor = numeros.stream().collect(Collectors.partitioningBy(e -> e>10));
        esMayor.get(true).stream().forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++Grouping by*************************");
        setUpUser();
        Map<Character, List<User>> alfabetivco = users.stream().collect(Collectors.groupingBy( user -> (user.getNombre().charAt(0))));
        alfabetivco.get('s').forEach(user -> System.out.println(user.getNombre()));
        System.out.println("++++++++++++++++++++++++++++++++++Mapping*************************");
        setUpUser();
        List<String> personas = users.stream().map(User::getNombre).collect(Collectors.toList());
        personas.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++Parallel stream*************************");
        setUpUser();
        long tiempo1 = System.currentTimeMillis();
        users.stream().map(User::getNombre).collect(Collectors.toList()).forEach(StreamPrueba::convertirAMayusculas);
        long tiempo2 =System.currentTimeMillis();
        System.out.println("tiempo :: " + (tiempo2 - tiempo1));
        tiempo1 = System.currentTimeMillis();
        users.stream().map(User::getNombre).collect(Collectors.toList()).parallelStream().forEach(StreamPrueba::convertirAMayusculas);
        tiempo2 =System.currentTimeMillis();
        System.out.println("tiempo paralelo :: " + (tiempo2 - tiempo1));
    }

    private static String convertirAMayusculas(String nombre){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return nombre.toUpperCase();
    }

    private static void setUpUser() {
        users = new ArrayList<>();
        users = Arrays.asList(
            new User(1, "saul"),
            new User(2, "sara"),
            new User(3, "cholo"),
            new User(4, "mica"),
            new User(5, "saul"),
            new User(6, "sara"));
    }

    private static void imprimirlista() {
        users.forEach(System.out::println);
    }
}
