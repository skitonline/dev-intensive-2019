package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.response.LogoutVoterDtoResponse;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.parents.Login;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceVoter {
    Gson gson = new Gson();
    Server server = new Server();


    @Test
    public void registerVoter() {
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        assertEquals(1, Server.dataBase.getVoters().size());
        assertEquals(1, Server.dataBase.getTokens().size());
        RegisterVoterDtoResponse registerVoterDtoResponse1 =
                gson.fromJson(rigister1, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse1), rigister1);
        //Нет фамилии
        Voter voter2 = new Voter("Alexander", "", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline2", "123456");
        String stringVoter2 = gson.toJson(voter2);
        String rigister2 = server.registerVoter(stringVoter2);
        RegisterVoterDtoResponse registerVoterDtoResponse2 =
                gson.fromJson(rigister2, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse2), rigister2);
        assertEquals(1, Server.dataBase.getVoters().size());
        assertEquals(1, Server.dataBase.getTokens().size());
        //Есть все поля
        Voter voter3 = new Voter("Alexander", "Pupkin", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline3", "123456");
        String stringVoter3 = gson.toJson(voter3);
        String rigister3 = server.registerVoter(stringVoter3);
        RegisterVoterDtoResponse registerVoterDtoResponse3 =
                gson.fromJson(rigister3, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse3), rigister3);
        assertEquals(2, Server.dataBase.getVoters().size());
        assertEquals(2, Server.dataBase.getTokens().size());
        //Нет отчества, это допустимо
        Voter voter4 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline4", "123456");
        String stringVoter4 = gson.toJson(voter4);
        String rigister4 = server.registerVoter(stringVoter4);
        RegisterVoterDtoResponse registerVoterDtoResponse4 =
                gson.fromJson(rigister4, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse4), rigister4);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
        //Нет номера дома
        Voter voter5 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", null, "10",
                "skitonline5", "123456");
        String stringVoter5 = gson.toJson(voter5);
        String rigister5 = server.registerVoter(stringVoter5);
        RegisterVoterDtoResponse registerVoterDtoResponse5 =
                gson.fromJson(rigister5, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse5), rigister5);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
        //Дубликат избирателя
        Voter voter6 = new Voter("Alexander", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline6", "123456");
        String stringVoter6 = gson.toJson(voter6);
        String rigister6 = server.registerVoter(stringVoter6);
        RegisterVoterDtoResponse registerVoterDtoResponse6 =
                gson.fromJson(rigister6, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse6), rigister6);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
        //Дубликат логина
        Voter voter7 = new Voter("Vasya", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter7 = gson.toJson(voter7);
        String rigister7 = server.registerVoter(stringVoter7);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
        //Короткий логин
        Voter voter8 = new Voter("Petya", "Pupkin", "",
                "Zarubina", "9", "10",
                "sk", "123456");
        String stringVoter8 = gson.toJson(voter8);
        String rigister8 = server.registerVoter(stringVoter8);
        RegisterVoterDtoResponse registerVoterDtoResponse8 =
                gson.fromJson(rigister8, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse8), rigister8);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
        //Короткий пароль
        Voter voter9 = new Voter("Petya", "Pupkin", "",
                "Zarubina", "9", "10",
                "skitoooo", null);
        String stringVoter9 = gson.toJson(voter9);
        String rigister9 = server.registerVoter(stringVoter9);
        RegisterVoterDtoResponse registerVoterDtoResponse9 =
                gson.fromJson(rigister9, RegisterVoterDtoResponse.class);
        assertEquals(gson.toJson(registerVoterDtoResponse9), rigister9);
        assertEquals(3, Server.dataBase.getVoters().size());
        assertEquals(3, Server.dataBase.getTokens().size());
    }

    @Test
    public void logoutAndLoginVoter(){
        Server.dataBase.getVoters().clear();
        Server.dataBase.getTokens().clear();
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);

        String stringLogout1 = gson.toJson(new Login("sk"));
        String logout1 = server.logoutVoter(stringLogout1);
        LogoutVoterDtoResponse logoutVoterDtoResponse1 =
                gson.fromJson(logout1, LogoutVoterDtoResponse.class);
        assertEquals(ErrorServiceVoter.LOGIN_LENGTH.getErrorString(), logoutVoterDtoResponse1.getError());
        String stringLogout2 = gson.toJson(new Login("nettakogo"));
        String logout2 = server.logoutVoter(stringLogout2);
        LogoutVoterDtoResponse logoutVoterDtoResponse2 =
                gson.fromJson(logout2, LogoutVoterDtoResponse.class);
        assertEquals(ErroDataBase.LOGIN_NOT_FOUND.getErrorString(), logoutVoterDtoResponse2.getError());
        String stringLogout3 = gson.toJson(new Login("skitonline1"));
        String logout3 = server.logoutVoter(stringLogout3);
        LogoutVoterDtoResponse logoutVoterDtoResponse3 =
                gson.fromJson(logout3, LogoutVoterDtoResponse.class);
        assertEquals(ErroDataBase.OK.getErrorString(), logoutVoterDtoResponse3.getError());
        String stringLogout4 = gson.toJson(new Login("skitonline1"));
        String logout4 = server.logoutVoter(stringLogout4);
        LogoutVoterDtoResponse logoutVoterDtoResponse4 =
                gson.fromJson(logout4, LogoutVoterDtoResponse.class);
        assertEquals(ErroDataBase.NOW_LOGOUT.getErrorString(), logoutVoterDtoResponse4.getError());
    }

}