package net.thumbtack.school.ttschool;

public class TrainingException extends Exception{
    public TrainingErrorCode getErrorCode() {
        return errorCode;
    }

    private TrainingErrorCode errorCode;

    public TrainingException(TrainingErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }
}
