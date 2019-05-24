package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.proposal.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.proposal.AddProposalRatingDtoRequest;
import net.thumbtack.school.elections.request.proposal.RemoveProposalRatingDtoRequest;

public interface ProposalsDao {
    ErroDataBase addProposal(AddProposalDtoRequest addProposalDtoRequest);
    ErroDataBase addProposalRating(AddProposalRatingDtoRequest addProposalRatingDtoRequest);
    ErroDataBase removeProposalRating(RemoveProposalRatingDtoRequest removeProposalRatingDtoRequest);
}
