package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.error.ErrorServiceVoter;

public class RegisterVoterDtoRequest {
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

    public ErrorServiceVoter validation(){
        if (name == null || name.isEmpty() ||
                surname == null || surname.isEmpty() ||
                street == null || street.isEmpty() ||
                number == null || number.isEmpty())
            return ErrorServiceVoter.DATA;
        if (login == null || login.length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        if (password == null || password.length() < 6)
            return ErrorServiceVoter.PASSWORD_LENGTH;
        return ErrorServiceVoter.OK;
    }
}