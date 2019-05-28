package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.VoterInformation;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ServiceGetTest {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void getCandidates() {
        DataBase.clear();
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
        GetCandidatesDtoResponse getCandidatesDtoResponse1 =
                gson.fromJson(getCandidates, GetCandidatesDtoResponse.class);
        List<VoterInformation> cantidates = new ArrayList<>();
        cantidates.add(new VoterInformation(voter1.getName(), voter1.getSurname(), voter1.getPatronymic(),
                voter1.getStreet(), voter1.getNumber(), voter1.getRoom()));
        cantidates.get(0).setProgram(DataBase.getVoters().get(0).getProgram().keySet());
        //assertEquals(cantidates, getCandidatesDtoResponse1.getCantidates());
    }

    public void getProposalsRating() {
        DataBase.clear();
        Map<String,Map<String,Integer>> map = new HashMap<>();
        Map<String, Integer> rating = new HashMap<>();
        rating.put("token1", 5); rating.put("token2", 5); rating.put("token3", 5);
        map.put("proposal 1", rating);
        rating.clear();
        rating.put("token1", 1); rating.put("token2", 2);
        map.put("proposal 2", rating);
        rating.clear();
        rating.put("token1", 5); rating.put("token2", 3);
        map.put("proposal 3", rating);
        DataBase.setPropsals(map);
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        System.out.println(server.getProposalsRating(stringVoter1));
    }
}