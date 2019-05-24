package net.thumbtack.school.elections.roles;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.Objects;
import java.util.UUID;

public class Voter{
    private String name, surname, patronymic;
    private String street, number, room;
    private String login, password;
    private OrderedMap proposals;
    private boolean candidate;
    private String token;
    private String tokenAddCandidate;
    private boolean requestAddCandidate;

    public boolean getRequestAddCandidate() {
        return requestAddCandidate;
    }

    public void setRequestAddCandidate(boolean requestAddCandidate) {
        this.requestAddCandidate = requestAddCandidate;
    }

    public String getTokenAddCandidate() {
        return tokenAddCandidate;
    }

    public void setTokenAddCandidate(String tokenAddCandidate) {
        this.tokenAddCandidate = tokenAddCandidate;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getRoom() {
        return room;
    }

    public void addProposal(String proposal, int rating){
        if (proposals == null)
            proposals = new LinkedMap();
        proposals.put(proposal, rating);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRoom(String room) {
        this.room = room;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(name, voter.name) &&
                Objects.equals(surname, voter.surname) &&
                Objects.equals(patronymic, voter.patronymic) &&
                Objects.equals(street, voter.street) &&
                Objects.equals(number, voter.number) &&
                Objects.equals(room, voter.room);
    }
}
