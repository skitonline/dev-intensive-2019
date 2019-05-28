package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErrorServiceGet;
import net.thumbtack.school.elections.roles.Voter;

public class GetDtoRequest {
    private String token;

    public String getToken() {
        return token;
    }

    private boolean realToken(){
        if (getToken() == null)
            return false;
        for (Voter voter : DataBase.getVoters()){
            if (getToken().equals(voter.getToken()))
                return true;
        }
        return false;
    }

    public ErrorServiceGet validationGetCandidates() {
        if (realToken())
            return ErrorServiceGet.OK;
        return ErrorServiceGet.VOTER_NOT_FOUND;
    }

    public ErrorServiceGet validationGetProposalsRating(){
        return validationGetCandidates();
    }
}
