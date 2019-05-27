package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;

public interface VoterDao {
    ErroDataBase insertVoter(VoterDtoRequest voterDtoRequest);
    ErroDataBase logoutVoter(VoterDtoRequest voterDtoRequest);
    ErroDataBase restoreVoter(VoterDtoRequest voterDtoRequest);
}
