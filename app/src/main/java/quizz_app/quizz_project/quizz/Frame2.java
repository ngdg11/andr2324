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
    ArrayList<Subject> listSubject; // mảng cho 5 subject
    private String level = "EASY"; // level default là easy
    private String fileScoreName = "score.txt"; // file lưu tổng điểm frame 2 (internal)
    private String selectedSubject; // subject đc chọn
    String currentScore; // biến lưu điểm lần chơi gần nhất

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame2);
        Log.d("QuizApp", "Frame2 Khởi tạo");

        listSubject = new ArrayList<>();
        listSubject.add(new Subject(0, "Địa lý", "Các câu hỏi về các vùng đất, địa hình, dân cư, khí hậu và hiện tương trên Trái Đất",  R.drawable.img_geo));
        listSubject.add(new Subject(1, "Lịch sử", "Các câu hỏi về sự kiện lịch sử liên quan dến con người",  R.drawable.img_his));
        listSubject.add(new Subject(2, "Khoa học", "Các câu hỏi về những định luật, cấu trúc và vận hành của thế giới tự nhiên",  R.drawable.img_science));
        listSubject.add(new Subject(3, "Toán học", "Các câu hỏi về tính toán",  R.drawable.img_math1));
        listSubject.add(new Subject(4, "Hóa học","Các câu hỏi về các chất hóa học",R.drawable.img_chem));
        Log.d("QuizApp", "Frame2 - Đã thêm các môn học vào danh sách");

        //truyen cho  adapter
        SubjectAdapter adapter = new SubjectAdapter(listSubject);

        // item ben trong subject
        ListView listItemSubject = findViewById(R.id.listItemSubject);
        listItemSubject.setAdapter(adapter);
        Log.d("QuizApp", "Frame2 - Adapter đã được thiết lập");

        //chọn level, default là easy
        SwitchMaterial chooseLevel = findViewById(R.id.levelBtn);
        chooseLevel.setOnClickListener(view ->{
            if(chooseLevel.isChecked()){
                level = "HARD";
                Log.d("QuizApp", "level: " + level);
            }else{
                level = "EASY";
                Log.d("QuizApp", "level: " + level);
            }
        });

        // chọn 1 trong 5 subject
        listItemSubject.setOnItemClickListener((parent, view, position, id) -> {
            selectedSubject = listSubject.get(position).getNameSub();
            Log.d("QuizApp", "subject đã chọn: " + selectedSubject);

            Intent pass_subject = new Intent(Frame2.this, Frame3.class);
            pass_subject.putExtra("SELECTED_SUBJECT", selectedSubject);
            pass_subject.putExtra("SELECTED_LEVEL", level);
            startActivity(pass_subject);
        });

        // hiển thị điểm hiện tại
        TextView score = findViewById(R.id.score);
        readScore();
        score.setText(currentScore);
        Log.d("QuizApp", "Điểm hiện tại: " + currentScore);


        // xem toan bo cau hoi frame 5
        Button allQuizBtn = findViewById(R.id.allQuizBtn);
        allQuizBtn.setOnClickListener(view->{
            Intent displayAllQuiz = new Intent(Frame2.this, Frame5.class);
            startActivity(displayAllQuiz);
            Log.d("QuizApp", "Di chuyển đến Frame5");
        });


        // restore state xoay màn hình
        if (savedInstanceState != null) {
            selectedSubject = savedInstanceState.getString("SELECTED_SUBJECT");
            level = savedInstanceState.getString("SELECTED_LEVEL");
            currentScore = savedInstanceState.getString("CURRENT_SCORE");
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