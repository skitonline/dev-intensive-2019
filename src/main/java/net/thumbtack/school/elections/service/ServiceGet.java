package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.GetDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceGet;
import net.thumbtack.school.elections.request.GetDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;

public class ServiceGet {
    GetDaoImpl getDao = new GetDaoImpl();

    public String getCandidates(String requestJsonString){
        Gson gson = new Gson();
        GetDtoRequest getDtoRequest;
        GetCandidatesDtoResponse getCandidatesDtoResponse = new GetCandidatesDtoResponse();
        try {
            getDtoRequest = gson.fromJson(requestJsonString, GetDtoRequest.class);
        } catch (JsonParseException e){
            getCandidatesDtoResponse.setError(ErrorServiceGet.PARSING.getErrorString());
            return gson.toJson(getCandidatesDtoResponse);
        }
        getCandidatesDtoResponse.setError(getDtoRequest.validationGetCandidates().getErrorString());
        if (getCandidatesDtoResponse.getError().equals(ErrorServiceGet.OK.getErrorString())){
            getCandidatesDtoResponse = getDao.getCandidates(getDtoRequest);
        }
        return gson.toJson(getCandidatesDtoResponse);
    }
}
