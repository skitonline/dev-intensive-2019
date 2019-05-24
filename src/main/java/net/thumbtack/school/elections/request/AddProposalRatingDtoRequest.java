package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceProposals;
import net.thumbtack.school.elections.roles.Voter;

public class AddProposalRatingDtoRequest {
    private String proposal;
    private String token;
    private int ratingProposal;

    public String getToken() {
        return token;
    }

    public String getProposal() {
        return proposal;
    }

    public int getRatingProposal() {
        return ratingProposal;
    }

    public ErrorServiceProposals validation(){
        if (token == null)
            return ErrorServiceProposals.NOW_LOGOUT;
        if (ratingProposal < 1 || ratingProposal > 5)
            return ErrorServiceProposals.WRONG_RATING;
        if (!DataBase.getPropsals().containsKey(proposal))
            return ErrorServiceProposals.PROPOSAL_NOT_FOUND;
        for (Voter voter : DataBase.getVoters()){
            if (token.equals(voter.getToken()))
                return ErrorServiceProposals.OK;
        }
        return ErrorServiceProposals.VOTER_NOT_FOUND;
    }
}
