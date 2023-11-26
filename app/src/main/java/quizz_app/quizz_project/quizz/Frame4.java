package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Frame4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame4);

        Intent pass_number_of_corrected_answers = getIntent();
        int correctAnswersCount = pass_number_of_corrected_answers.getIntExtra("CORRECT_ANSWERS_COUNT", 0);

        TextView correctAnswersTextView = findViewById(R.id.correctAnswersTextView); // Make sure you have a TextView with this ID in your layout
        correctAnswersTextView.setText("Correct Answers: " + correctAnswersCount);
    }
}

