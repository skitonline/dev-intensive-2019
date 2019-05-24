package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.service.ServiceCandidate;
import net.thumbtack.school.elections.service.ServiceServer;
import net.thumbtack.school.elections.service.ServiceVoter;

public class Server {
    static public DataBase dataBase = DataBase.getInstance();
    private ServiceServer serviceServer = new ServiceServer();
    private boolean running;
    private ServiceVoter serviceVoter = new ServiceVoter();
    private ServiceCandidate serviceCandidate = new ServiceCandidate();

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

    public String restoreAccess(String requestJsonString){
        return serviceVoter.restoreAccess(requestJsonString);
    }

    public String addCandidate(String requestJsonString){
        return serviceCandidate.addCandidate(requestJsonString);
    }

    public String acceptAddCandidate(String requestJsonString){
        return serviceCandidate.acceptAddCandidate(requestJsonString);
    }
}
