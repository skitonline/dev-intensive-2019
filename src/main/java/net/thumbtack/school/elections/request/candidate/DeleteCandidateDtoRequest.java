package net.thumbtack.school.elections.request.candidate;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.roles.Voter;

public class DeleteCandidateDtoRequest {
    private boolean candidate;
    private String token;

    public boolean isCandidate() {
        return candidate;
    }

    public String getToken() {
        return token;
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
