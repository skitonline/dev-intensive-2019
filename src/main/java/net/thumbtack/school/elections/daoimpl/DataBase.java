package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.parents.Login;

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

    public static List<Voter> getVoters() {
        return voters;
    }

    public static List<String> getTokens() {
        return tokens;
    }

    static public ErroDataBase insert(Voter insertVoter) {
        for (Voter voter : voters) {
            if (insertVoter.equals(voter))
                return ErroDataBase.DUPLICATE_VOTER;
            if (insertVoter.getLogin().equals(voter.getLogin()))
                return ErroDataBase.DUPLICATE_LOGIN;
        }
        voters.add(insertVoter);
        tokens.add(UUID.randomUUID().toString());
        return ErroDataBase.OK;
    }

    static public ErroDataBase logout(Login login){
        for (int i = 0; i < voters.size(); i++){
            if (login.getLogin().equals(voters.get(i).getLogin())) {
                if (tokens.get(i) == null)
                    return ErroDataBase.NOW_LOGOUT;
                else {
                    tokens.set(i, null);
                    return ErroDataBase.OK;
                }
            }
        }
        return ErroDataBase.LOGIN_NOT_FOUND;
    }
}
