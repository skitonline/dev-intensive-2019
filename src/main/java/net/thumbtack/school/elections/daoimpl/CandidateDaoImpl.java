package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.CandidateDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.candidate.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.AddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.DeleteCandidateDtoRequest;

public class CandidateDaoImpl implements CandidateDao {
    @Override
    public ErroDataBase addCandidate(AddCandidateDtoRequest addCandidateDtoRequest) {
        return DataBase.addCandidate(addCandidateDtoRequest);
    }

    @Override
    public ErroDataBase acceptAddCandidate(AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest) {
        return DataBase.acceptAddCandidate(acceptAddCandidateDtoRequest);
    }

    @Override
    public ErroDataBase deleteCandidate(DeleteCandidateDtoRequest deleteCandidateDtoRequest) {
        return DataBase.deleteCandidate(deleteCandidateDtoRequest);
    }
}
