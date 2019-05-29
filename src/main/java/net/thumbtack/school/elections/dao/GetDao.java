package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsVoterDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;

public interface GetDao {
    GetCandidatesDtoResponse getCandidates(VoterDtoRequest getDtoRequest);
    GetProposalsRatingDtoResponse getProposalsRating(VoterDtoRequest getDtoRequest);
    GetProposalsVoterDtoResponse getProposalsVoter(VoterDtoRequest getDtoRequest);
}
