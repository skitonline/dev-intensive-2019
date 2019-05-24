package net.thumbtack.school.elections.request.voter;

import net.thumbtack.school.elections.error.ErrorServiceVoter;

public class LogoutVoterDtoRequest {
    private String login;

    public String getLogin() {
        return login;
    }

    public ErrorServiceVoter validation(){
        if (login == null || login.length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        return ErrorServiceVoter.OK;
    }
}
