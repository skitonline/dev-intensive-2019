package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public class ServiceVoter {
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString){
        Gson gson = new Gson();
        RegisterVoterDtoRequest registerVoterDtoRequest =
                gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        ErrorServiceVoter validation = registerVoterDtoRequest.validation();
        if (validation != ErrorServiceVoter.OK)
            return validation.getErrorString();
        RegisterVoterDtoResponse registerVoterDtoResponse =
                gson.fromJson(requestJsonString, RegisterVoterDtoResponse.class);
        ErrorServiceVoter insert = voterDao.insert(registerVoterDtoResponse);
        if (insert != ErrorServiceVoter.OK)
            return insert.getErrorString();
        registerVoterDtoResponse.activated();
        return registerVoterDtoResponse.getToken().toString();
    }

    public String logoutVoter(String requestJsonString){
        if (requestJsonString == null || requestJsonString.length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH.getErrorString();
        ErrorServiceVoter deactivated = voterDao.logout(requestJsonString);
        return deactivated.getErrorString();
    }

    public String loginVoter(String requestJsonString){
        Gson gson = new Gson();
        String[] loginPassword = gson.fromJson(requestJsonString, String[].class);
        if (loginPassword.length != 2)
            return ErrorServiceVoter.LOGGING_LENGTH.getErrorString();
        ErrorServiceVoter logging = voterDao.logging(loginPassword[0], loginPassword[1]);
        return logging.getErrorString();
    }
}