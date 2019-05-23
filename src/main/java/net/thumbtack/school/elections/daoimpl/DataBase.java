package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.roles.Voter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private static DataBase instance;

    public static DataBase getInstance() {
        return instance;
    }

    private DataBase(){}

    static private List<Voter> voters = new ArrayList<>();
    static private List<String> tokens = new ArrayList<>();

    static public ErroDataBase insert(Voter inserVoter) {
        for (Voter voter : voters) {
            if (inserVoter.equals(voter))
                return ErroDataBase.DUPLICATE_VOTER;
            if (inserVoter.getLogin().equals(voter.getLogin()))
                return ErroDataBase.DUPLICATE_LOGIN;
        }
        voters.add(inserVoter);
        tokens.add(UUID.randomUUID().toString());
        return ErroDataBase.OK;
    }

    public static List<Voter> getVoters() {
        return voters;
    }

    public static List<String> getTokens() {
        return tokens;
    }
}
