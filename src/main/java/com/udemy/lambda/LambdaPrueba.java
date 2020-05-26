package com.udemy.lambda;

import com.udemy.lambda.model.User;
import com.udemy.lambda.service.IUser;
import com.udemy.lambda.service.Trabajo;
import com.udemy.lambda.service.TrabajoString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaPrueba {

    public static void main(String[] args) {
	// write your code here
        Trabajo trabajo = new Trabajo(){
            public void action() {
                User.referenciaAMetodoEstatico();
            }
        };

        Trabajo trabajoL = () -> User.referenciaAMetodoEstatico();
        Trabajo trabajoMR = User::referenciaAMetodoEstatico;
        trabajoMR.action();

        User user = new User("Saul");

        Trabajo trabajoL2 = () -> user.referenciaAMetodoParticular();
        Trabajo trabajoMR2 = user :: referenciaAMetodoParticular;
        trabajoMR2.action();

        TrabajoString trabajoString = (palabra) -> palabra.toUpperCase();
        TrabajoString trabajoStringRM = String::toUpperCase;
        System.out.println(trabajoStringRM.action("saul"));

        List<User> users = new ArrayList();
        users = Arrays.asList(new User ("saul"),new User ("sara"),new User ("cholo"),new User ("mica"));
//        users.forEach(nombre -> nombre.mostrarNombre());
        users.forEach(User::mostrarNombre);

        IUser user1 = new IUser() {
            @Override
            public User crear(String nombre) {
                return new User(nombre);
            }
        };
        IUser user2 = (nombre -> new User(nombre));
        IUser user3 = User::new;
        user3.crear("Saul");
    }
}
