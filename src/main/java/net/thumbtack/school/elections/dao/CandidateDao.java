package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.AcceptAddCandidateDtoRequest;
import net.thumbtack.school.elections.request.AddCandidateDtoRequest;

public interface CandidateDao {
    ErroDataBase addCandidate(AddCandidateDtoRequest addCandidateDtoRequest);
    ErroDataBase acceptAddCandidate(AcceptAddCandidateDtoRequest acceptAddCandidateDtoRequest);
}
