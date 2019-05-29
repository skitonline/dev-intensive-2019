package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestServiceServer {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void stopStartServer() throws IOException {
        DataBase.clear();
        DataBase.startElections = false;
        server.stopServer("stop");
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        voter1.setCandidate(true);
        voter1.setProposal("proposal");
        String stringVoter1 = gson.toJson(voter1);
        server.registerVoter(stringVoter1);
        voter1.setToken(DataBase.getVoters().get(0).getToken());
        stringVoter1 = gson.toJson(voter1);
        server.addProposal(stringVoter1);
        server.stopServer("stop1");
        server.startServer("stop1");
        server.stopServer("stop2");
        assertTrue(FileUtils.contentEquals(new File("stop1"), new File("stop2")));
    }
}