package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public class ServiceVoter {
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString){
        Gson gson = new Gson();
        RegisterVoterDtoRequest registerVoterDtoRequest =
                gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        if (!registerVoterDtoRequest.validation())
            return "error";
        RegisterVoterDtoResponse registerVoterDtoResponse =
                gson.fromJson(requestJsonString, RegisterVoterDtoResponse.class);
        if (!voterDao.insert(registerVoterDtoResponse))
            return "error";
        registerVoterDtoResponse.generateToken();
        return registerVoterDtoResponse.getToken().toString();
    }
}
