package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.candidate.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.AddCandidateDtoRequest;
import net.thumbtack.school.elections.request.candidate.DeleteCandidateDtoRequest;

public interface CandidateDao {
    ErroDataBase addCandidate(AddCandidateDtoRequest addCandidateDtoRequest);
    ErroDataBase acceptAddCandidate(AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest);
    ErroDataBase deleteCandidate(DeleteCandidateDtoRequest deleteCandidateDtoRequest);
}
