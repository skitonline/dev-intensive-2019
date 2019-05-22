package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.service.ServiceVoter;

import java.io.*;

public class Server {
    static public DataBase dataBase = DataBase.getInstance();
    private ServiceVoter serviceVoter = new ServiceVoter();

    public static DataBase getDataBase() {
        return dataBase;
    }

    public String registerVoter(String requestJsonString){
        return serviceVoter.registerVoter(requestJsonString);
    }
}
