package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Frame4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame4); // You need to create this layout

        Intent pass_finalScore = getIntent();
        int finalScore = pass_finalScore.getIntExtra("FINAL_SCORE", 0);

        TextView scoreTextView = findViewById(R.id.scoreTextView); // You need a TextView in your layout with this ID
        scoreTextView.setText("Your Score: " + finalScore);
    }
}
