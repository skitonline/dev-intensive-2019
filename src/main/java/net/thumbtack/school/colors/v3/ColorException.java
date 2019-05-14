package net.thumbtack.school.colors.v3;

public class ColorException extends Exception {
    public ColorErrorCode getErrorCode() {
        return errorCode;
    }

    private ColorErrorCode errorCode;

    public ColorException(ColorErrorCode errorCode){
        this.errorCode = errorCode;
    }
}
