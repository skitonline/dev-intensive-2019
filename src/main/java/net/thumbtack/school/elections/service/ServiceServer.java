package net.thumbtack.school.elections.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.thumbtack.school.elections.daoimpl.DataBase;
import net.thumbtack.school.elections.roles.Voter;
import net.thumbtack.school.elections.server.Server;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ServiceServer {
    public void startServer(String savedDataFileName){
        if (savedDataFileName != null) {
            try (BufferedReader br = new BufferedReader (new FileReader(savedDataFileName))) {
                DataBase.clear();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Voter>>(){}.getType();
                List<Voter> voters = gson.fromJson(br.readLine(), type);
                DataBase.setVoters(voters);
                type = new TypeToken<Map<String, Map<String,Integer>>>(){}.getType();
                Map<String, Map<String,Integer>> proposals = gson.fromJson(br.readLine(), type);
                DataBase.setPropsals(proposals);
                type = new TypeToken<Map<String, Integer>>(){}.getType();
                Map<String, Integer> cantidates = gson.fromJson(br.readLine(), type);
                DataBase.setCandidates(cantidates);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public void stopServer(String saveDataFileName){
        if (saveDataFileName != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataFileName))) {
                Gson gson = new Gson();
                bw.write(gson.toJson(DataBase.getVoters()));
                bw.newLine();
                bw.write(gson.toJson(DataBase.getPropsals()));
                bw.newLine();
                bw.write(gson.toJson(DataBase.getCandidates()));
                DataBase.clear();
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }
}
