package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;

public interface ElectionsDao {
    ErroDataBase vote(VoterDtoRequest voterDtoRequest);
}
