package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Registry {

    public static Scanner sc = new Scanner(System.in);
    private final List<Person> registry;

    public Registry() {
        registry = new ArrayList<>();
    }

    public void createPerson() {
        String correctFormat = "[id] [firstName] [lastName]";
        System.out.println("Please enter the person details in given format:" + correctFormat + ".");
        String[] commandDetails = sc.nextLine().trim().split("\\s+");
        if (commandDetails.length == 3) {
            String validId = validateId(commandDetails[0]);
            Person alreadyRegistered = getPersonById(validId);
            if (validId != null && alreadyRegistered == null) {
                registry.add(new Person(validId, commandDetails[1], commandDetails[2]));
            } else if (alreadyRegistered != null){
                System.err.println("Person of given id already exists");
            }
        } else {
            System.err.println("Missing fields, correct format is " + correctFormat);
        }
    }

    public void deletePerson() {
        System.out.println("Please, enter an id of the person you want to delete.");
        Person personToDelete = getPersonById(validateId(sc.nextLine()));
        if (personToDelete != null) {
            registry.remove(personToDelete);
        } else {
            System.err.println("Can not delete - record not found.");
        }
    }

    public void findPerson() {
        System.out.println("Please, enter an id of the person you want to search for.");
        Person person = getPersonById(validateId(sc.nextLine()));
        if (person != null) {
            String age = (person.getAge() == 0) ? "Age unknown" : String.valueOf(person.getAge());
            System.out.format("%s %s %s %s\n",
                    person.getFirstName(), person.getLastName(),
                    age, person.getId());
        } else {
            System.err.println("Record not found.");
        }
    }

    public Person getPersonById(String id) {
        if (id != null) {
            for (Person p : registry) {
                if (id.equals(p.getId())) {
                    return p;
                }
            }
        }
        return null;
    }

    public String validateId(String id) {
        if (id != null && id.matches("\\d{2}(0[1-9]|1[0-2]|5[1-9]|6[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])/?\\d{4}")) {
            if (id.contains("/")) {
                return id.replace("/", "");
            }
            return id;
        }
        System.err.println("Id has to be in one of the following formats: YYMMDDXXXX or YYMMDD/XXXX");
        return null;
    }


    public List<Person> getRegistry() {
        return registry;
    }
}