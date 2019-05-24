package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceProposals;
import net.thumbtack.school.elections.request.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.AddProposalRatingDtoRequest;
import net.thumbtack.school.elections.request.candidate.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.AddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.DeleteCandidateDtoRequest;
import net.thumbtack.school.elections.request.voter.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.request.voter.RestoreVoterDtoRequest;
import net.thumbtack.school.elections.roles.Voter;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.*;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){}

    static private List<Voter> voters = new ArrayList<>();
    static private Map<String, Map<String,Integer>> propsals;

    public static List<Voter> getVoters() {
        return voters;
    }

    public static Map<String, Map<String, Integer>> getPropsals() {
        return propsals;
    }

    public static void clear() {
        voters.clear();
    }

    static public ErroDataBase insert(Voter insertVoter) {
        for (Voter voter : voters) {
            if (insertVoter.equals(voter))
                return ErroDataBase.DUPLICATE_VOTER;
            if (insertVoter.getLogin().equals(voter.getLogin()))
                return ErroDataBase.DUPLICATE_LOGIN;
        }
        insertVoter.generateToken();
        voters.add(insertVoter);
        return ErroDataBase.OK;
    }

    static public ErroDataBase logout(LogoutVoterDtoRequest login){
        for (Voter voter : voters){
            if (login.getLogin().equals(voter.getLogin())) {
                if (voter.getToken() == null)
                    return ErroDataBase.NOW_LOGOUT;
                else {
                    voter.doNullToken();
                    voter.setCandidate(false);
                    return ErroDataBase.OK;
                }
            }
        }
        return ErroDataBase.VOTER_NOT_FOUND;
    }

    static public ErroDataBase restore(RestoreVoterDtoRequest user){
        for (Voter voter : voters){
            if (user.getLogin().equals(voter.getLogin()) &&
                    user.getPassword().equals(voter.getPassword())) {
                if (voter.getToken() == null) {
                    voter.generateToken();
                    return ErroDataBase.OK;
                }
                else
                    return ErroDataBase.NOW_ACTIVED;
            }
        }
        return ErroDataBase.LOGIN_OR_PASSWORD;
    }

    static public ErroDataBase addCandidate(AddCandidateDtoRequest addCandidateDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters){
            if (addCandidateDtoRequest.getTokenAddCandidate().equals(voter.getToken())){
                if (voter.isCandidate())
                    result = ErroDataBase.NOW_CANDIDATE;
                else {
                    if (addCandidateDtoRequest.getToken().equals(addCandidateDtoRequest.getTokenAddCandidate())) {
                        voter.setCandidate(true);
                    }
                    else {
                        voter.setRequestAddCandidate(true);
                        result = ErroDataBase.WAIT_ACCEPT_ADD_CANDIDATE;
                    }
                }
                break;
            }
        }
        return result;
    }

    static public ErroDataBase acceptAddCandidate(AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters)
            if (acceptAddCandidateDtoRequest.getToken().equals(voter.getToken())){
                if (!voter.getRequestAddCandidate())
                    result = ErroDataBase.NOT_ADD_CANDIDATE;
                else
                    voter.setCandidate(true);
                break;
            }
        return result;
    }

    static public ErroDataBase deleteCandidate(DeleteCandidateDtoRequest deleteCandidateDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters)
            if (deleteCandidateDtoRequest.getToken().equals(voter.getToken())){
                if (!voter.isCandidate())
                    result = ErroDataBase.NOT_CANDIDATE;
                else
                    voter.setCandidate(false);
                break;
            }
        return result;
    }

    static public ErroDataBase addProposal(AddProposalDtoRequest addProposalDtoRequest) {
        if (propsals == null)
            propsals = new HashMap<>();
        if (propsals.get(addProposalDtoRequest.getProposal()) == null){
            propsals.put(addProposalDtoRequest.getProposal(), new HashMap<>());
            propsals.get(addProposalDtoRequest.getProposal()).put(addProposalDtoRequest.getToken(), 5);
            for (Voter voter : voters)
                if (addProposalDtoRequest.getToken().equals(voter.getToken()))
                    voter.addPorposal(addProposalDtoRequest.getProposal());
            return ErroDataBase.OK;
        }
        else
            return ErroDataBase.NOW_ADD_PROPOSAL;
    }

    static public ErroDataBase addProposalRating(AddProposalRatingDtoRequest addProposalRatingDtoRequest) {
        propsals.get(addProposalRatingDtoRequest.getProposal()).
                put(addProposalRatingDtoRequest.getToken(), addProposalRatingDtoRequest.getRatingProposal());
        return ErroDataBase.OK;
    }
}
