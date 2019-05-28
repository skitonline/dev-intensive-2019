package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ElectionsDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class ElectionsDaoImpl implements ElectionsDao {
    @Override
    public ErroDataBase vote(VoterDtoRequest voterDtoRequest) {
        return DataBase.vote(voterDtoRequest);
    }
}
