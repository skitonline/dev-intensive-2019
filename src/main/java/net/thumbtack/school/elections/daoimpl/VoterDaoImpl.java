package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public ErrorServiceVoter insert(RegisterVoterDtoResponse registerVoterDtoResponse) {
        return Server.dataBase.insert(registerVoterDtoResponse);
    }
}
