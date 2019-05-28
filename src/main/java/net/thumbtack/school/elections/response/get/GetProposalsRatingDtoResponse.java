package net.thumbtack.school.elections.response.get;

import java.util.HashMap;
import java.util.Map;

public class GetProposalsRatingDtoResponse {
    private Map<String, Double> ratings = new HashMap<>();

    public Map<String, Double> getRatings() {
        return ratings;
    }

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
