package net.thumbtack.school.colors.v3;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("WRONG_COLOR_STRING"),
    NULL_COLOR("NULL_COLOR");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ColorErrorCode(String errorString){
        this.errorString = errorString;
    }
}
