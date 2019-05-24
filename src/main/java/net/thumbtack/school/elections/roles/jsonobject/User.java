package net.thumbtack.school.elections.roles.jsonobject;

public class User extends Login {
    private String password;

    public User(String login, String password) {
        super(login);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
