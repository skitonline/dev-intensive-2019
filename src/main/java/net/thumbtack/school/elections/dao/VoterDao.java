package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.response.RegisterVoterDtoResponse;

public interface VoterDao {
    void insert(RegisterVoterDtoResponse registerVoterDtoResponse);
}
