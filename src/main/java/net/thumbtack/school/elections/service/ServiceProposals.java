package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.ProposalsDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.error.ErrorServiceProposals;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.proposal.AddProposalDtoResponse;
import net.thumbtack.school.elections.response.proposal.AddProposalRatingDtoResponse;
import net.thumbtack.school.elections.response.proposal.RemoveProposalRatingDtoResponse;

public class ServiceProposals {
    ProposalsDaoImpl proposalsDao = new ProposalsDaoImpl();

    public String addProposal(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        AddProposalDtoResponse addProposalDtoResponse = new AddProposalDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            addProposalDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addProposalDtoResponse);
        }
        addProposalDtoResponse.setError(voterDtoRequest.validationAddProposal().getErrorString());
        if (addProposalDtoResponse.getError().equals(ErrorServiceProposals.OK.getErrorString())){
            addProposalDtoResponse.setError(proposalsDao.addProposal(voterDtoRequest).getErrorString());
        }
        return gson.toJson(addProposalDtoResponse);
    }

    public String addProposalRating(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        AddProposalRatingDtoResponse addProposalRatingDtoResponse = new AddProposalRatingDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            addProposalRatingDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addProposalRatingDtoResponse);
        }
        addProposalRatingDtoResponse.setError(voterDtoRequest.validationAddProposalRating().getErrorString());
        if (addProposalRatingDtoResponse.getError().equals(ErrorServiceProposals.OK.getErrorString())){
            addProposalRatingDtoResponse.setError(
                    proposalsDao.addProposalRating(voterDtoRequest).getErrorString());
        }
        return gson.toJson(addProposalRatingDtoResponse);
    }

    public String removeProposalRating(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        RemoveProposalRatingDtoResponse removeProposalRatingDtoResponse = new RemoveProposalRatingDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            removeProposalRatingDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(removeProposalRatingDtoResponse);
        }
        removeProposalRatingDtoResponse.setError(voterDtoRequest.
                validationRemoveProposalRating().getErrorString());
        if (removeProposalRatingDtoResponse.getError().equals(ErrorServiceProposals.OK.getErrorString())){
            removeProposalRatingDtoResponse.setError(
                    proposalsDao.removeProposalRating(voterDtoRequest).getErrorString());
        }
        return gson.toJson(removeProposalRatingDtoResponse);
    }
}
