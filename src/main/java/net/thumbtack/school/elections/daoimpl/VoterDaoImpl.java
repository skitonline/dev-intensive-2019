package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public ErroDataBase insertVoter(VoterDtoRequest insertVoter) {
        return DataBase.insertVoter(insertVoter);
    }

    @Override
    public ErroDataBase logoutVoter(VoterDtoRequest login) {
        return DataBase.logoutVoter(login);
    }

    @Override
    public ErroDataBase restoreVoter(VoterDtoRequest user) {
        return DataBase.restoreVoter(user);
    }
}
