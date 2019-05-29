package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.elections.ResultElectionsDtoResponse;
import net.thumbtack.school.elections.response.elections.VoteDtoResponse;

public interface ElectionsDao {
    VoteDtoResponse vote(VoterDtoRequest voterDtoRequest);
    ResultElectionsDtoResponse resultElections(VoterDtoRequest voterDtoRequest);
}
