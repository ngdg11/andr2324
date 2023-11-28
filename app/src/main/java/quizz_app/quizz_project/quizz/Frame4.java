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
//    private String selectedSubject;
//    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Frame2 frame2 = new Frame2(); // Tạo một đối tượng Frame2
//        String subject = frame2.selectedSubject; // Truy cập selectedSubject
//        String level = frame2.level; // Truy cập level
        setContentView(R.layout.frame4);
        Intent pass_number_of_corrected_answers = getIntent();
        int correctAnswersCount = pass_number_of_corrected_answers.getIntExtra("CORRECT_ANSWERS_COUNT", 0);

        TextView correctAnswersTextView = findViewById(R.id.correctAnswersTextView); // Make sure you have a TextView with this ID in your layout
        correctAnswersTextView.setText(String.valueOf(correctAnswersCount) );
//        selectedSubject = pass_number_of_corrected_answers.getStringExtra("Selected subject");
//        level = pass_number_of_corrected_answers.getStringExtra("Selected level");

        //Button Finish
        finishBtn = findViewById(R.id.btnFinish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_finish = new Intent(Frame4.this, MainActivity.class);
                startActivity(intent_finish);
            }
        });
        //Button playAgain( on fix)
        playAgainBtn = findViewById(R.id.btnAgain);
//        playAgainBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v1) {
//                Log.d("Frame4", "Selected Subject: " + selectedSubject);
//                Log.d("Frame4", "Selected Level: " + level);
//                Intent intent_play_again = new Intent(Frame4.this, Frame3.class);
//                intent_play_again.putExtra("Select Subject",selectedSubject);
//                intent_play_again.putExtra("Select level",level);
//                startActivity(intent_play_again);
//                Log.e("QuizApp", "Not back");
//            }
//        });
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

