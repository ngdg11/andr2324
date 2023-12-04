package quizz_app.quizz_project.quizz;

import java.io.Serializable;

public class QuestionDisplayFrame5 implements Serializable {
    private String question;
    private String[] answer;
    private String correctAnswer;
    private String level;

    public QuestionDisplayFrame5(String question, String[] answer, String correctAnswer, String level) {
        this.question = question;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.level = level;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getLevel() {
        return level;
    }


}
