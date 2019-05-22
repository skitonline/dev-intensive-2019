package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErrorServiceVoter;
import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public interface VoterDao {
    ErrorServiceVoter insert(RegisterVoterDtoResponse registerVoterDtoResponse);
}
