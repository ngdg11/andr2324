package quizz_app.quizz_project.quizz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Frame6 extends AppCompatActivity {
    private String level;
    private String question;
    private String correctAnswer;
    private String[] listAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame6);
        QuestionDisplayFrame5 questionDisplayInFrame6 = (QuestionDisplayFrame5) getIntent().getSerializableExtra("QUESTION_DATA");

        if(questionDisplayInFrame6 !=null){
            question = questionDisplayInFrame6.getQuestion();
            listAnswer = questionDisplayInFrame6.getAnswer();
            level = questionDisplayInFrame6.getLevel();
            correctAnswer = questionDisplayInFrame6.getCorrectAnswer();

        }
        TextView questionDisplay = findViewById(R.id.question);
        questionDisplay.setText(question);
        TextView levelDisplay = findViewById(R.id.level);
        levelDisplay.setText(level);
        TextView answer1Display = findViewById(R.id.answer1);
        answer1Display.setText(listAnswer[0]);
        TextView answer2Display = findViewById(R.id.answer2);
        answer2Display.setText(listAnswer[1]);
        TextView answer3Display = findViewById(R.id.answer3);
        answer3Display.setText(listAnswer[2]);
        TextView answer4Display = findViewById(R.id.answer4);
        answer4Display.setText(listAnswer[3]);
        TextView correctAnswerDisplay = findViewById(R.id.correctAnswer);
        correctAnswerDisplay.setText(listAnswer[Integer.valueOf(correctAnswer)]);

    }
}