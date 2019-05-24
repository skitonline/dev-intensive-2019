package net.thumbtack.school.elections.response.voter;

public class RegisterVoterDtoResponse {
    private String token;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
