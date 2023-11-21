package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Frame2QuizDisplay extends AppCompatActivity {
    ArrayList<QuizDisplay> listQuizDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_quiz);
//       Thêm câu hỏi cho chủ đề
        listQuizDisplay = new ArrayList<>();
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",1,"hello"));
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",2,"hello"));
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",3,"hello"));
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",4,"hello"));
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",5,"hello"));
        listQuizDisplay.add(new QuizDisplay(0,"Địa lí",6,"hello"));
//        truyền cho adapter
        QuizDisplayAdapter adapter = new QuizDisplayAdapter((listQuizDisplay));
        ListView listItemSubject = findViewById(R.id.listItemSubject);
        listItemSubject.setAdapter(adapter);
//        Quay lại chọn chủ đề
        Button chooseSubjectBtn = findViewById(R.id.chooseSubjectBtn);
        chooseSubjectBtn.setOnClickListener(view->{
            Intent back = new Intent(Frame2QuizDisplay.this, Frame2.class);
            startActivity(back);
        });

    }
}