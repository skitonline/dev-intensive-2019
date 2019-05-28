package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.request.GetDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;
import net.thumbtack.school.elections.response.get.GetProposalsRatingDtoResponse;

public interface GetDao {
    GetCandidatesDtoResponse getCandidates(GetDtoRequest getDtoRequest);
    GetProposalsRatingDtoResponse getProposalsRating(GetDtoRequest getDtoRequest);
}
