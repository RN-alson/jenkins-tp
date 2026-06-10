package com.app;

public class App {
    public static String saluer(String nom) {
        return "Bonjour, " + nom + " !";
    }

    public static void main(String[] args) {
        System.out.println(saluer("Jenkins CI/CD + Webhook"));
    }
}
