package net.thumbtack.school.elections.error;

public enum ErroDataBase {
    OK("Операция успешна"),
    DUPLICATE_VOTER("Пользователь с такими данными зарегистрирован"),
    DUPLICATE_LOGIN("Пользователь с таким логином зарегистрирован"),
    NOW_LOGOUT("Пользователь уже покинул сервер"),
    VOTER_NOT_FOUND("Пользователь  не найден"),
    LOGIN_OR_PASSWORD("Неверный логин или пароль"),
    NOW_ACTIVED("Аккаунт уже активирован"),
    NOW_CANDIDATE("Выдвигаемый избиратель уже является кандидатом"),
    WAIT_ACCEPT_ADD_CANDIDATE("Вы выдвинули кандидата, он должен дать согласие"),
    NOT_ADD_CANDIDATE("Вас не выдвигали в кандидаты");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErroDataBase(String errorString){
        this.errorString = errorString;
    }
}
