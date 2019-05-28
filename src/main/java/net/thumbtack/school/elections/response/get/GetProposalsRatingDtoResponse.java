package net.thumbtack.school.elections.response.get;

import java.util.*;

public class GetProposalsRatingDtoResponse {
    private Map<String, Double> ratings = new HashMap<>();

    public Map<String, Double> getRatings() {
        return ratings;
    }

    public void setRatings(Map<String, Double> ratings) {
        this.ratings = ratings;
    }

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void sortByValue()
    {
        List<Map.Entry<String, Double>> list = new LinkedList<>(ratings.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        ratings = result;
    }
}
