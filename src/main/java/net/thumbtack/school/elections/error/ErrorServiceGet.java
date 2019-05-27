package net.thumbtack.school.elections.error;

public enum ErrorServiceGet {
    PARSING("Не удалось распарсить строку"),
    OK("Операция успешна"),
    VOTER_NOT_FOUND("Пользователь не найден");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceGet(String errorString){
        this.errorString = errorString;
    }
}
