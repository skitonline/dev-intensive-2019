package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.voter.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.request.voter.RestoreVoterDtoRequest;
import net.thumbtack.school.elections.roles.Voter;

public interface VoterDao {
    ErroDataBase insert(Voter insertVoter);
    ErroDataBase logout(LogoutVoterDtoRequest login);
    ErroDataBase restore(RestoreVoterDtoRequest user);
}
