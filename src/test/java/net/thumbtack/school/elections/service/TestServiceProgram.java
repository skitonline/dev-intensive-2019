package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceProgram;
import net.thumbtack.school.elections.response.program.AddInProgramDtoResponse;
import net.thumbtack.school.elections.response.program.RemoveFromProgramDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestServiceProgram {
    Gson gson = new Gson();
    Server server = new Server();

    @Test
    public void addInProgram() {
        DataBase.clear();
        Server.startElections = false;
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        voter1.setCandidate(true);
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
        //voter1 добавялет себе предложение voter2
        voter1.setProposal("very very nice");
        stringVoter1 = gson.toJson(voter1);
        assertEquals(1, DataBase.getVoters().get(0).getProgram().size());
        String addInProgram1 = server.addInProgram(stringVoter1);
        AddInProgramDtoResponse addInProgramDtoResponse1 =
                gson.fromJson(addProposa2, AddInProgramDtoResponse.class);
        assertEquals(2, DataBase.getVoters().get(0).getProgram().size());
        assertTrue(DataBase.getVoters().get(0).getProgram().get("very nice"));
        assertFalse(DataBase.getVoters().get(0).getProgram().get("very very nice"));
        assertEquals(ErroDataBase.OK.getErrorString(), addInProgramDtoResponse1.getError());
        //добавляем в программу уже добавленное ранее предложение
        String addInProgram2 = server.addInProgram(stringVoter1);
        AddInProgramDtoResponse addInProgramDtoResponse2 =
                gson.fromJson(addInProgram2, AddInProgramDtoResponse.class);
        assertEquals(2, DataBase.getVoters().get(0).getProgram().size());
        assertEquals(ErroDataBase.NOW_ADD_PROPOSAL.getErrorString(), addInProgramDtoResponse2.getError());
        //добавялем несуществующее предложение
        voter1.setProposal("net takogo");
        stringVoter1 = gson.toJson(voter1);
        String addInProgram3 = server.addInProgram(stringVoter1);
        AddInProgramDtoResponse addInProgramDtoResponse3 =
                gson.fromJson(addInProgram3, AddInProgramDtoResponse.class);
        assertEquals(2, DataBase.getVoters().get(0).getProgram().size());
        assertEquals(ErrorServiceProgram.PORPOSAL_NOT_FOUND.getErrorString(), addInProgramDtoResponse3.getError());
    }

    @Test
    public void removeFromProgram() {
        DataBase.clear();
        Server.startElections = false;
        //регаем 1 ползьователя
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        voter1.setCandidate(true);
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
        //voter1 добавялет себе предложение voter2
        voter1.setProposal("very very nice");
        stringVoter1 = gson.toJson(voter1);
        String addInProgram1 = server.addInProgram(stringVoter1);
        //удаялем предложение из программы
        String removeFromProgram1 = server.removeFromProgram(stringVoter1);
        RemoveFromProgramDtoResponse removeFromProgramDtoResponse =
                gson.fromJson(removeFromProgram1, RemoveFromProgramDtoResponse.class);
        assertEquals(1, DataBase.getVoters().get(0).getProgram().size());
        assertEquals(ErroDataBase.OK.getErrorString(),
                removeFromProgramDtoResponse.getError());
        //удаялем свое собственное предлжение
        voter1.setProposal("very nice");
        stringVoter1 = gson.toJson(voter1);
        String removeFromProgram2 = server.removeFromProgram(stringVoter1);
        RemoveFromProgramDtoResponse removeFromProgramDtoResponse1 =
                gson.fromJson(removeFromProgram2, RemoveFromProgramDtoResponse.class);
        assertEquals(1, DataBase.getVoters().get(0).getProgram().size());
        assertEquals(ErroDataBase.YOUR_PROPOSAL.getErrorString(),
                removeFromProgramDtoResponse1.getError());
    }
}