package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceVoterRigister {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void registerVoter() {
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
        assertEquals(ErrorServiceVoter.VOTER_DATA.getErrorString(), rigister2);
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
        assertEquals(ErrorServiceVoter.VOTER_DATA.getErrorString(), rigister5);
        //Дубликат избирателя
        Voter voter6 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline6", "123456");
        String stringVoter6 = gson.toJson(voter6);
        String rigister6 = server.registerVoter(stringVoter6);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(ErrorServiceVoter.DUPLICATE_VOTER.getErrorString(), rigister6);
        //Дубликат логина
        Voter voter7 = new Voter("Vasya", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter7 = gson.toJson(voter7);
        String rigister7 = server.registerVoter(stringVoter7);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(ErrorServiceVoter.DUPLICATE_LOGIN.getErrorString(), rigister7);
        //Короткий логин
        Voter voter8 = new Voter("Petya", "Pupkin", "",
                "Zarubina", "9", "10",
                "sk", "123456");
        String stringVoter8 = gson.toJson(voter8);
        String rigister8 = server.registerVoter(stringVoter8);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(ErrorServiceVoter.LOGIN_LENGTH.getErrorString(), rigister8);
        //Короткий пароль
        Voter voter9 = new Voter("Petya", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitoooo", null);
        String stringVoter9 = gson.toJson(voter9);
        String rigister9 = server.registerVoter(stringVoter9);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(ErrorServiceVoter.PASSWORD_LENGTH.getErrorString(), rigister9);
    }

    @Test
    public void logoutAndLoginVoter(){
        Server.dataBase.getVoters().clear();
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        assertEquals(1, Server.dataBase.getVoters().size());
        assertTrue(Server.dataBase.getVoters().get(0).isActive());
        String logout1 = server.logoutVoter(voter1.getLogin());
        assertEquals(1, Server.dataBase.getVoters().size());
        assertEquals(ErrorServiceVoter.OK.getErrorString(), logout1);
        assertFalse(Server.dataBase.getVoters().get(0).isActive());
        String logout2 = server.logoutVoter(null);
        assertEquals(ErrorServiceVoter.LOGIN_LENGTH.getErrorString(), logout2);
        String logout3 = server.logoutVoter(voter1.getLogin());
        assertEquals(ErrorServiceVoter.ALREADY_DEACTIVATED.getErrorString(), logout3);
        String login1 = server.loginVoter("[skitonline1, 123456]");
        assertTrue(Server.dataBase.getVoters().get(0).isActive());
        assertEquals(ErrorServiceVoter.OK.getErrorString(), login1);
        String login2 = server.loginVoter("[skit]");
        assertEquals(ErrorServiceVoter.LOGGING_LENGTH.getErrorString(), login2);
        String login3 = server.loginVoter("[skitonline1, 12345678]");
        assertEquals(ErrorServiceVoter.LOGIN_OR_PASSWORD.getErrorString(), login3);
    }
}