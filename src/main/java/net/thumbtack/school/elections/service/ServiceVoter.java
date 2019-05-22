package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;

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
        if (requestJsonString.length() < 3)
            return ErrorServiceVoter.WRONG_LOGIN.getErrorString();
        for (RegisterVoterDtoResponse registerVoterDtoResponse : Server.dataBase.getVoters()){
            if (requestJsonString.equals(registerVoterDtoResponse.getLogin())){
                if (!registerVoterDtoResponse.isActive())
                    return ErrorServiceVoter.ALREADY_DEACTIVITED.getErrorString();
                registerVoterDtoResponse.deactivated();
                return ErrorServiceVoter.OK.getErrorString();
            }
        }
        return ErrorServiceVoter.NOT_FOUND_LOGIN.getErrorString();
    }

    public String loginVoter(String requestJsonString){
        return null;
    }
}
