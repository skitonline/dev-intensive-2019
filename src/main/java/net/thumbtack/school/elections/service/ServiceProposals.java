package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.ProposalsDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.request.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.AddProposalRatingDtoRequest;
import net.thumbtack.school.elections.response.AddProposalDtoResponse;
import net.thumbtack.school.elections.response.AddProposalRatingDtoResponse;

public class ServiceProposals {
    ProposalsDaoImpl proposalsDao = new ProposalsDaoImpl();

    public String addProposals(String requestJsonString){
        Gson gson = new Gson();
        AddProposalDtoRequest addProposalDtoRequest;
        AddProposalDtoResponse addProposalDtoResponse = new AddProposalDtoResponse();
        try {
            addProposalDtoRequest = gson.fromJson(requestJsonString, AddProposalDtoRequest.class);
        } catch (JsonParseException e){
            addProposalDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addProposalDtoResponse);
        }
        addProposalDtoResponse.setError(addProposalDtoRequest.validation().getErrorString());
        if (addProposalDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            addProposalDtoResponse.setError(proposalsDao.addProposal(addProposalDtoRequest).getErrorString());
        }
        return gson.toJson(addProposalDtoResponse);
    }

    public String addProposalRating(String requestJsonString){
        Gson gson = new Gson();
        AddProposalRatingDtoRequest addProposalRatingDtoRequest;
        AddProposalRatingDtoResponse addProposalRatingDtoResponse = new AddProposalRatingDtoResponse();
        try {
            addProposalRatingDtoRequest = gson.fromJson(requestJsonString, AddProposalRatingDtoRequest.class);
        } catch (JsonParseException e){
            addProposalRatingDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addProposalRatingDtoResponse);
        }
        addProposalRatingDtoResponse.setError(addProposalRatingDtoRequest.validation().getErrorString());
        if (addProposalRatingDtoResponse.getError().equals(ErrorServiceCandidate.OK.getErrorString())){
            addProposalRatingDtoResponse.setError(
                    proposalsDao.addProposalRating(addProposalRatingDtoRequest).getErrorString());
        }
        return gson.toJson(addProposalRatingDtoResponse);
    }
}