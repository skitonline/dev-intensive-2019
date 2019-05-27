package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public ErroDataBase insertVoter(VoterDtoRequest insertVoter) {
        return Server.dataBase.insertVoter(insertVoter);
    }

    @Override
    public ErroDataBase logoutVoter(VoterDtoRequest login) {
        return Server.dataBase.logoutVoter(login);
    }

    @Override
    public ErroDataBase restoreVoter(VoterDtoRequest user) {
        return Server.dataBase.restoreVoter(user);
    }
}
