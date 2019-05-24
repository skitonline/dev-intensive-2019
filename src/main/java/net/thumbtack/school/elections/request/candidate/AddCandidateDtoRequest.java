package net.thumbtack.school.elections.request.candidate;

import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;

public class AddCandidateDtoRequest {
    private String token;
    private String tokenAddCandidate;

    public AddCandidateDtoRequest(String token, String tokenAddCandidate) {
        this.token = token;
        this.tokenAddCandidate = tokenAddCandidate;
    }

    public String getToken() {
        return token;
    }

    public String getTokenAddCandidate() {
        return tokenAddCandidate;
    }

    public ErrorServiceCandidate validation(){
        if (token == null)
            return ErrorServiceCandidate.WHO_ADD_NOT_FOUND;
        if (tokenAddCandidate == null)
            return ErrorServiceCandidate.WHOM_ADD_NOT_FOUND;
        boolean findToken = false, findTokenWhoExcute = false;
        for (Voter voter : Server.dataBase.getVoters()){
            if (token.equals(voter.getToken()))
                findToken = true;
            if (tokenAddCandidate.equals(voter.getToken()))
                findTokenWhoExcute = true;
            if (findToken && findTokenWhoExcute)
                break;
        }
        if (!findToken)
            return ErrorServiceCandidate.WHO_ADD_NOT_FOUND;
        if (!findTokenWhoExcute)
            return ErrorServiceCandidate.WHOM_ADD_NOT_FOUND;
        return ErrorServiceCandidate.OK;
    }
}
