package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;

public interface CandidateDao {
    ErroDataBase addCandidate(VoterDtoRequest voterDtoRequest);
    ErroDataBase acceptAddCandidate(VoterDtoRequest voterDtoRequest);
    ErroDataBase deleteCandidate(VoterDtoRequest voterDtoRequest);
}
