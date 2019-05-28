package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class CandidateDaoImpl implements CandidateDao {
    @Override
    public ErroDataBase addCandidate(VoterDtoRequest voterDtoRequest) {
        return DataBase.addCandidate(voterDtoRequest);
    }

    @Override
    public ErroDataBase acceptAddCandidate(VoterDtoRequest voterDtoRequest) {
        return DataBase.acceptAddCandidate(voterDtoRequest);
    }

    @Override
    public ErroDataBase deleteCandidate(VoterDtoRequest voterDtoRequest) {
        return DataBase.deleteCandidate(voterDtoRequest);
    }
}
