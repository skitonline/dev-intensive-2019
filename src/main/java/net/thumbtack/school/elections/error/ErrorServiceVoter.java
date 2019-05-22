package net.thumbtack.school.elections.error;

public enum ErrorServiceVoter {
    OK("Операция успешна"),
    WRONG_VOTER_DATA("Отсутствует одно из обязательных полей: фамилию, имя, улица, дом"),
    WRONG_LOGIN("Логин должен быть не меньше 3 символов"),
    WRONG_PASSWORD("Пароль должен быть не меньше 6 символов"),
    DUPLICATE_VOTER("Пользователь с такими данными зарегистрирован"),
    DUPLICATE_LOGIN("Пользователь с таким логином зарегистрирован"),
    ALREADY_DEACTIVITED("Пользователь уже покинул сервер"),
    NOT_FOUND_LOGIN("Пользователь с указанным логином не найден"),
    ALREADY_ACTIVITED("Пользователь в сети");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceVoter(String errorString){
        this.errorString = errorString;
    }
}
