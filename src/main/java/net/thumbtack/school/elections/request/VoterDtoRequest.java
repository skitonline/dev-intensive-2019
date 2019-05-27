package net.thumbtack.school.elections.request;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.error.ErrorServiceProgram;
import net.thumbtack.school.elections.error.ErrorServiceProposals;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;

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
        if (getLogin() == null || getLogin().length() < 3)
            return ErrorServiceVoter.LOGIN_LENGTH;
        return ErrorServiceVoter.OK;
    }

    public ErrorServiceVoter validationRestore(){
        validationLogout();
        if (getPassword() == null || getPassword().length() < 6)
            return ErrorServiceVoter.PASSWORD_LENGTH;
        return ErrorServiceVoter.OK;
    }

    public ErrorServiceCandidate validationAddCantidate(){
        if (realToken()){
            for (Voter voter : Server.dataBase.getVoters()){
                if (getTokenAddCandidate().equals(voter.getToken()))
                    return ErrorServiceCandidate.OK;
            }
            return ErrorServiceCandidate.WHOM_ADD_NOT_FOUND;
        }
        return ErrorServiceCandidate.WHO_ADD_NOT_FOUND;
    }

    public ErrorServiceCandidate validationAcceptAddCandidate(){
        if (realToken())
            return ErrorServiceCandidate.OK;
        return ErrorServiceCandidate.VOTER_NOT_FOUND;
    }

    public ErrorServiceCandidate validationDeleteCandidate(){
        return validationAcceptAddCandidate();
    }

    public ErrorServiceProposals validationAddProposal(){
        if (realToken())
            return ErrorServiceProposals.OK;
        return ErrorServiceProposals.VOTER_NOT_FOUND;
    }

    public ErrorServiceProposals validationAddProposalRating(){
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
        if (realToken()) {
            if (Server.dataBase.getPropsals().get(getProposal()) == null)
                return ErrorServiceProgram.PORPOSAL_NOT_FOUND;
            return ErrorServiceProgram.OK;
        }
        return ErrorServiceProgram.VOTER_NOT_FOUND;
    }

    public ErrorServiceProgram validationRemoveFromProgram() {
        if (realToken()) {
            if (Server.dataBase.getPropsals().get(getProposal()) == null)
                return ErrorServiceProgram.PORPOSAL_NOT_FOUND;
            return ErrorServiceProgram.OK;
        }
        return ErrorServiceProgram.VOTER_NOT_FOUND;
    }
}