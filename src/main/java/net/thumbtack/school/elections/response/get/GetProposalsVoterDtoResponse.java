package net.thumbtack.school.elections.response.get;

import java.util.HashSet;
import java.util.Set;

public class GetProposalsVoterDtoResponse {
    Set<String> proposals = new HashSet<>();

    public Set<String> getProposals() {
        return proposals;
    }

    public void setProposals(Set<String> proposals) {
        this.proposals = proposals;
    }

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
