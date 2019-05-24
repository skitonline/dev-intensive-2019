package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalsDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.proposal.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.proposal.AddProposalRatingDtoRequest;
import net.thumbtack.school.elections.request.proposal.RemoveProposalRatingDtoRequest;

public class ProposalsDaoImpl implements ProposalsDao {
    @Override
    public ErroDataBase addProposal(AddProposalDtoRequest addProposalDtoRequest) {
        return DataBase.addProposal(addProposalDtoRequest);
    }

    @Override
    public ErroDataBase addProposalRating(AddProposalRatingDtoRequest addProposalRatingDtoRequest) {
        return DataBase.addProposalRating(addProposalRatingDtoRequest);
    }

    @Override
    public ErroDataBase removeProposalRating(RemoveProposalRatingDtoRequest removeProposalRatingDtoRequest) {
        return DataBase.removeProposalRating(removeProposalRatingDtoRequest);
    }
}
