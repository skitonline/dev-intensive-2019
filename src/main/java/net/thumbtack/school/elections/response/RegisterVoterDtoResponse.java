package net.thumbtack.school.elections.response;

import java.util.Objects;
import java.util.UUID;

public class RegisterVoterDtoResponse {
    private String name, surname, patronymic;
    private String street, number, room;
    private String login, password;
    private UUID token;
    private boolean active;

    public RegisterVoterDtoResponse(String name, String surname, String patronymic,
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

    public String getLogin() {
        return login;
    }

    public void activated(){
        token = UUID.randomUUID();
        active = true;
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterVoterDtoResponse that = (RegisterVoterDtoResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(street, that.street) &&
                Objects.equals(number, that.number) &&
                Objects.equals(room, that.room);
    }
}
