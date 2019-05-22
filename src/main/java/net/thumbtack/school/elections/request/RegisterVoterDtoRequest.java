package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.heplinginterfaces.CheckEmpty;

public class RegisterVoterDtoRequest implements CheckEmpty {
    private String name, surname, patronymic;
    private String street, number, room;
    private String login, password;

    public RegisterVoterDtoRequest(String name, String surname, String patronymic,
                 String street, String number, String room,
                 String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.number = number;
        this.room = room;
        this.login = login;
        this.password = password;
    }

    public boolean validation(){
        return notContainsEmpty(name, surname, street, number, login, password);
    }
}