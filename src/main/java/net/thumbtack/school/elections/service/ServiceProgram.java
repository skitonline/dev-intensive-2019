package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import net.thumbtack.school.elections.daoimpl.ProgramDaoImpl;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.error.ErrorServiceProgram;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.program.AddInProgramDtoResponse;
import net.thumbtack.school.elections.response.program.RemoveFromProgramDtoResponse;

public class ServiceProgram {
    ProgramDaoImpl programDao = new ProgramDaoImpl();

    public String addInProgram(String requestJsonString){
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        AddInProgramDtoResponse addInProgramDtoResponse = new AddInProgramDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            addInProgramDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(addInProgramDtoResponse);
        }
        addInProgramDtoResponse.setError(voterDtoRequest.validationAddInProgram().getErrorString());
        if (addInProgramDtoResponse.getError().equals(ErrorServiceProgram.OK.getErrorString())){
            addInProgramDtoResponse.setError(programDao.addInProgram(voterDtoRequest).getErrorString());
        }
        return gson.toJson(addInProgramDtoResponse);
    }

    public String removeFromProgram(String requestJsonString) {
        Gson gson = new Gson();
        VoterDtoRequest voterDtoRequest;
        RemoveFromProgramDtoResponse removeFromProgramDtoResponse = new RemoveFromProgramDtoResponse();
        try {
            voterDtoRequest = gson.fromJson(requestJsonString, VoterDtoRequest.class);
        } catch (JsonParseException e){
            removeFromProgramDtoResponse.setError(ErrorServiceCandidate.PARSING.getErrorString());
            return gson.toJson(removeFromProgramDtoResponse);
        }
        removeFromProgramDtoResponse.setError(voterDtoRequest.validationRemoveFromProgram().getErrorString());
        if (removeFromProgramDtoResponse.getError().equals(ErrorServiceProgram.OK.getErrorString())){
            removeFromProgramDtoResponse.setError(programDao.removeFromProgram(voterDtoRequest).getErrorString());
        }
        return gson.toJson(removeFromProgramDtoResponse);
    }
}
