package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceGet;
import net.thumbtack.school.elections.request.GetDtoRequest;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsVoterDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.VoterInformation;

import java.util.*;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){}

    static private List<Voter> voters = new ArrayList<>();
    static private Map<String, Map<String,Integer>> propsals;
    static private Map<String, Integer> election = new HashMap<>();

    public static List<Voter> getVoters() {
        return voters;
    }

    public static Map<String, Map<String, Integer>> getPropsals() {
        return propsals;
    }

    public static void setPropsals(Map<String, Map<String, Integer>> propsals) {
        DataBase.propsals = propsals;
    }

    public static Map<String, Integer> getElection() {
        return election;
    }

    public static void setElection(Map<String, Integer> election) {
        DataBase.election = election;
    }

    public static void clear() {
        voters.clear();
        propsals = new HashMap<>();
    }

    static public ErroDataBase insertVoter(VoterDtoRequest voterDtoRequest) {
        for (Voter voter : voters) {
            if (voterDtoRequest.equals(voter))
                return ErroDataBase.DUPLICATE_VOTER;
            if (voterDtoRequest.getLogin().equals(voter.getLogin()))
                return ErroDataBase.DUPLICATE_LOGIN;
        }
        voterDtoRequest.generateToken();
        voters.add(voterDtoRequest);
        return ErroDataBase.OK;
    }

    static public ErroDataBase logoutVoter(VoterDtoRequest voterDtoRequest){
        for (int i = 0; i < voters.size(); i++){
            if (voterDtoRequest.getLogin().equals(voters.get(i).getLogin())) {
                if (voters.get(i).getToken() == null)
                    return ErroDataBase.NOW_LOGOUT;
                else {
                    String token = voters.get(i).getToken();
                    voters.get(i).doNullToken();
                    voters.get(i).setCandidate(false);
                    for(Map.Entry<String, Map<String,Integer>> item : DataBase.getPropsals().entrySet())
                        item.getValue().keySet().removeIf(el -> el.equals(token));
                    return ErroDataBase.OK;
                }
            }
        }
        return ErroDataBase.VOTER_NOT_FOUND;
    }

    static public ErroDataBase restoreVoter(VoterDtoRequest voterDtoRequest){
        for (Voter voter : voters){
            if (voterDtoRequest.getLogin().equals(voter.getLogin()) &&
                    voterDtoRequest.getPassword().equals(voter.getPassword())) {
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

    static public ErroDataBase addCandidate(VoterDtoRequest voterDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters){
            if (voterDtoRequest.getTokenActionCandidate().equals(voter.getToken())){
                if (voter.isCandidate())
                    result = ErroDataBase.NOW_CANDIDATE;
                else {
                    if (voterDtoRequest.getToken().equals(voterDtoRequest.getTokenActionCandidate())) {
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

    static public ErroDataBase acceptAddCandidate(VoterDtoRequest voterDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters)
            if (voterDtoRequest.getToken().equals(voter.getToken())){
                if (!voter.getRequestAddCandidate())
                    result = ErroDataBase.NOT_ADD_CANDIDATE;
                else
                    voter.setCandidate(true);
                break;
            }
        return result;
    }

    static public ErroDataBase deleteCandidate(VoterDtoRequest voterDtoRequest) {
        ErroDataBase result = ErroDataBase.OK;
        for (Voter voter : voters)
            if (voterDtoRequest.getToken().equals(voter.getToken())){
                if (!voter.isCandidate())
                    result = ErroDataBase.NOT_CANDIDATE;
                else
                    voter.setCandidate(false);
                break;
            }
        return result;
    }

    static public ErroDataBase addProposal(VoterDtoRequest voterDtoRequest) {
        if (propsals == null)
            propsals = new HashMap<>();
        for (Voter voter : voters)
            if (voterDtoRequest.getToken().equals(voter.getToken()))
                voter.addPorposal(voterDtoRequest.getProposal(), true);
        if (propsals.get(voterDtoRequest.getProposal()) == null){
            propsals.put(voterDtoRequest.getProposal(), new HashMap<>());
            propsals.get(voterDtoRequest.getProposal()).put(voterDtoRequest.getToken(), 5);
            return ErroDataBase.OK;
        }
        else
            return ErroDataBase.NOW_ADD_PROPOSAL;
    }

    static public ErroDataBase addProposalRating(VoterDtoRequest voterDtoRequest) {
        propsals.get(voterDtoRequest.getProposal()).
                put(voterDtoRequest.getToken(), voterDtoRequest.getRatingProposal());
        return ErroDataBase.OK;
    }


    static public ErroDataBase removeProposalRating(VoterDtoRequest voterDtoRequest) {
        for(Map.Entry<String, Map<String,Integer>> item : DataBase.getPropsals().entrySet())
            if (item.getKey().equals(voterDtoRequest.getProposal()))
                if (item.getValue().remove(voterDtoRequest.getToken()) == null)
                    return ErroDataBase.PROPOSAL_NOT_FOUND;
        return ErroDataBase.OK;
    }

    static public ErroDataBase addInProgram(VoterDtoRequest voterDtoRequest) {
        for (Voter voter : voters) {
            if (voterDtoRequest.getToken().equals(voter.getToken())) {
                if (voter.getProgram().get(voterDtoRequest.getProposal()) == null)
                    voter.addPorposal(voterDtoRequest.getProposal(), false);
                else
                    return ErroDataBase.NOW_ADD_PROPOSAL;
            }
        }
        return ErroDataBase.OK;
    }

    static public ErroDataBase removeFromProgram(VoterDtoRequest voterDtoRequest) {
        for (Voter voter : voters) {
            if (voterDtoRequest.getToken().equals(voter.getToken())) {
                if (voter.getProgram().get(voterDtoRequest.getProposal()))
                    return ErroDataBase.YOUR_PROPOSAL;
                else
                    voter.getProgram().remove(voterDtoRequest.getProposal());
            }
        }
        return ErroDataBase.OK;
    }

    static public GetCandidatesDtoResponse getCandidates(GetDtoRequest getDtoRequest) {
        GetCandidatesDtoResponse getCandidatesDtoResponse = new GetCandidatesDtoResponse();
        for (Voter voter : voters)
            if (voter.isCandidate()) {
                VoterInformation candidate = new VoterInformation
                        (voter.getName(), voter.getSurname(), voter.getPatronymic(),
                                voter.getStreet(), voter.getNumber(), voter.getRoom());
                candidate.setProgram(voter.getProgram().keySet());
                getCandidatesDtoResponse.getCantidates().add(candidate);
            }
        getCandidatesDtoResponse.setError(ErrorServiceGet.OK.getErrorString());
        return getCandidatesDtoResponse;
    }

    static public GetProposalsRatingDtoResponse getProposalsRating(GetDtoRequest getDtoRequest) {
        GetProposalsRatingDtoResponse getProposalsRatingDtoResponse =
                new GetProposalsRatingDtoResponse();
        for(Map.Entry<String, Map<String, Integer>> el : propsals.entrySet()){
            Collection<Integer> rating = el.getValue().values();
            int sum = 0;
            for (int item : rating)
                sum += item;
            getProposalsRatingDtoResponse.getRatings().put(el.getKey(), sum / (double)rating.size());
        }
        getProposalsRatingDtoResponse.sortByValue();
        getProposalsRatingDtoResponse.setError(ErrorServiceGet.OK.getErrorString());
        return getProposalsRatingDtoResponse;
    }

    static public GetProposalsVoterDtoResponse getProposalsVoter(GetDtoRequest getDtoRequest) {
        GetProposalsVoterDtoResponse getProposalsVoterDtoResponse =
                new GetProposalsVoterDtoResponse();
        for (Voter voter : voters)
            if (getDtoRequest.getTokenActionCandidate().equals(voter.getToken())){
                for(Map.Entry<String, Boolean> el : voter.getProgram().entrySet()){
                    if (el.getValue())
                        getProposalsVoterDtoResponse.getProposals().add(el.getKey());
                }
            }
        getProposalsVoterDtoResponse.setError(ErrorServiceGet.OK.getErrorString());
        return getProposalsVoterDtoResponse;
    }

    static public void startElections() {
        election = new HashMap<>();
        for (Voter voter : voters)
            if (voter.isCandidate() && voter.getProgram() != null && !voter.getProgram().isEmpty())
                election.put(voter.getToken(), 0);
        election.put(null, 0);
    }

    static public ErroDataBase vote(VoterDtoRequest voterDtoRequest) {
        for (Voter voter : voters)
            if (voterDtoRequest.getToken().equals(voter.getToken()))
                voter.setVoted(true);
        int rating = election.get(voterDtoRequest.getTokenActionCandidate());
        election.put(voterDtoRequest.getTokenActionCandidate(), rating + 1);
        return ErroDataBase.OK;
    }

    static public String resultElections() {
        String result = null; int maxValue = 0;
        for(Map.Entry<String, Integer> el : election.entrySet())
            if (el.getValue() > maxValue){
                result = el.getKey();
                maxValue = el.getValue();
            }
        if (result == null)
            result = "Выборы не состоялись";
        return result;
    }
}
