package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){}

    static private List<RegisterVoterDtoResponse> voters = new ArrayList<>();

    static public List<RegisterVoterDtoResponse> getVoters() {
        return voters;
    }

    static public ErrorServiceVoter insert(RegisterVoterDtoResponse registerVoterDtoResponse) {
        for (RegisterVoterDtoResponse voter : voters) {
            if (registerVoterDtoResponse.equals(voter))
                return ErrorServiceVoter.DUPLICATE_VOTER;
            if (registerVoterDtoResponse.getLogin().equals(voter.getLogin()))
                return ErrorServiceVoter.DUPLICATE_LOGIN;
        }
        voters.add(registerVoterDtoResponse);
        return ErrorServiceVoter.OK;
    }

    static public ErrorServiceVoter logout(String login){
        for (RegisterVoterDtoResponse registerVoterDtoResponse : voters){
            if (login.equals(registerVoterDtoResponse.getLogin())){
                if (!registerVoterDtoResponse.isActive())
                    return ErrorServiceVoter.ALREADY_DEACTIVATED;
                registerVoterDtoResponse.deactivated();
                return ErrorServiceVoter.OK;
            }
        }
        return ErrorServiceVoter.NOT_FOUND_LOGIN;
    }

    static public ErrorServiceVoter logging(String login, String password){
        for (RegisterVoterDtoResponse registerVoterDtoResponse : voters){
            if (login.equals(registerVoterDtoResponse.getLogin()) &&
                    password.equals(registerVoterDtoResponse.getPassword())){
                if (registerVoterDtoResponse.isActive())
                    return ErrorServiceVoter.ALREADY_ACTIVATED;
                registerVoterDtoResponse.activated();
                return ErrorServiceVoter.OK;
            }
        }
        return ErrorServiceVoter.LOGIN_OR_PASSWORD;
    }
}
