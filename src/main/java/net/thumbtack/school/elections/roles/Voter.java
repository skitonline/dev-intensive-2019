package net.thumbtack.school.elections.roles;

import net.thumbtack.school.elections.roles.parents.User;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.Objects;

public class Voter extends User {
    private String name, surname, patronymic;
    private String street, number, room;
    private OrderedMap proposals;

    public Voter(String name, String surname, String patronymic,
                 String street, String number, String room,
                 String login, String password) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.number = number;
        this.room = room;
        proposals = new LinkedMap();
    }

    public void addProposal(String proposal, int rating){
        proposals.put(proposal, rating);
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
