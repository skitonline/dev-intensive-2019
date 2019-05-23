package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.server.User;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.request.server.Login;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public ErroDataBase insert(Voter insertVoter) {
        return Server.dataBase.insert(insertVoter);
    }

    @Override
    public ErroDataBase logout(Login login) {
        return Server.dataBase.logout(login);
    }

    @Override
    public ErroDataBase restore(User user) {
        return Server.dataBase.restore(user);
    }
}
