package org.example;

import org.example.models.Person;
import org.example.models.Registry;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@SpringBootApplication
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        new SpringApplicationBuilder(Main.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
        Registry registry = new Registry();
        String commands = """
                To perform a specific operation use one of following commands

                create             Create new record
                delete             Delete a record
                search             Find a record
                help               List available commands
                """;

        System.out.println("Welcome to the Person Registry App!\n" + commands);

        boolean keepAlive = true;
        while (keepAlive) {
            String operation = sc.nextLine();
            switch (operation) {
                case "help" -> System.out.println(commands);
                case "create" -> registry.createPerson();
                case "delete" -> registry.deletePerson();
                case "search" -> registry.findPerson();
                default -> System.err.println("Invalid command. See 'help'.");
            }
            if (!shouldContinue()) {
                keepAlive = false;
            }
        }
    }

    public static boolean shouldContinue() {
        Boolean shouldContinue = null;
        while (shouldContinue == null) {
            System.out.println("Do you want to continue? yes / no");
            String answer = sc.nextLine();
            if (answer.equals("yes")) {
                shouldContinue = true;
                System.out.println("available commands: create | delete | search | help");
            } else if (answer.equals("no")) {
                shouldContinue = false;
            } else {
                System.err.println("'" + answer + "' is not valid argument.");
            }
        }
        return shouldContinue;
    }
}