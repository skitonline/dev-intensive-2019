package net.thumbtack.school.elections.roles;

public class Voter {
    private String name, surname, patronymic;
    private String street, number, room;
    private String login, password;
    private boolean active;

    public Voter(String name, String surname, String patronymic,
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
        active = true;
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

    public String getRoom() {
        return room;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
