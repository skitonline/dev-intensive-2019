package net.thumbtack.school.elections.roles.validation;

import net.thumbtack.school.elections.roles.Voter;

public class VoterValidation implements Validation{
    private Voter voter;

    public VoterValidation(Voter voter) {
        this.voter = voter;
    }

    @Override
    public boolean validation() {
        return true;
    }
}
