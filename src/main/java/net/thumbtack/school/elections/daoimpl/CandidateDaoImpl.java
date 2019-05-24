package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.AddCandidateDtoRequest;

public class CandidateDaoImpl implements CandidateDao {
    @Override
    public ErroDataBase addCandidate(AddCandidateDtoRequest addCandidateDtoRequest) {
        return DataBase.addCandidate(addCandidateDtoRequest);
    }

    @Override
    public ErroDataBase acceptAddCandidate(AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest) {
        return DataBase.acceptAddCandidate(acceptAddCandidateDtoRequest);
    }
}
