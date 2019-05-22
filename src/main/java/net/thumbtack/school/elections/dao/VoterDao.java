package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public interface VoterDao {
    boolean insert(RegisterVoterDtoResponse registerVoterDtoResponse);
}
