package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.GetDtoRequest;
import net.thumbtack.school.elections.response.get.GetCandidatesDtoResponse;

public interface GetDao {
    GetCandidatesDtoResponse getCandidates(GetDtoRequest getDtoRequest);
}
