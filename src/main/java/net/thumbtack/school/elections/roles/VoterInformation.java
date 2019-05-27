package net.thumbtack.school.elections.roles;

import java.util.HashSet;
import java.util.Set;

public class VoterInformation {
    private String name, surname, patronymic;
    private String street, number, room;
    private Set<String> program;

    public VoterInformation(String name, String surname, String patronymic, String street, String number, String room) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.number = number;
        this.room = room;
        program = new HashSet<>();
    }

    public Set<String> getProgram() {
        return program;
    }

    public void setProgram(Set<String> program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
