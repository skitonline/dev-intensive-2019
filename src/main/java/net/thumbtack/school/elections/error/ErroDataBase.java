package net.thumbtack.school.elections.error;

public enum ErroDataBase {
    OK("Операция успешна"),
    DUPLICATE_VOTER("Пользователь с такими данными зарегистрирован"),
    DUPLICATE_LOGIN("Пользователь с таким логином зарегистрирован"),
    NOW_LOGOUT("Пользователь уже покинул сервер"),
    LOGIN_NOT_FOUND("Пользователь с указанным логином не найден"),
    LOGIN_OR_PASSWORD("Неверный логин или пароль"),
    NOW_ACTIVED("Аккаунт уже активирован");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErroDataBase(String errorString){
        this.errorString = errorString;
    }
}
