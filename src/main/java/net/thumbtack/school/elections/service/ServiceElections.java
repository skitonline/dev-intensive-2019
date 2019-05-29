package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.daoimpl.ElectionsDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceElections;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.elections.ResultElectionsDtoResponse;
import net.thumbtack.school.elections.response.elections.VoteDtoResponse;

public class ServiceElections {
    ElectionsDaoImpl electionsDao = new ElectionsDaoImpl();

    public String vote(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        VoteDtoResponse voteDtoResponse = new VoteDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            voteDtoResponse.setError(ErrorServiceElections.PARSING.getErrorString());
            return gson.toJson(voteDtoResponse);
        }
        DataBase.startElections();
        voteDtoResponse.setError(voterDtoRequest.validationVote().getErrorString());
        if (voteDtoResponse.getError().equals(ErrorServiceElections.OK.getErrorString())){
            voteDtoResponse = electionsDao.vote(voterDtoRequest);
        }
        return gson.toJson(voteDtoResponse);
    }

    public String resultElections(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        ResultElectionsDtoResponse resultElectionsDtoResponse = new ResultElectionsDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            resultElectionsDtoResponse.setError(ErrorServiceElections.PARSING.getErrorString());
            return gson.toJson(resultElectionsDtoResponse);
        }
        resultElectionsDtoResponse.setError(voterDtoRequest.validationResultElections().getErrorString());
        if (resultElectionsDtoResponse.getError().equals(ErrorServiceElections.OK.getErrorString())){
            resultElectionsDtoResponse = electionsDao.resultElections(voterDtoRequest);
        }
        return gson.toJson(resultElectionsDtoResponse);
    }
}
