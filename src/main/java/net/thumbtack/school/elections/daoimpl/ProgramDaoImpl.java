package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProgramDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class ProgramDaoImpl implements ProgramDao {
    @Override
    public ErroDataBase addInProgram(VoterDtoRequest voterDtoRequest) {
        return DataBase.addInProgram(voterDtoRequest);
    }

    @Override
    public ErroDataBase removeFromProgram(VoterDtoRequest voterDtoRequest) {
        return DataBase.removeFromProgram(voterDtoRequest);
    }
}
