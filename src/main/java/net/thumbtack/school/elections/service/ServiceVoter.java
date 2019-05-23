package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.roles.Voter;

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
}
