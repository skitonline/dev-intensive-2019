package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.VoterDaoImpl;
import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public class ServiceVoter {
    private VoterDaoImpl voterDao = new VoterDaoImpl();

    public String registerVoter(String requestJsonString){
        Gson gson = new Gson();
        RegisterVoterDtoRequest registerVoterDtoReques =
                gson.fromJson(requestJsonString, RegisterVoterDtoRequest.class);
        if (!registerVoterDtoReques.validation())
            return "error";
        RegisterVoterDtoResponse registerVoterDtoResponse = new RegisterVoterDtoResponse();
        voterDao.insert(registerVoterDtoResponse);
        return registerVoterDtoResponse.getToken().toString();
    }
}
