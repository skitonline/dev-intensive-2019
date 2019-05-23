package net.thumbtack.school.elections.error;

public enum ErroDataBase {
    OK("Операция успешна"),
    DUPLICATE_VOTER("Пользователь с такими данными зарегистрирован"),
    DUPLICATE_LOGIN("Пользователь с таким логином зарегистрирован");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErroDataBase(String errorString){
        this.errorString = errorString;
    }
}
