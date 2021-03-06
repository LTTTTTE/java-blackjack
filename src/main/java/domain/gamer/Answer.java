package domain.gamer;

import exception.AnswerFormatException;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private String answerValue;

    Answer(String answerValue) {
        this.answerValue = answerValue;
    }

    public boolean isYes() {
        return this == YES;
    }

    public static Answer findAnswer(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.answerValue.equals(answer.toLowerCase()))
                .findFirst()
                .orElseThrow(AnswerFormatException::new);
    }
}
