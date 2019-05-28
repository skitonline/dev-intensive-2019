package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.*;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;

import java.util.Map;

public class VoterDtoRequest extends Voter {

    public VoterDtoRequest(String name, String surname, String patronymic,
                                   String street, String number, String room,
                                   String login, String password) {
        super(name, surname, patronymic, street, number, room, login, password);
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

    public ErrorServiceVoter validationRegister(){
        if (Server.startElections)
            return ErrorServiceVoter.ELECTIONS_START;
        if (getName() == null || getName().isEmpty() ||
                getSurname() == null || getSurname().isEmpty() ||
                getStreet() == null || getStreet().isEmpty() ||
                getNumber() == null || getNumber().isEmpty())
            return ErrorServiceVoter.DATA;
        if (getLogin() == null || getLogin().length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        if (getPassword() == null || getPassword().length() < 6)
            return ErrorServiceVoter.PASSWORD_LENGTH;
        return ErrorServiceVoter.OK;
    }

    public ErrorServiceVoter validationLogout(){
        if (Server.startElections)
           return ErrorServiceVoter.ELECTIONS_START;
        if (getLogin() == null || getLogin().length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        return ErrorServiceVoter.OK;
    }

    public ErrorServiceVoter validationRestore(){
        ErrorServiceVoter validation = validationLogout();
        if (validation.equals(ErrorServiceVoter.OK)) {
            if (getPassword() == null || getPassword().length() < 6)
                return ErrorServiceVoter.PASSWORD_LENGTH;
            return ErrorServiceVoter.OK;
        }
        return validation;
    }

    public ErrorServiceCandidate validationAddCantidate(){
        if (Server.startElections)
            return ErrorServiceCandidate.ELECTIONS_START;
        if (realToken()){
            for (Voter voter : Server.dataBase.getVoters()){
                if (getTokenActionCandidate().equals(voter.getToken()))
                    return ErrorServiceCandidate.OK;
            }
            return ErrorServiceCandidate.WHOM_ADD_NOT_FOUND;
        }
        return ErrorServiceCandidate.WHO_ADD_NOT_FOUND;
    }

    public ErrorServiceCandidate validationAcceptAddCandidate(){
        if (Server.startElections)
            return ErrorServiceCandidate.ELECTIONS_START;
        if (realToken())
            return ErrorServiceCandidate.OK;
        return ErrorServiceCandidate.VOTER_NOT_FOUND;
    }

    public ErrorServiceCandidate validationDeleteCandidate(){
        return validationAcceptAddCandidate();
    }

    public ErrorServiceProposals validationAddProposal(){
        if (Server.startElections)
            return ErrorServiceProposals.ELECTIONS_START;
        if (realToken())
            return ErrorServiceProposals.OK;
        return ErrorServiceProposals.VOTER_NOT_FOUND;
    }

    public ErrorServiceProposals validationAddProposalRating(){
        if (Server.startElections)
            return ErrorServiceProposals.ELECTIONS_START;
        if (realToken()) {
            if (getRatingProposal() < 1 || getRatingProposal() > 5)
                return ErrorServiceProposals.WRONG_RATING;
            if (!DataBase.getPropsals().containsKey(getProposal()))
                return ErrorServiceProposals.PROPOSAL_NOT_FOUND;
            return ErrorServiceProposals.OK;
        }
        return ErrorServiceProposals.VOTER_NOT_FOUND;
    }

    public ErrorServiceProposals validationRemoveProposalRating(){
        return validationAddProposal();
    }

    public ErrorServiceProgram validationAddInProgram(){
        if (Server.startElections)
            return ErrorServiceProgram.ELECTIONS_START;
        if (realToken()) {
            for (Voter voter : DataBase.getVoters())
                if (getToken().equals(voter.getToken()) && !isCandidate())
                    return ErrorServiceProgram.NOT_CANDIDATE;
            if (Server.dataBase.getPropsals().get(getProposal()) == null)
                return ErrorServiceProgram.PORPOSAL_NOT_FOUND;
            return ErrorServiceProgram.OK;
        }
        return ErrorServiceProgram.VOTER_NOT_FOUND;
    }

    public ErrorServiceProgram validationRemoveFromProgram() {
        return validationAddInProgram();
    }

    public ErrorServiceElections validationVote(){
        if (!Server.startElections)
            return ErrorServiceElections.NOT_START;
        if (isVoted())
            return ErrorServiceElections.VOTED;
        if (realToken()){
            if (getToken().equals(getTokenActionCandidate()))
                return ErrorServiceElections.VOTE_YOURSELF;
            if (DataBase.getElection().keySet().contains(getTokenActionCandidate()))
                return ErrorServiceElections.OK;
            return ErrorServiceElections.CANDIDATE_NOT_FOUND;
        }
        return ErrorServiceElections.VOTER_NOT_FOUND;
    }
}