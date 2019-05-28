package net.thumbtack.school.elections.error;

public enum ErrorServiceVoter {
    PARSING("Не удалось распарсить строку"),
    OK("Операция успешна"),
    DATA("Отсутствует одно из обязательных полей: фамилию, имя, улица, дом"),
    LOGIN_LENGTH("Логин должен быть не меньше 3 символов"),
    PASSWORD_LENGTH("Пароль должен быть не меньше 6 символов"),
    ELECTIONS_START("Голосование уже началось");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceVoter(String errorString){
        this.errorString = errorString;
    }
}
