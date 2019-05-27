package net.thumbtack.school.elections.response.get;

import net.thumbtack.school.elections.roles.VoterInformation;

import java.util.ArrayList;
import java.util.List;

public class GetCandidatesDtoResponse {
    private List<VoterInformation> cantidates;

    public GetCandidatesDtoResponse() {
        cantidates = new ArrayList<>();
    }

    public List<VoterInformation> getCantidates() {
        return cantidates;
    }

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
