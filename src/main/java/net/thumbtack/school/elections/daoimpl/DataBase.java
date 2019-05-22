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

    static private List<String> voters = new ArrayList<>();

    static public List<String> getVoters() {
        return voters;
    }

    static public void insert(RegisterVoterDtoResponse registerVoterDtoResponse) {
        voters.add(registerVoterDtoResponse.toString());
    }
}
