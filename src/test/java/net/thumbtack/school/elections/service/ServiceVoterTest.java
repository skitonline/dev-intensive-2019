package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceVoterTest {

    @Test
    public void registerVoter() {
        Gson gson = new Gson();
        Server server = new Server();
        //Есть все поля
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        assertEquals(1, Server.dataBase.getVoters().size());
        assertEquals(rigister1, Server.dataBase.getVoters().get(0).getToken().toString());
        //Нет фамилии
        Voter voter2 = new Voter("Alexander", "", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline2", "123456");
        String stringVoter2 = gson.toJson(voter2);
        String rigister2 = server.registerVoter(stringVoter2);
        assertEquals("error", rigister2);
        assertEquals(1, Server.dataBase.getVoters().size());
        //Есть все поля
        Voter voter3 = new Voter("Alexander", "Pupkin", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline3", "123456");
        String stringVoter3 = gson.toJson(voter3);
        String rigister3 = server.registerVoter(stringVoter3);
        assertEquals(2, Server.dataBase.getVoters().size());
        assertEquals(rigister3, Server.dataBase.getVoters().get(1).getToken().toString());
        //Нет отчества, это допустимо
        Voter voter4 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline4", "123456");
        String stringVoter4 = gson.toJson(voter4);
        String rigister4 = server.registerVoter(stringVoter4);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(rigister4, Server.dataBase.getVoters().get(2).getToken().toString());
        //Нет номера дома
        Voter voter5 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", null, "10",
                "skitonline5", "123456");
        String stringVoter5 = gson.toJson(voter5);
        String rigister5 = server.registerVoter(stringVoter5);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals("error", rigister5);
        //Дубликат избирателя
        Voter voter6 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline6", "123456");
        String stringVoter6 = gson.toJson(voter6);
        String rigister6 = server.registerVoter(stringVoter6);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals("error", rigister6);
        //Дубликат логина
        Voter voter7 = new Voter("Vasya", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter7 = gson.toJson(voter7);
        String rigister7 = server.registerVoter(stringVoter7);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals("error", rigister7);
    }
}