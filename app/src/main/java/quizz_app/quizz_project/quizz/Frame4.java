package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Frame4 extends AppCompatActivity {
    private Button finishBtn;
    private Button playAgainBtn;
    private Button shareBtn;

    private String selectedSubject;
    private String selectedLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame4);
        Intent pass_number_of_corrected_answers = getIntent();
        int correctAnswersCount = pass_number_of_corrected_answers.getIntExtra("CORRECT_ANSWERS_COUNT", 0);

        TextView correctAnswersTextView = findViewById(R.id.correctAnswersTextView);
        correctAnswersTextView.setText(String.valueOf(correctAnswersCount) );

        //Button Finish
        finishBtn = findViewById(R.id.btnFinish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_finish = new Intent(Frame4.this, MainActivity.class);
                startActivity(intent_finish);
            }
        });


        //pass lại sang frame 3
        Intent pass_subject_level = getIntent();
        selectedSubject = pass_subject_level.getStringExtra("SELECTED_SUBJECT");
        selectedLevel = pass_subject_level.getStringExtra("SELECTED_LEVEL");
        //Button playAgain
        playAgainBtn = findViewById(R.id.btnAgain);
        playAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playAgainIntent = new Intent(Frame4.this, Frame3.class);
                playAgainIntent.putExtra("SELECTED_SUBJECT", selectedSubject);
                playAgainIntent.putExtra("SELECTED_LEVEL", selectedLevel);
                startActivity(playAgainIntent);
                finish();
            }
        });

        //Button share
        shareBtn = findViewById(R.id.btnShare);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                if(v2.getId() == shareBtn.getId()){
                    Intent intent_share = new Intent(Intent.ACTION_SEND);
                    intent_share.setAction(Intent.ACTION_SEND);
                    intent_share.putExtra(Intent.EXTRA_TEXT,"My score is"+ correctAnswersTextView.getText());
                    intent_share.setType("text/plain");
                    startActivity(intent_share);
                    Log.e("QuizApp", "Ko share duoc");
                }
            }
        });

    }

}

