package net.thumbtack.school.elections.error;

public enum ErrorServiceCandidate {
    PARSING("Не удалось распарсить строку"),
    OK("Операция успешна"),
    WHO_ADD_NOT_FOUND("Выдвигающий кандидата не найден или покинул сервер"),
    WHOM_ADD_NOT_FOUND("Выдвигаемый кандидат не найден или покинул сервер"),
    VOTER_NOT_FOUND("Пользователь не найден"),
    ELECTIONS_START("Голосование уже началось");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceCandidate(String errorString){
        this.errorString = errorString;
    }
}
