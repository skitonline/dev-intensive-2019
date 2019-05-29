package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErrorServiceElections;
import net.thumbtack.school.elections.response.elections.ResultElectionsDtoResponse;
import net.thumbtack.school.elections.response.elections.VoteDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class TestServiceElections {
    Server server = new Server();
    Gson gson = new Gson();

    @Test
    public void vote() {
        DataBase.clear();
        DataBase.startElections = false;
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        voter1.setCandidate(true);
        Map<String, Boolean> map1 = new HashMap<>();
        map1.put("proposal 1", true);
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        //голосует против всех, поле кандидата = null
        assertEquals(0, DataBase.getCandidates().size());
        String vote1 = server.vote(stringVoter1);
        assertEquals(1, (int)DataBase.getCandidates().get(null));
        VoteDtoResponse voteDtoResponse1 = gson.fromJson(vote1, VoteDtoResponse.class);
        assertEquals(ErrorServiceElections.OK.getErrorString(), voteDtoResponse1.getError());
        //повторно пытается голосовать против всех
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        String vote2 = server.vote(stringVoter1);
        VoteDtoResponse voteDtoResponse2 = gson.fromJson(vote2, VoteDtoResponse.class);
        assertEquals(ErrorServiceElections.VOTED.getErrorString(), voteDtoResponse2.getError());
        assertEquals(1, (int)DataBase.getCandidates().get(null));
    }

    @Test
    public void resultElections() {
        DataBase.clear();
        Map<String, Integer> map = new HashMap<>();
        map.put("token 1", 3);
        map.put("token 2", 6);
        map.put(null, 0);
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1 = DataBase.getVoters().get(0);
        stringVoter1 = gson.toJson(voter1);
        DataBase.startElections();
        DataBase.setCandidates(map);
        String resultElections = server.resultElections(stringVoter1);
        ResultElectionsDtoResponse resultElectionsDtoResponse =
                gson.fromJson(resultElections, ResultElectionsDtoResponse.class);
        assertEquals("token 2", resultElectionsDtoResponse.getWinner());
    }
}