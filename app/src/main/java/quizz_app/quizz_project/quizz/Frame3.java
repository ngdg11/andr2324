package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Frame3 extends AppCompatActivity {
    private int currentQuestionIndex = 0;
    private List<Question> questions;

    private int score = 0; // Track the player's score
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);

        Intent pass_subject = getIntent();
        String subject = pass_subject.getStringExtra("SELECTED_SUBJECT");

        // Assign directly to the class-level 'level' variable
        level = pass_subject.getStringExtra("SELECTED_LEVEL");
        if (level == null) {
            level = "EASY";
        }

        questions = loadQuestions(subject, level);
        displayQuestion();


        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAnswer()) {
                    updateScore();
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        displayQuestion();
                    } else {
                        goToScoreScreen(); // hết câu hỏi
                    }
                } else {
                    goToScoreScreen(); // trả lời sai
                }
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            TextView questionTextView = findViewById(R.id.questionTextView);
            RadioButton answer1 = findViewById(R.id.answer1);
            RadioButton answer2 = findViewById(R.id.answer2);
            RadioButton answer3 = findViewById(R.id.answer3);
            RadioButton answer4 = findViewById(R.id.answer4);

            Log.d("QuizApp", "Current question: " + currentQuestion.getQuestionText());
            for (String answer : currentQuestion.getAnswers()) {
                Log.d("QuizApp", "Answer: " + answer);
            }

            questionTextView.setText(currentQuestion.getQuestionText());

            String[] answers = currentQuestion.getAnswers();
            answer1.setText(answers[0]);
            answer2.setText(answers[1]);
            answer3.setText(answers[2]);
            answer4.setText(answers[3]);

            // Reset RadioGroup cho câu hỏi mới
            RadioGroup answersGroup = findViewById(R.id.answersGroup);
            answersGroup.clearCheck();
        } else {
            Log.d("QuizApp", "No more questions available.");
        }
    }

    private List<Question> loadQuestions(String subject, String level) {
        List<Question> loadedQuestions = new ArrayList<>();
        String fileName = getFileNameForSubject(subject, level);
        Log.d("QuizApp", "Loading questions from file: " + fileName);

        try {
            InputStream is = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String[] answers = parts[1].split("\\$");
                int correctAnswer = Integer.parseInt(parts[2]);
                loadedQuestions.add(new Question(parts[0], answers, correctAnswer));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return loadedQuestions;
    }


    private String getFileNameForSubject(String subject, String level) {
        String baseName;
        switch (subject) {
            case "Địa lý":
                baseName = "geography_questions";
                break;
            case "Lịch sử":
                baseName = "history_questions";
                break;
            case "Khoa học":
                baseName = "science_questions";
                break;
            // ... other cases for different subjects
            default:
                baseName = ""; // Or handle unknown subjects appropriately
                break;
        }

        String levelSuffix = level.equalsIgnoreCase("HARD") ? "_hard" : "_easy";
        return baseName + levelSuffix + ".txt";
    }




    private boolean checkAnswer() {
        RadioGroup answersGroup = findViewById(R.id.answersGroup);
        int selectedId = answersGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        int answerIndex = answersGroup.indexOfChild(selectedRadioButton);

        return questions.get(currentQuestionIndex).getCorrectAnswerIndex() == answerIndex;
    }

    private void updateScore() {
        if ("HARD".equalsIgnoreCase(level)) {
            score += 2;
        } else {
            score += 1;
        }
    }

    private void goToScoreScreen() {
        Intent intent = new Intent(Frame3.this, Frame4.class);
        intent.putExtra("FINAL_SCORE", score);
        startActivity(intent);
        finish(); // close
    }

}