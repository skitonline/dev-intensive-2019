package net.thumbtack.school.elections.error;

public enum  ErrorServiceProposals {
    NOW_LOGOUT("Пользователь покинул сервер"),
    OK("Операция успешна"),
    VOTER_NOT_FOUND("Пользователь не найден"),
    WRONG_RATING("Оценка должна быть от 1 до 5"),
    PROPOSAL_NOT_FOUND("Предложение не найдено");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceProposals(String errorString){
        this.errorString = errorString;
    }
}
