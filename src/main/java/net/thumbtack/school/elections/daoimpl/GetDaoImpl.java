package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.GetDao;
import net.thumbtack.school.elections.request.GetDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsVoterDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;
import net.thumbtack.school.elections.server.Server;

public class GetDaoImpl implements GetDao {
    @Override
    public GetCandidatesDtoResponse getCandidates(GetDtoRequest getDtoRequest) {
        return DataBase.getCandidates(getDtoRequest);
    }

    @Override
    public GetProposalsRatingDtoResponse getProposalsRating(GetDtoRequest getDtoRequest) {
        return DataBase.getProposalsRating(getDtoRequest);
    }

    @Override
    public GetProposalsVoterDtoResponse getProposalsVoter(GetDtoRequest getDtoRequest) {
        return DataBase.getProposalsVoter(getDtoRequest);
    }
}
