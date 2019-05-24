package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.request.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.AddCandidateDtoRequest;
import net.thumbtack.school.elections.response.AcceptAddCandidateDtoResponse;
import net.thumbtack.school.elections.response.AddCandidateDtoResponse;

public class ServiceCandidate {
    CandidateDaoImpl candidateDao = new CandidateDaoImpl();

    public String addCandidate(String requestJsonString){
        Gson gson = new Gson();
        AddCandidateDtoRequest addCandidateDtoRequest;
        AddCandidateDtoResponse addCandidateDtoResponse = new AddCandidateDtoResponse();
        try {
            addCandidateDtoRequest = gson.fromJson(requestJsonString, AddCandidateDtoRequest.class);
        } catch (JsonParseException e){
            addCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addCandidateDtoResponse);
        }
        addCandidateDtoResponse.setError(addCandidateDtoRequest.validation().getErrorString());
        if (addCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            addCandidateDtoResponse.setError(candidateDao.addCandidate(addCandidateDtoRequest).getErrorString());
        }
        return gson.toJson(addCandidateDtoResponse);
    }

    public String acceptAddCandidate(String requestJsonString){
        Gson gson = new Gson();
        AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest;
        AcceptAddCandidateDtoResponse acceptAddCandidateDtoResponse = new AcceptAddCandidateDtoResponse();
        try {
            acceptAddCandidateDtoRequest = gson.fromJson(requestJsonString, AcceptAddCandidateDtoRequest.class);
        } catch (JsonParseException e){
            acceptAddCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(acceptAddCandidateDtoResponse);
        }
        acceptAddCandidateDtoResponse.setError(acceptAddCandidateDtoRequest.validation().getErrorString());
        if (acceptAddCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            acceptAddCandidateDtoResponse.setError
                    (candidateDao.acceptAddCandidate(acceptAddCandidateDtoRequest).getErrorString());
        }
        return gson.toJson(acceptAddCandidateDtoResponse);
    }
}
