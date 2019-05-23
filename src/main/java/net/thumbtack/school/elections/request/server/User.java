package net.thumbtack.school.elections.request.server;

import net.thumbtack.school.elections.request.server.Login;

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
