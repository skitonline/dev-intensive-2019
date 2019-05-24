package net.thumbtack.school.elections.request.proposal;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.roles.Voter;

public class RemoveProposalRatingDtoRequest {
    private String proposal;
    private String token;

    public String getToken() {
        return token;
    }

    public String getProposal() {
        return proposal;
    }

    public ErroDataBase validation(){
        if (token == null)
            return ErroDataBase.NOW_LOGOUT;
        for (Voter voter : DataBase.getVoters()){
            if (token.equals(voter.getToken()))
                return ErroDataBase.OK;
        }
        return ErroDataBase.VOTER_NOT_FOUND;
    }
}
