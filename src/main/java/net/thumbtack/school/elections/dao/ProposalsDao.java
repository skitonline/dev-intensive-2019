package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.AddProposalRatingDtoRequest;

public interface ProposalsDao {
    ErroDataBase addProposal(AddProposalDtoRequest addProposalDtoRequest);
    ErroDataBase addProposalRating(AddProposalRatingDtoRequest addProposalRatingDtoRequest);
}
