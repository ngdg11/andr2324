package quizz_app.quizz_project.quizz;

public class QuizDisplay {
    private int subjectId;
    private String subject;
    private int quizId;
    private String quiz;

    public QuizDisplay(int subjectId, String subject, int quizId, String quiz) {
        this.subjectId = subjectId;
        this.subject = subject;
        this.quizId = quizId;
        this.quiz = quiz;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }


}
