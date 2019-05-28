package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErrorServiceGet;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.VoterInformation;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TestServiceGet {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void getCandidates() {
        DataBase.clear();
        Server.startElections = false;
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        voter1.setCandidate(true);
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        voter1.setProposal("very nice");
        stringVoter1 = gson.toJson(voter1);
        String addProposal = server.addProposal(stringVoter1);
        Voter voter2 = new Voter("Alexander", "NeEvseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline2", "123456");
        String stringVoter2 = gson.toJson(voter2);
        server.registerVoter(stringVoter2);
        Voter voter3 = new Voter("NeAlexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline3", "123456");
        String stringVoter3 = gson.toJson(voter3);
        server.registerVoter(stringVoter3);
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        String getCandidates = server.getCandidates(stringVoter1);
        assertEquals("{\"cantidates\":[{\"name\":\"Alexander\",\"surname\":\"Evseev\"," +
                "\"patronymic\":\"Maksimovich\",\"street\":\"Zarubina\",\"number\":\"9\",\"room\":\"10\"," +
                "\"program\":[\"very nice\"]}],\"error\":\"Операция успешна\"}", getCandidates);
    }

    @Test
    public void getProposalsRating() {
        DataBase.clear();
        Map<String,Map<String,Integer>> map = new HashMap<>();
        Map<String, Integer> rating1 = new HashMap<>();
        rating1.put("token1", 5); rating1.put("token2", 5); rating1.put("token3", 5);
        map.put("proposal 1", rating1);
        Map<String, Integer> rating2 = new HashMap<>();
        rating2.put("token1", 1); rating2.put("token2", 2);
        map.put("proposal 2", rating2);
        Map<String, Integer> rating3 = new HashMap<>();
        rating3.put("token1", 5); rating3.put("token2", 3);
        map.put("proposal 3", rating3);
        DataBase.setPropsals(map);
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        String getProposalsRating = server.getProposalsRating(stringVoter1);
        assertEquals("{\"ratings\":{\"proposal 2\":1.5,\"proposal 3\":4.0,\"proposal 1\":5.0}," +
                "\"error\":\"Операция успешна\"}", getProposalsRating);
    }

    @Test
    public void getProposalsVoter() {
        DataBase.clear();
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        Map<String, Boolean> map = new HashMap<>();
        map.put("proposal 1", true);
        map.put("proposal 2", false);
        map.put("proposal 3", true);
        voter1.setProgram(map);
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        Voter voter2 = new Voter("Alexander1", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline2", "123456");
        voter2.setTokenActionCandidate(voter1.getToken());
        String stringVoter2 = gson.toJson(voter2);
        server.registerVoter(stringVoter2);
        voter2 = DataBase.getVoters().get(1);
        stringVoter2 = gson.toJson(voter2);
        String result = server.getProposalsVoter(stringVoter2);
        assertEquals("{\"proposals\":[\"proposal 1\",\"proposal 3\"],\"error\":\"Операция успешна\"}",
                result);
    }
}