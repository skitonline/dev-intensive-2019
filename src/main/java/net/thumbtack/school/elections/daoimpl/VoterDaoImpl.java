package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.request.RestoreVoterDtoRequest;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public ErroDataBase insert(Voter insertVoter) {
        return Server.dataBase.insert(insertVoter);
    }

    @Override
    public ErroDataBase logout(LogoutVoterDtoRequest login) {
        return Server.dataBase.logout(login);
    }

    @Override
    public ErroDataBase restore(RestoreVoterDtoRequest user) {
        return Server.dataBase.restore(user);
    }
}
