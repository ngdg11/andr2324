package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class Frame2 extends AppCompatActivity {
    ArrayList<Subject> listSubject;

    private String selectedSubject; // Add this line to store the selected subject
    private String level = "EASY"; // Default level to "EASY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame2);
        listSubject = new ArrayList<>();
        listSubject.add(new Subject(0, "Địa lý", "Các câu hỏi về các vùng đất, địa hình, dân cư, khí hậu và hiện tương trên Trái Đất",  R.drawable.img_geography));
        listSubject.add(new Subject(1, "Lịch sử", "Các câu hỏi về sự kiện lịch sử liên quan dến con người",  R.drawable.img_history));
        listSubject.add(new Subject(2, "Khoa học", "Các câu hỏi về những định luật, cấu trúc và vận hành của thế giới tự nhiên",  R.drawable.img_science));
        listSubject.add(new Subject(3, "Toán học", "Các câu hỏi về tính toán",  R.drawable.art));


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


        //      xem toan bo cau hoi
        Button allQuizBtn = findViewById(R.id.allQuizBtn);
        allQuizBtn.setOnClickListener(view->{
            Intent displayAllQuiz = new Intent(Frame2.this, Frame5.class);
            startActivity(displayAllQuiz);
        });

    }
}