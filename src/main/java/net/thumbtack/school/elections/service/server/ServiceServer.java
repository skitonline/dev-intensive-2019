package net.thumbtack.school.elections.service.server;

import net.thumbtack.school.elections.server.Server;

import java.io.*;

public class ServiceServer {
    private Server server;

    public ServiceServer(Server server) {
        this.server = server;
    }

    private void initializationServerState(String name){
        server.setState(new StringBuilder());
        server.setName(name);
        try {
            new File(name).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer(String savedDataFileName) throws IOException{
        if (savedDataFileName == null){
            initializationServerState("start");
        } else {
            server.setName(savedDataFileName);
            try(BufferedReader br = new BufferedReader(new FileReader(savedDataFileName))) {
                String s;
                while((s=br.readLine())!=null){
                    server.getState().append(s);
                }
            } catch(IOException e){
                e.getStackTrace();
            }
        }
    }

    public void stopServer(String saveDataFileName)  throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataFileName))) {
            bw.write(server.getState().toString());
            server.setState(new StringBuilder());
            server.setName("");
        } catch(IOException e){
            e.getStackTrace();
        }
    }
}
