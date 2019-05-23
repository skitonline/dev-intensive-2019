package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.request.RestoreVoterDtoRequest;
import net.thumbtack.school.elections.request.server.User;
import net.thumbtack.school.elections.response.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.response.RestoreVoterDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.request.server.Login;

public class ServiceVoter {
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString){
        Gson gson = new Gson();
        RegisterVoterDtoRequest registerVoterDtoRequest;
        RegisterVoterDtoResponse registerVoterDtoResponse = new RegisterVoterDtoResponse();
        try {
            registerVoterDtoRequest = gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        } catch (JsonParseException e){
            registerVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(registerVoterDtoResponse);
        }
        registerVoterDtoResponse.setError(registerVoterDtoRequest.validation().getErrorString());
        if (registerVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            String jsonVoter = gson.toJson(registerVoterDtoRequest);
            Voter insertVoter = gson.fromJson(jsonVoter, Voter.class);
            registerVoterDtoResponse.setError(voterDao.insert(insertVoter).getErrorString());
            if (registerVoterDtoResponse.getError().equals(ErroDataBase.OK.toString()))
                registerVoterDtoResponse.setToken(DataBase.getTokens().get(DataBase.getTokens().size() - 1));
        }
        return gson.toJson(registerVoterDtoResponse);
    }

    public String logoutVoter(String requestJsonString){
        Gson gson = new Gson();
        LogoutVoterDtoRequest logoutVoterDtoRequest;
        LogoutVoterDtoResponse logoutVoterDtoResponse = new LogoutVoterDtoResponse();
        try {
            logoutVoterDtoRequest = gson.fromJson(requestJsonString, LogoutVoterDtoRequest.class);
        } catch (JsonParseException e){
            logoutVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(logoutVoterDtoResponse);
        }
        logoutVoterDtoResponse.setError(logoutVoterDtoRequest.validation().getErrorString());
        if (logoutVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            String jsonVoter = gson.toJson(logoutVoterDtoRequest);
            Login logoutVoter = gson.fromJson(jsonVoter, Login.class);
            logoutVoterDtoResponse.setError(voterDao.logout(logoutVoter).getErrorString());
        }
        return gson.toJson(logoutVoterDtoResponse);
    }

    public String restoreAccess(String requestJsonString){
        Gson gson = new Gson();
        RestoreVoterDtoRequest restoreVoterDtoRequest;
        RestoreVoterDtoResponse restoreVoterDtoResponse = new RestoreVoterDtoResponse();
        try {
            restoreVoterDtoRequest = gson.fromJson(requestJsonString, RestoreVoterDtoRequest.class);
        } catch (JsonParseException e){
            restoreVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(restoreVoterDtoResponse);
        }
        restoreVoterDtoResponse.setError(restoreVoterDtoRequest.validation().getErrorString());
        if (restoreVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            String jsonVoter = gson.toJson(restoreVoterDtoRequest);
            User user = gson.fromJson(jsonVoter, User.class);
            restoreVoterDtoResponse.setError(voterDao.restore(user).getErrorString());
        }
        return gson.toJson(restoreVoterDtoResponse);
    }
}
