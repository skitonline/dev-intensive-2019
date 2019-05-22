package net.thumbtack.school.elections.error;

public enum ErrorServiceVoter {
    OK("Операция успешна"),
    VOTER_DATA("Отсутствует одно из обязательных полей: фамилию, имя, улица, дом"),
    LOGIN_LENGTH("Логин должен быть не меньше 3 символов"),
    PASSWORD_LENGTH("Пароль должен быть не меньше 6 символов"),
    DUPLICATE_VOTER("Пользователь с такими данными зарегистрирован"),
    DUPLICATE_LOGIN("Пользователь с таким логином зарегистрирован"),
    ALREADY_DEACTIVATED("Пользователь уже покинул сервер"),
    NOT_FOUND_LOGIN("Пользователь с указанным логином не найден"),
    ALREADY_ACTIVATED("Пользователь в сети"),
    LOGGING_LENGTH("Введите информацию в формате: [логин, пароль]"),
    LOGIN_OR_PASSWORD("Неверный логин или пароль");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceVoter(String errorString){
        this.errorString = errorString;
    }
}
