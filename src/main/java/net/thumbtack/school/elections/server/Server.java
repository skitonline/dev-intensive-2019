package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.service.ServiceVoter;

public class Server {
    static public DataBase dataBase = DataBase.getInstance();
    private ServiceVoter serviceVoter = new ServiceVoter();

    public String registerVoter(String requestJsonString){
        return serviceVoter.registerVoter(requestJsonString);
    }
}
