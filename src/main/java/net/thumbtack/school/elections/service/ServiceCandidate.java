package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.request.candidate.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.AddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.DeleteCandidateDtoRequest;
import net.thumbtack.school.elections.response.candidate.AcceptAddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.AddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.DeleteCandidateDtoResponse;

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

    public String deleteCandidate(String requestJsonString){
        Gson gson = new Gson();
        DeleteCandidateDtoRequest deleteCandidateDtoRequest;
        DeleteCandidateDtoResponse deleteCandidateDtoResponse = new DeleteCandidateDtoResponse();
        try {
            deleteCandidateDtoRequest = gson.fromJson(requestJsonString, DeleteCandidateDtoRequest.class);
        } catch (JsonParseException e){
            deleteCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(deleteCandidateDtoResponse);
        }
        deleteCandidateDtoResponse.setError(deleteCandidateDtoRequest.validation().getErrorString());
        if (deleteCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            deleteCandidateDtoResponse.setError
                    (candidateDao.deleteCandidate(deleteCandidateDtoRequest).getErrorString());
        }
        return gson.toJson(deleteCandidateDtoResponse);
    }
}
