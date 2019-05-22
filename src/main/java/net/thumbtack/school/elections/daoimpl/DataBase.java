package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){}

    static private List<RegisterVoterDtoResponse> voters = new ArrayList<>();

    static public List<RegisterVoterDtoResponse> getVoters() {
        return voters;
    }

    static public void insert(RegisterVoterDtoResponse registerVoterDtoResponse) {
        voters.add(registerVoterDtoResponse);
    }
}
