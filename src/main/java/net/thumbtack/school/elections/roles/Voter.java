package net.thumbtack.school.elections.roles;

import net.thumbtack.school.elections.exception.ActionError;
import net.thumbtack.school.elections.roles.validation.VoterValidation;

public class Voter {
    private String name, surname, patronymic;
    private String street, number, roomn;
    private String login, password;
    private boolean active;

    public Voter(String name, String surname, String patronymic,
                 String street, String number, String roomn,
                 String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.number = number;
        this.roomn = roomn;
        this.login = login;
        this.password = password;
        active = true;
        boolean validation = new VoterValidation(this).validation();
        if (!validation)
            try {
                throw new ActionError();
            } catch (ActionError actionError) {
                actionError.printStackTrace();
            }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getRoomn() {
        return roomn;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
