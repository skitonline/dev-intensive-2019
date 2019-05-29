package net.thumbtack.school.elections.error;

public enum  ErrorServiceElections {
    PARSING("Не удалось распарсить строку"),
    OK("Операция успешна"),
    VOTER_NOT_FOUND("Избиратель не найден"),
    CANDIDATE_NOT_FOUND("Кандидат не найден"),
    VOTE_YOURSELF("Нельзя голосовать за себя"),
    NOT_START("Голосование еще не началось"),
    VOTED("Избиратель уже проголосовал");

    public String getErrorString() {
        return errorString;
    }

    private String errorString;

    ErrorServiceElections(String errorString){
        this.errorString = errorString;
    }
}
