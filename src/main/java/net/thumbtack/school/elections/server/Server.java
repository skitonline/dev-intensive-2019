package net.thumbtack.school.elections.server;

import net.thumbtack.school.elections.service.server.ServiceServer;

import java.io.*;

public class Server {
    private StringBuilder state;
    private String name;

    public StringBuilder getState() {
        return state;
    }

    public void setState(StringBuilder state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void startServer(String savedDataFileName) throws IOException{
        new ServiceServer(this).startServer(savedDataFileName);
    }

    public void stopServer(String saveDataFileName)  throws IOException{
        new ServiceServer(this).stopServer(saveDataFileName);
    }
}
