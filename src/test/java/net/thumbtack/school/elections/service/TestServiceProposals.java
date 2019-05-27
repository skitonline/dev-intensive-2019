package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceProposals;
import net.thumbtack.school.elections.response.proposal.AddProposalDtoResponse;
import net.thumbtack.school.elections.response.proposal.AddProposalRatingDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceProposals {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void addProposals() {
        DataBase.clear();
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        //пользователь подает предложение
        voter1.setProposal("very nice");
        stringVoter1 = gson.toJson(voter1);
        String addProposal = server.addProposal(stringVoter1);
        AddProposalDtoResponse addProposalDtoResponse1 =
                gson.fromJson(addProposal, AddProposalDtoResponse.class);
        assertEquals(1, DataBase.getPropsals().size());
        assertEquals(ErroDataBase.OK.getErrorString(), addProposalDtoResponse1.getError());
        //подача предложения, которое уже есть в базе
        String addProposa2 = server.addProposal(stringVoter1);
        AddProposalDtoResponse addProposalDtoResponse2 =
                gson.fromJson(addProposa2, AddProposalDtoResponse.class);
        assertEquals(1, DataBase.getPropsals().size());
        assertEquals(ErroDataBase.NOW_ADD_PROPOSAL.getErrorString(), addProposalDtoResponse2.getError());
    }

    @Test
    public void addProposalRating() {
        DataBase.clear();
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        //пользователь подает предложение
        voter1.setProposal("very nice");
        stringVoter1 = gson.toJson(voter1);
        String addProposal = server.addProposal(stringVoter1);
        //регаем 2 ползьователя
        Voter voter2 = new Voter("Alexander", "NeEvseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline3", "123456");
        String stringVoter2 = gson.toJson(voter2);
        String rigister2 = server.registerVoter(stringVoter2);
        voter2 = DataBase.getVoters().get(1);
        //пользователь подает предложение
        voter2.setProposal("very very nice");
        stringVoter2 = gson.toJson(voter2);
        String addProposa2 = server.addProposal(stringVoter2);
        //2ой пользователь оценивает 1го
        voter2.setRatingProposal(2);
        voter2.setProposal("very nice");
        stringVoter2 = gson.toJson(voter2);
        assertEquals(1, DataBase.getPropsals().get("very nice").size());
        String setRatingPorposal = server.addProposalRating(stringVoter2);
        assertEquals(2, DataBase.getPropsals().get("very nice").size());
        //оцениваем несуществующее предложение
        voter2.setProposal("not very nice");
        stringVoter2 = gson.toJson(voter2);
        String setRatingPorposa2 = server.addProposalRating(stringVoter2);
        AddProposalRatingDtoResponse addProposalRatingDtoResponse =
                gson.fromJson(setRatingPorposa2, AddProposalRatingDtoResponse.class);
        assertEquals(ErrorServiceProposals.PROPOSAL_NOT_FOUND.getErrorString(),
                addProposalRatingDtoResponse.getError());
        //недопустимая оценка
        voter2.setProposal("very nice");
        voter2.setRatingProposal(7);
        stringVoter2 = gson.toJson(voter2);
        String setRatingPorposa3 = server.addProposalRating(stringVoter2);
        AddProposalRatingDtoResponse addProposalRatingDtoResponse1 =
                gson.fromJson(setRatingPorposa3, AddProposalRatingDtoResponse.class);
        assertEquals(ErrorServiceProposals.WRONG_RATING.getErrorString(),
                addProposalRatingDtoResponse1.getError());
        //пользователь покинул сервер, оценки удалились
        String logout = server.logoutVoter(stringVoter2);
        assertEquals(1, DataBase.getPropsals().get("very nice").size());
    }

    @Test
    public void removeProposalRating() {
        DataBase.clear();
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        //пользователь подает предложение
        voter1.setProposal("very nice");
        stringVoter1 = gson.toJson(voter1);
        String addProposal = server.addProposal(stringVoter1);
        //регаем 2 ползьователя
        Voter voter2 = new Voter("Alexander", "NeEvseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline3", "123456");
        String stringVoter2 = gson.toJson(voter2);
        String rigister2 = server.registerVoter(stringVoter2);
        voter2 = DataBase.getVoters().get(1);
        //пользователь подает предложение
        voter2.setProposal("very very nice");
        stringVoter2 = gson.toJson(voter2);
        String addProposa2 = server.addProposal(stringVoter2);
        //2ой пользователь оценивает 1го
        voter2.setRatingProposal(2);
        voter2.setProposal("very nice");
        stringVoter2 = gson.toJson(voter2);
        assertEquals(1, DataBase.getPropsals().get("very nice").size());
        String setRatingPorposal = server.addProposalRating(stringVoter2);
        assertEquals(2, DataBase.getPropsals().get("very nice").size());
        //удаляем оценку
        String removeRating = server.removeProposalRating(stringVoter2);
        assertEquals(1, DataBase.getPropsals().get("very nice").size());
    }
}