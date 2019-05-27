package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.candidate.AcceptAddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.AddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.DeleteCandidateDtoResponse;

public class ServiceCandidate {
    CandidateDaoImpl candidateDao = new CandidateDaoImpl();

    public String addCandidate(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        AddCandidateDtoResponse addCandidateDtoResponse = new AddCandidateDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            addCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addCandidateDtoResponse);
        }
        addCandidateDtoResponse.setError(voterDtoRequest.validationAddCantidate().getErrorString());
        if (addCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            addCandidateDtoResponse.setError(candidateDao.addCandidate(voterDtoRequest).getErrorString());
        }
        return gson.toJson(addCandidateDtoResponse);
    }

    public String acceptAddCandidate(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest acceptAddCandidateDtoRequest;
        AcceptAddCandidateDtoResponse acceptAddCandidateDtoResponse = new AcceptAddCandidateDtoResponse();
        try {
            acceptAddCandidateDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            acceptAddCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(acceptAddCandidateDtoResponse);
        }
        acceptAddCandidateDtoResponse.setError(acceptAddCandidateDtoRequest.
                validationAcceptAddCandidate().getErrorString());
        if (acceptAddCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            acceptAddCandidateDtoResponse.setError
                    (candidateDao.acceptAddCandidate(acceptAddCandidateDtoRequest).getErrorString());
        }
        return gson.toJson(acceptAddCandidateDtoResponse);
    }

    public String deleteCandidate(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        DeleteCandidateDtoResponse deleteCandidateDtoResponse = new DeleteCandidateDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            deleteCandidateDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(deleteCandidateDtoResponse);
        }
        deleteCandidateDtoResponse.setError(voterDtoRequest.
                validationDeleteCandidate().getErrorString());
        if (deleteCandidateDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            deleteCandidateDtoResponse.setError
                    (candidateDao.deleteCandidate(voterDtoRequest).getErrorString());
        }
        return gson.toJson(deleteCandidateDtoResponse);
    }
}
