package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.voter.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.response.voter.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.response.voter.RestoreVoterDtoResponse;

public class ServiceVoter {
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        RegisterVoterDtoResponse registerVoterDtoResponse = new RegisterVoterDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            registerVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(registerVoterDtoResponse);
        }
        registerVoterDtoResponse.setError(voterDtoRequest.validationRegister().getErrorString());
        if (registerVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            registerVoterDtoResponse.setError(voterDao.insertVoter(voterDtoRequest).getErrorString());
            if (registerVoterDtoResponse.getError().equals(ErroDataBase.OK.toString()))
                registerVoterDtoResponse.setToken(
                        DataBase.getVoters().get(DataBase.getVoters().size() - 1).getToken());
        }
        return gson.toJson(registerVoterDtoResponse);
    }

    public String logoutVoter(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        LogoutVoterDtoResponse logoutVoterDtoResponse = new LogoutVoterDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            logoutVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(logoutVoterDtoResponse);
        }
        logoutVoterDtoResponse.setError(voterDtoRequest.validationLogout().getErrorString());
        if (logoutVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            logoutVoterDtoResponse.setError(voterDao.logoutVoter(voterDtoRequest).getErrorString());
        }
        return gson.toJson(logoutVoterDtoResponse);
    }

    public String restoreVoter(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        RestoreVoterDtoResponse restoreVoterDtoResponse = new RestoreVoterDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            restoreVoterDtoResponse.setError(ErrorServiceVoter.PARSING.getErrorString());
            return gson.toJson(restoreVoterDtoResponse);
        }
        restoreVoterDtoResponse.setError(voterDtoRequest.validationRestore().getErrorString());
        if (restoreVoterDtoResponse.getError().equals(ErrorServiceVoter.OK.getErrorString())){
            restoreVoterDtoResponse.setError(voterDao.restoreVoter(voterDtoRequest).getErrorString());
        }
        return gson.toJson(restoreVoterDtoResponse);
    }
}
