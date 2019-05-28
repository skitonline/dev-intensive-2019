package net.thumbtack.school.elections.error;

public enum  ErrorServiceProgram {
    OK("Операция успешна"),
    VOTER_NOT_FOUND("Пользователь не найден"),
    PORPOSAL_NOT_FOUND("Предложение не найдено"),
    NOT_CANDIDATE("Пользователь не является кандидидатом"),
    ELECTIONS_START("Голосование уже началось");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceProgram(String errorString){
        this.errorString = errorString;
    }
}
