package quizz_app.quizz_project.quizz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Frame5 extends AppCompatActivity {


    private String subject;
    private ArrayList<QuestionDisplayFrame5> listQuestion;

    private String[] listAllFileName;
    private String[] listSubjectName = {"history_questions", "geography_questions", "science_questions", "math_questions"};
    ListView listItemQuestion;

    //    Xử lí tên file cho các chủ để
    private String handleFileName(String subject) {
        String subjectName = " ";
        String fileName = " ";
        switch (subject) {
            case "Địa lý":
                subjectName = "geography_questions";
                break;
            case "Lịch sử":
                subjectName = "history_questions";
                break;
            case "Khoa học":
                subjectName = "science_questions";
                break;
            case "Toán học":
                subjectName = "math_questions";
            default:
                break;
        }
        fileName = String.format(subjectName + ".txt");
        return fileName;
    }

    //    Xử lí tên các file
    private String GetAllFileNameSubject() {
        String[] listFileSubjectNameHard = new String[listSubjectName.length];
        String[] lisFiletSubjectNameEasy = new String[listSubjectName.length];
        for (int i = 0; i < listSubjectName.length; i++) {
            lisFiletSubjectNameEasy[i] = listSubjectName[i] + ".txt";
            listFileSubjectNameHard[i] = listSubjectName[i] + ".txt";
        }
        String[] combinedSubject = new String[listFileSubjectNameHard.length * 2];
        for (int i = 0; i < listSubjectName.length; i++) {
            combinedSubject[i] = lisFiletSubjectNameEasy[i];
            combinedSubject[listSubjectName.length + i] = listFileSubjectNameHard[i];
        }
        StringBuilder connectFileNameTogether = new StringBuilder();
        for (int i = 0; i < listFileSubjectNameHard.length + lisFiletSubjectNameEasy.length; i++) {
            connectFileNameTogether.append(combinedSubject[i]).append("&");
        }

        return String.valueOf(connectFileNameTogether);
    }

    //    hàm lưu câu hỏi
    private void saveQuestion(String saveQuestion) {
        try {
            InputStream is = getAssets().open(saveQuestion);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String data;
            while ((data = reader.readLine()) != null) {
                String[] parts = data.split("\\|");
                String[] answers = parts[1].split("\\$");
                int correctAnswer = Integer.parseInt(parts[2]);
                String question = parts[0];
                String level = parts[3];
                Log.d("question", parts[0]);
                Log.d("answer", parts[1]);
                Log.d("correctAnswer", parts[2]);
                Log.d("level", parts[3]);
                listQuestion.add(new QuestionDisplayFrame5(question, answers, String.valueOf(correctAnswer), level));
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    hiển thị câu hỏi theo chủ đề
    private void displayQuestion(String file) {
        listAllFileName = GetAllFileNameSubject().split("\\&");
        listQuestion = new ArrayList<>();
        QuestionDisplayAdapterFrame5 adapter;
        Log.d("file", file);
        switch (file) {
            case "ALL":
                for (int i = 0; i < listAllFileName.length; i++) {
                    saveQuestion(listAllFileName[i]);
                }
                //truyen cho  adapter
                adapter = new QuestionDisplayAdapterFrame5(listQuestion);
                listItemQuestion = (ListView) findViewById(R.id.listItemQuestion);
                listItemQuestion.setAdapter(adapter);
                break;
            case "Khoa học":
                Log.d("fileName", handleFileName(subject));
                saveQuestion(handleFileName(subject));
                //truyen cho  adapter
                adapter = new QuestionDisplayAdapterFrame5(listQuestion);
                listItemQuestion = findViewById(R.id.listItemQuestion);
                listItemQuestion.setAdapter(adapter);
                break;
            case "Địa lý":
                saveQuestion(handleFileName(subject));
                //truyen cho  adapter
                adapter = new QuestionDisplayAdapterFrame5(listQuestion);
                listItemQuestion = findViewById(R.id.listItemQuestion);
                listItemQuestion.setAdapter(adapter);
                break;
            case "Lịch sử":
                saveQuestion(handleFileName(subject));
                //truyen cho  adapter
                adapter = new QuestionDisplayAdapterFrame5(listQuestion);
                listItemQuestion = findViewById(R.id.listItemQuestion);
                listItemQuestion.setAdapter(adapter);
                break;
            case "Toán học":
                saveQuestion(handleFileName(subject));
                //truyen cho  adapter
                adapter = new QuestionDisplayAdapterFrame5(listQuestion);
                listItemQuestion = findViewById(R.id.listItemQuestion);
                listItemQuestion.setAdapter(adapter);
                break;
            default:
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_quiz_frame5);

        TextInputLayout subjectSuggest = findViewById(R.id.subjectSuggest);
//      Tạo danh sách và hiển thị theo gợi ý chủ đề
        String[] list = {"Địa lý", "Lịch sử", "Khoa học", "Toán học"};
        AppCompatAutoCompleteTextView autoCompleteSubject = findViewById(R.id.autoCompleteSubject);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list);
        autoCompleteSubject.setAdapter(adapter);

//      Khi focus vào ô sẽ hiện lên danh sách gợi ý
        autoCompleteSubject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    autoCompleteSubject.showDropDown();
                } else {
                    // ẩn bản phím khi không còn focus
                    InputMethodManager hideKeyBoard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    hideKeyBoard.hideSoftInputFromWindow(autoCompleteSubject.getWindowToken(), 0);
                }
            }
        });

//      hiển thị danh sách gợi ý chủ đề khi xóa hết chữ cái
        autoCompleteSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Kiểm tra nếu không có chữ sẽ show danh sách gợi ý, delay để không bị xung đột với onFocusChange, hiện bàn phím gõ chữ
                autoCompleteSubject.postDelayed(() -> {
                    if (editable.length() == 0) {
                        autoCompleteSubject.showDropDown();
                        InputMethodManager showKeyBoard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        showKeyBoard.showSoftInput(autoCompleteSubject, InputMethodManager.SHOW_IMPLICIT);
                    }
                }, 200);
            }
        });

//      hiển thị câu hỏi khi chọn chủ đề trong list gợi ý
        autoCompleteSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                subject = String.valueOf(subjectSuggest.getEditText().getText());
                Log.d("subject", subject);
//               Hien thi cau hoi theo tung chu de
                displayQuestion(subject);
//               Xóa bỏ focus
                autoCompleteSubject.clearFocus();
//               ẩn bản phím khi không còn focus
                InputMethodManager hideKeyBoard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyBoard.hideSoftInputFromWindow(autoCompleteSubject.getWindowToken(), 0);
            }
        });

//      hien thi toan bo cau hoi duoc luu trong cac file
        displayQuestion("ALL");
//      chuyen sang man hinh hien thi cau hoi, cau tra loi va muc do
        listItemQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toFrame6 = new Intent(Frame5.this, Frame6.class);
                QuestionDisplayFrame5 questionDisplayInFrame6 = new QuestionDisplayFrame5(listQuestion.get(i).getQuestion(), listQuestion.get(i).getAnswer(), listQuestion.get(i).getCorrectAnswer(), listQuestion.get(i).getLevel());
                toFrame6.putExtra("QUESTION_DATA", questionDisplayInFrame6);
                startActivity(toFrame6);
            }
        });
    }
}