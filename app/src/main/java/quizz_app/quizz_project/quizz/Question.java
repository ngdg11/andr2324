package quizz_app.quizz_project.quizz;

public class Question {
    private String questionText;
    private String[] answers; // An array of four answers
    private int correctAnswerIndex; // Index of the correct answer (0 to 3)

    public Question(String questionText, String[] answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
