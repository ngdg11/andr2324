package quizz_app.quizz_project.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Frame5 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_quiz_frame5);
        TextInputLayout textField = findViewById(R.id.textField);
        String[] list = {"Địa lí", "Lịch sử", "Khoa học", "Toán học"};
        ((MaterialAutoCompleteTextView) textField.getEditText()).setSimpleItems(list);

    }
}