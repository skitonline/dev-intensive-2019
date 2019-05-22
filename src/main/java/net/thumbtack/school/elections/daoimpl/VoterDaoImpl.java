package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.VoterDao;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;
import net.thumbtack.school.elections.server.Server;

public class VoterDaoImpl implements VoterDao {
    @Override
    public void insert(RegisterVoterDtoResponse registerVoterDtoResponse) {
        Server.dataBase.insert(registerVoterDtoResponse);
    }
}
