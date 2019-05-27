package net.thumbtack.school.elections.dao;

import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;

public interface ProposalsDao {
    ErroDataBase addProposal(VoterDtoRequest voterDtoRequest);
    ErroDataBase addProposalRating(VoterDtoRequest voterDtoRequest);
    ErroDataBase removeProposalRating(VoterDtoRequest voterDtoRequest);
}
