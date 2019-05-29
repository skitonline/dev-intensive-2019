package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.GetDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceGet;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsVoterDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;

public class ServiceGet {
    GetDaoImpl getDao = new GetDaoImpl();

    public String getCandidates(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        GetCandidatesDtoResponse getCandidatesDtoResponse = new GetCandidatesDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            getCandidatesDtoResponse.setError(ErrorServiceGet.PARSING.getErrorString());
            return gson.toJson(getCandidatesDtoResponse);
        }
        getCandidatesDtoResponse.setError(voterDtoRequest.validationGetCandidates().getErrorString());
        if (getCandidatesDtoResponse.getError().equals(ErrorServiceGet.OK.getErrorString())){
            getCandidatesDtoResponse = getDao.getCandidates(voterDtoRequest);
        }
        return gson.toJson(getCandidatesDtoResponse);
    }

    public String getProposalsRating(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        GetProposalsRatingDtoResponse getProposalsRatingDtoResponse = new GetProposalsRatingDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            getProposalsRatingDtoResponse.setError(ErrorServiceGet.PARSING.getErrorString());
            return gson.toJson(getProposalsRatingDtoResponse);
        }
        getProposalsRatingDtoResponse.setError(voterDtoRequest.validationGetProposalsRating().getErrorString());
        if (getProposalsRatingDtoResponse.getError().equals(ErrorServiceGet.OK.getErrorString())){
            getProposalsRatingDtoResponse = getDao.getProposalsRating(voterDtoRequest);
        }
        return gson.toJson(getProposalsRatingDtoResponse);
    }

    public String getProposalsVoter(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        GetProposalsVoterDtoResponse getProposalsCandidateDtoResponse =
                new GetProposalsVoterDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            getProposalsCandidateDtoResponse.setError(ErrorServiceGet.PARSING.getErrorString());
            return gson.toJson(getProposalsCandidateDtoResponse);
        }
        getProposalsCandidateDtoResponse.setError(voterDtoRequest.validationGetProposalsCandidate().getErrorString());
        if (getProposalsCandidateDtoResponse.getError().equals(ErrorServiceGet.OK.getErrorString())){
            getProposalsCandidateDtoResponse = getDao.getProposalsVoter(voterDtoRequest);
        }
        return gson.toJson(getProposalsCandidateDtoResponse);
    }
}
