package net.thumbtack.school.elections.request.voter;

import net.thumbtack.school.elections.error.ErrorServiceVoter;

public class RestoreVoterDtoRequest {
    private String login, password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public ErrorServiceVoter validation(){
        if (login == null || login.length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        if (password == null || password.length() < 6)
            return ErrorServiceVoter.PASSWORD_LENGTH;
        return ErrorServiceVoter.OK;
    }
}
