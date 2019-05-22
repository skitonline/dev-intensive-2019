package net.thumbtack.school.elections.response;

import net.thumbtack.school.elections.request.RegisterVoterDtoRequest;

import java.util.UUID;

public class RegisterVoterDtoResponse {
    private UUID token;

    public UUID getToken() {
        return token;
    }

    public RegisterVoterDtoResponse() {
        this.token = UUID.randomUUID();
    }
}
