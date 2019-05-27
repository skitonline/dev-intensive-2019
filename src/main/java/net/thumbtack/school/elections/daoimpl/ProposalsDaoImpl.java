package net.thumbtack.school.elections.daoimpl;

import net.thumbtack.school.elections.dao.ProposalsDao;
import net.thumbtack.school.elections.error.ErroDataBase;
import net.thumbtack.school.elections.request.VoterDtoRequest;
import net.thumbtack.school.elections.server.Server;

public class ProposalsDaoImpl implements ProposalsDao {
    @Override
    public ErroDataBase addProposal(VoterDtoRequest addProposalDtoRequest) {
        return Server.dataBase.addProposal(addProposalDtoRequest);
    }

    @Override
    public ErroDataBase addProposalRating(VoterDtoRequest addProposalRatingDtoRequest) {
        return Server.dataBase.addProposalRating(addProposalRatingDtoRequest);
    }

    @Override
    public ErroDataBase removeProposalRating(VoterDtoRequest removeProposalRatingDtoRequest) {
        return Server.dataBase.removeProposalRating(removeProposalRatingDtoRequest);
    }
}
