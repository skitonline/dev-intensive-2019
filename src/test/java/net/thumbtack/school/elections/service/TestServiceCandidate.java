package net.thumbtack.school.elections.service;

import com.google.gson.Gson;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.error.ErrorServiceCandidate;
import net.thumbtack.school.elections.response.candidate.AcceptAddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.AddCandidateDtoResponse;
import net.thumbtack.school.elections.response.candidate.DeleteCandidateDtoResponse;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestServiceCandidate {
    Server server = new Server();
    Gson gson = new Gson();

    @Test
    public void addAcceptDeleteCandidate() {
        DataBase.clear();
        //регистрация 2х пользователей
        Voter voter1 = new Voter("Alexander", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "skitonline1", "123456");
        String stringVoter1 = gson.toJson(voter1);
        String rigister1 = server.registerVoter(stringVoter1);
        Voter voter2 = new Voter("Petya", "Evseev", "Maksimovich",
                "Zarubina", "9", "10",
                "batya", "123456");
        String stringVoter2 = gson.toJson(voter2);
        String rigister2 = server.registerVoter(stringVoter2);
        stringVoter2 = gson.toJson(DataBase.getVoters().get(1));
        //Успешное выдвижение voter1 (сам себя)
        voter1.setToken(Server.dataBase.getVoters().get(0).getToken());
        voter1.setTokenActionCandidate(Server.dataBase.getVoters().get(0).getToken());
        stringVoter1 = gson.toJson(voter1);
        String addCandidate1 = server.addCandidate(stringVoter1);
        AddCandidateDtoResponse addCandidateDtoResponse1 =
                gson.fromJson(addCandidate1, AddCandidateDtoResponse.class);
        assertEquals(ErrorServiceCandidate.OK.getErrorString(), addCandidateDtoResponse1.getError());
        //Выдвижение voter1, который уже кандидат
        String addCandidate2 = server.addCandidate(stringVoter1);
        AddCandidateDtoResponse addCandidateDtoResponse2 =
                gson.fromJson(addCandidate2, AddCandidateDtoResponse.class);
        assertEquals(ErroDataBase.NOW_CANDIDATE.getErrorString(), addCandidateDtoResponse2.getError());
        //принять выдвжиение, когда вас не выдвигали
        String acceptAdd1 = server.acceptAddCandidate(stringVoter2);
        AcceptAddCandidateDtoResponse acceptAddCandidateDtoResponse1 =
                gson.fromJson(acceptAdd1, AcceptAddCandidateDtoResponse.class);
        assertEquals(ErroDataBase.NOT_ADD_CANDIDATE.getErrorString(),
                acceptAddCandidateDtoResponse1.getError());
        assertTrue(DataBase.getVoters().get(0).isCandidate());
        //voter1 выдвигает voter2 c подтверждением
        voter1.setTokenActionCandidate(Server.dataBase.getVoters().get(1).getToken());
        stringVoter1 = gson.toJson(voter1);
        String addCandidate3 = server.addCandidate(stringVoter1);
        AddCandidateDtoResponse addCandidateDtoResponse3 =
                gson.fromJson(addCandidate3, AddCandidateDtoResponse.class);
        assertEquals(ErroDataBase.WAIT_ACCEPT_ADD_CANDIDATE.getErrorString(),
                addCandidateDtoResponse3.getError());
        assertFalse(DataBase.getVoters().get(1).isCandidate());
        //voter 2 принимает выдвижение и становится кандидатом
        stringVoter2 = gson.toJson(DataBase.getVoters().get(1));
        String acceptAdd2 = server.acceptAddCandidate(stringVoter2);
        AcceptAddCandidateDtoResponse acceptAddCandidateDtoResponse2 =
                gson.fromJson(acceptAdd2, AcceptAddCandidateDtoResponse.class);
        assertEquals(ErroDataBase.OK.getErrorString(),
                acceptAddCandidateDtoResponse2.getError());
        assertTrue(DataBase.getVoters().get(1).isCandidate());
        //voter2 перестает быть кандидатом
        stringVoter2 = gson.toJson(DataBase.getVoters().get(1));
        String delete1 = server.deleteCandidate(stringVoter2);
        DeleteCandidateDtoResponse deleteCandidateDtoResponse1 =
                gson.fromJson(delete1, DeleteCandidateDtoResponse.class);
        assertEquals(ErroDataBase.OK.getErrorString(), deleteCandidateDtoResponse1.getError());
        assertFalse(DataBase.getVoters().get(1).isCandidate());
        //voter2 не кандидат, но все равно пытается сняться
        stringVoter2 = gson.toJson(DataBase.getVoters().get(1));
        String delete2 = server.deleteCandidate(stringVoter2);
        DeleteCandidateDtoResponse deleteCandidateDtoResponse2 =
                gson.fromJson(delete2, DeleteCandidateDtoResponse.class);
        assertEquals(ErroDataBase.NOT_CANDIDATE.getErrorString(), deleteCandidateDtoResponse2.getError());
        assertFalse(DataBase.getVoters().get(1).isCandidate());
    }
}