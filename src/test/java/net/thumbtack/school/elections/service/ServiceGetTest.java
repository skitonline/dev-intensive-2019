package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

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
        System.out.println(server.getCandidates(stringVoter1));
    }
}