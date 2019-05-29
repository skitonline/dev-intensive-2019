package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ElectionsDao;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.elections.ResultElectionsDtoResponse;
import net.thumbtack.school.elections.response.elections.VoteDtoResponse;

public class ElectionsDaoImpl implements ElectionsDao {
    @Override
    public VoteDtoResponse vote(VoterDtoRequest voterDtoRequest) {
        return DataBase.vote(voterDtoRequest);
    }

    @Override
    public ResultElectionsDtoResponse resultElections(VoterDtoRequest voterDtoRequest) {
        return DataBase.resultElections(voterDtoRequest);
    }
}
