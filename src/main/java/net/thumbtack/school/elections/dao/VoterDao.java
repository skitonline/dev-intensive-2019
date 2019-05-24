package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.LogoutVoterDtoRequest;
import net.thumbtack.school.elections.request.RestoreVoterDtoRequest;
import net.thumbtack.school.elections.roles.Voter;

public interface VoterDao {
    ErroDataBase insert(Voter insertVoter);
    ErroDataBase logout(LogoutVoterDtoRequest login);
    ErroDataBase restore(RestoreVoterDtoRequest user);
}
