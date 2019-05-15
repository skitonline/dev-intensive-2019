package net.thumbtack.school.ttschool;

enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("TRAINEE_WRONG_FIRSTNAME"),
    TRAINEE_WRONG_LASTNAME("TRAINEE_WRONG_LASTNAME"),
    TRAINEE_WRONG_RATING("TRAINEE_WRONG_RATING");

    TrainingErrorCode(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    private String error;
}
