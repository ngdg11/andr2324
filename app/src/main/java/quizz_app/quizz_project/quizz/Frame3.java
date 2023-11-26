package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Frame3 extends AppCompatActivity {
    private int currentQuestionIndex = 0;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);

        Intent intent = getIntent();
        String subject = intent.getStringExtra("SELECTED_SUBJECT");
        // Initialize your questions list here based on the subject
        initializeQuestions(subject);
        displayQuestion();

        displayQuestion();

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAnswer()) {
                    currentQuestionIndex++;
                    if(currentQuestionIndex < questions.size()) {
                        displayQuestion();
                    } else {
                        // Handle the case when all questions are answered
                        // e.g., show a score summary or return to the subject list
                    }
                } else {
                    // Handle incorrect answer
                    // e.g., display a message or a prompt
                }
            }
        });





    }

    // gen questions
    private void initializeQuestions(String subject) {
        questions = new ArrayList<>();

        if (subject.equals("Địa lý")) {
            questions.add(new Question("What is the capital of France?",
                    new String[]{"Paris", "London", "Rome", "Berlin"}, 0));
            questions.add(new Question("What is the largest ocean on Earth?",
                    new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3));
            // Add more geography questions here
        } else if (subject.equals("Lịch sử")) {
            questions.add(new Question("Who was the first president of the United States?",
                    new String[]{"George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams"}, 0));
            questions.add(new Question("In what year did World War II end?",
                    new String[]{"1945", "1939", "1918", "1963"}, 0));
            // Add more history questions here
        }
        // ... Add cases for other subjects
    }





    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            TextView questionTextView = findViewById(R.id.questionTextView);
            RadioButton answer1 = findViewById(R.id.answer1);
            RadioButton answer2 = findViewById(R.id.answer2);
            RadioButton answer3 = findViewById(R.id.answer3);
            RadioButton answer4 = findViewById(R.id.answer4);

            questionTextView.setText(currentQuestion.getQuestionText());

            // Assuming each question has exactly 4 answers
            String[] answers = currentQuestion.getAnswers();
            answer1.setText(answers[0]);
            answer2.setText(answers[1]);
            answer3.setText(answers[2]);
            answer4.setText(answers[3]);

            // Reset the RadioGroup for new question
            RadioGroup answersGroup = findViewById(R.id.answersGroup);
            answersGroup.clearCheck();
        } else {
            // Handle the case when there are no more questions
        }
    }


    private boolean checkAnswer() {
        RadioGroup answersGroup = findViewById(R.id.answersGroup);
        int selectedId = answersGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        int answerIndex = answersGroup.indexOfChild(selectedRadioButton);

        return questions.get(currentQuestionIndex).getCorrectAnswerIndex() == answerIndex;
    }

}