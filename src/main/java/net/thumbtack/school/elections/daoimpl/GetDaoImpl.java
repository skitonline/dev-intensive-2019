package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.GetDao;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsVoterDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;

public class GetDaoImpl implements GetDao {
    @Override
    public GetCandidatesDtoResponse getCandidates(VoterDtoRequest voterDtoRequest) {
        return DataBase.getCandidates(voterDtoRequest);
    }

    @Override
    public GetProposalsRatingDtoResponse getProposalsRating(VoterDtoRequest voterDtoRequest) {
        return DataBase.getProposalsRating(voterDtoRequest);
    }

    @Override
    public GetProposalsVoterDtoResponse getProposalsVoter(VoterDtoRequest voterDtoRequest) {
        return DataBase.getProposalsVoter(voterDtoRequest);
    }
}
