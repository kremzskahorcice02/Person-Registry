package org.example.dtos;

import org.example.models.Person;

public class PersonDetailsResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String age;

    public PersonDetailsResponse(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        int age = person.getAge();
        this.age = (age == 0) ? "Age unknown" : String.valueOf(age);
    }

    public PersonDetailsResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
