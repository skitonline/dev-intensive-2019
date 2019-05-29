package net.thumbtack.school.elections.response.elections;

import java.util.Map;

public class ResultElectionsDtoResponse {
    String winner;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
