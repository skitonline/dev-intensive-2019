package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalsDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.AddProposalDtoRequest;
import net.thumbtack.school.elections.request.AddProposalRatingDtoRequest;

public class ProposalsDaoImpl implements ProposalsDao {
    @Override
    public ErroDataBase addProposal(AddProposalDtoRequest addProposalDtoRequest) {
        return DataBase.addProposal(addProposalDtoRequest);
    }

    @Override
    public ErroDataBase addProposalRating(AddProposalRatingDtoRequest addProposalRatingDtoRequest) {
        return DataBase.addProposalRating(addProposalRatingDtoRequest);
    }
}
