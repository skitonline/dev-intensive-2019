package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.roles.parents.Login;

public interface VoterDao {
    ErroDataBase insert(Voter insertVoter);
    ErroDataBase logout(Login login);
}
