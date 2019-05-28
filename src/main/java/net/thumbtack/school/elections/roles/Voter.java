package net.thumbtack.school.elections.roles;

import java.util.*;

public class Voter {
    private String name, surname, patronymic;
    private String street, number, room;
    private Map<String, Boolean> program;
    private String login, password;
    private boolean candidate;
    private String token;
    private String tokenActionCandidate;
    private boolean requestAddCandidate;
    private String proposal;
    private int ratingProposal;
    private boolean voted;

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean getRequestAddCandidate() {
        return requestAddCandidate;
    }

    public void setRequestAddCandidate(boolean requestAddCandidate) {
        this.requestAddCandidate = requestAddCandidate;
    }

    public String getTokenActionCandidate() {
        return tokenActionCandidate;
    }

    public void setTokenActionCandidate(String tokenActionCandidate) {
        this.tokenActionCandidate = tokenActionCandidate;
    }

    public Voter(String name, String surname, String patronymic,
                 String street, String number, String room,
                 String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.number = number;
        this.room = room;
        this.login = login;
        this.password = password;
    }

    public boolean isCandidate() {
        return candidate;
    }

    public void setCandidate(boolean candidate) {
        this.candidate = candidate;
    }

    public String getToken() {
        return token;
    }

    public void generateToken(){
        token = UUID.randomUUID().toString();
    }

    public void doNullToken(){
        token = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public int getRatingProposal() {
        return ratingProposal;
    }

    public void setRatingProposal(int ratingProposal) {
        this.ratingProposal = ratingProposal;
    }

    public void addPorposal(String proposal, boolean myProposal){
        if (getProgram() == null){
            setProgram(new HashMap<>());
        }
        getProgram().put(proposal, myProposal);
    }

    public Map<String, Boolean> getProgram() {
        return program;
    }

    public void setProgram(Map<String, Boolean> program) {
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(getName(), voter.getName()) &&
                Objects.equals(getSurname(), voter.getSurname()) &&
                Objects.equals(getPatronymic(), voter.getPatronymic()) &&
                Objects.equals(getStreet(), voter.getStreet()) &&
                Objects.equals(getNumber(), voter.getNumber()) &&
                Objects.equals(getRoom(), voter.getRoom());
    }
}
