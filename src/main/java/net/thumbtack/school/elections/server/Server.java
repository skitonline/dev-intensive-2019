package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.service.*;

public class Server {
    static public DataBase dataBase = DataBase.getInstance();
    private ServiceServer serviceServer = new ServiceServer();
    private boolean running;
    private ServiceVoter serviceVoter = new ServiceVoter();
    private ServiceCandidate serviceCandidate = new ServiceCandidate();
    private ServiceProposals serviceProposals = new ServiceProposals();
    private ServiceProgram serviceProgram = new ServiceProgram();
    private ServiceGet serviceGet = new ServiceGet();

    public void startServer(String savedDataFileName){
        running = true;
        serviceServer.startServer(savedDataFileName);
    }

    public void stopServer(String saveDataFileName){
        running = false;
        serviceServer.stopServer(saveDataFileName);
    }

    public String registerVoter(String requestJsonString){
        return serviceVoter.registerVoter(requestJsonString);
    }

    public String logoutVoter(String requestJsonString){
        return serviceVoter.logoutVoter(requestJsonString);
    }

    public String restoreVoter(String requestJsonString){
        return serviceVoter.restoreVoter(requestJsonString);
    }

    public String addCandidate(String requestJsonString){
        return serviceCandidate.addCandidate(requestJsonString);
    }

    public String acceptAddCandidate(String requestJsonString){
        return serviceCandidate.acceptAddCandidate(requestJsonString);
    }

    public String deleteCandidate(String requestJsonString){
        return serviceCandidate.deleteCandidate(requestJsonString);
    }

    public String addProposal(String requestJsonString){
        return serviceProposals.addProposal(requestJsonString);
    }

    public String addProposalRating(String requestJsonString){
        return serviceProposals.addProposalRating(requestJsonString);
    }

    public String removeProposalRating(String requestJsonString){
        return serviceProposals.removeProposalRating(requestJsonString);
    }

    public String addInProgram(String requestJsonString){
        return serviceProgram.addInProgram(requestJsonString);
    }

    public String removeFromProgram(String requestJsonString){
        return serviceProgram.removeFromProgram(requestJsonString);
    }

    public String getCandidates(String requestJsonString){
        return serviceGet.getCandidates(requestJsonString);
    }
}
