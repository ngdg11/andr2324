package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Frame2 extends AppCompatActivity {
    ArrayList<Subject> listSubject;

    private String selectedSubject; // Add this line to store the selected subject
    private String level = "EASY"; // Default level to "EASY
    private String fileScoreName = "score.txt";
    String currentScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame2);
        listSubject = new ArrayList<>();
        listSubject.add(new Subject(0, "Địa lý", "Các câu hỏi về các vùng đất, địa hình, dân cư, khí hậu và hiện tương trên Trái Đất",  R.drawable.img_geo));
        listSubject.add(new Subject(1, "Lịch sử", "Các câu hỏi về sự kiện lịch sử liên quan dến con người",  R.drawable.img_his));
        listSubject.add(new Subject(2, "Khoa học", "Các câu hỏi về những định luật, cấu trúc và vận hành của thế giới tự nhiên",  R.drawable.img_science));
        listSubject.add(new Subject(3, "Toán học", "Các câu hỏi về tính toán",  R.drawable.img_math1));
        listSubject.add(new Subject(4, "Hóa học","Các câu hỏi về các chất hóa học",R.drawable.img_chem));

        //truyen cho  adapter
        SubjectAdapter adapter = new SubjectAdapter(listSubject);

        // item ben trong subject
        ListView listItemSubject = findViewById(R.id.listItemSubject);
        listItemSubject.setAdapter(adapter);

        //chọn level, default là easy
        SwitchMaterial chooseLevel = findViewById(R.id.levelBtn);
        chooseLevel.setOnClickListener(view ->{
            if(chooseLevel.isChecked()){
                level = "HARD";
            }else{
                level = "EASY";
            }
        });


        listItemSubject.setOnItemClickListener((parent, view, position, id) -> {
            selectedSubject = listSubject.get(position).getNameSub();
            Intent pass_subject = new Intent(Frame2.this, Frame3.class);
            pass_subject.putExtra("SELECTED_SUBJECT", selectedSubject);
            pass_subject.putExtra("SELECTED_LEVEL", level);
            startActivity(pass_subject);
        });

        //hiển thị điểm
        TextView score = findViewById(R.id.score);
        readScore();
        score.setText(currentScore);


        //      xem toan bo cau hoi
        Button allQuizBtn = findViewById(R.id.allQuizBtn);
        allQuizBtn.setOnClickListener(view->{
            Intent displayAllQuiz = new Intent(Frame2.this, Frame5.class);
            startActivity(displayAllQuiz);
        });


        // Restore state if available
        if (savedInstanceState != null) {
            selectedSubject = savedInstanceState.getString("SELECTED_SUBJECT");
            level = savedInstanceState.getString("SELECTED_LEVEL");
            currentScore = savedInstanceState.getString("CURRENT_SCORE");
            // Update UI elements with restored data if needed
            TextView score_save = findViewById(R.id.score);
            score_save.setText(currentScore);
        }

    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save data khi xoay mh
        outState.putString("SELECTED_SUBJECT", selectedSubject);
        outState.putString("SELECTED_LEVEL", level);
        outState.putString("CURRENT_SCORE", currentScore);
    }
    // Đọc file Score
    private void readScore() {
        try {
            // Open stream to read file.
            FileInputStream in = this.openFileInput(fileScoreName);

            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String dataScore;
            while ((dataScore = br.readLine()) != null) {
                // phân chia kí tự score|"string"
                String[] score = dataScore.split("\\|");
                String title = String.valueOf(score[0]);
                currentScore = String.valueOf(score[1]);
                Log.d("title",title);
                Log.d("score",currentScore);

            }
        } catch (Exception e) {
            Log.d("error",String.valueOf(e) );
        }
    }
}