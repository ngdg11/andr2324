package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Frame3 extends AppCompatActivity {
    // Biến để theo dõi câu hỏi hiện tại
    private int currentQuestionIndex = 0;
    private List<Question> questions; // Danh sách câu hỏi

    // Biến để lưu điểm và số câu trả lời đúng
    private int score = 0;
    private int correctAnswersCount = 0;
    private String fileScoreName = "score.txt";
    private String level; // Cấp độ câu hỏi (easy hoặc hard)
    private String subject;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);
        Log.d("QuizApp", "Khởi tạo Frame3");

        // Lấy thông tin môn học và level từ Intent trước đó
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("CURRENT_QUESTION_INDEX");
            score = savedInstanceState.getInt("SCORE");
            correctAnswersCount = savedInstanceState.getInt("CORRECT_ANSWERS_COUNT");
            level = savedInstanceState.getString("SELECTED_LEVEL");
            subject = savedInstanceState.getString("SELECTED_SUBJECT");
        } else {
            // nếu state là null thì lấy data từ intent frame 2
            Intent get_subject_and_level = getIntent();
            subject = get_subject_and_level.getStringExtra("SELECTED_SUBJECT");
            level = get_subject_and_level.getStringExtra("SELECTED_LEVEL");
        }
        if (level == null) {
            level = "EASY"; // Mặc định là easy
        }

        // Tải danh sách câu hỏi dựa trên môn học và cấp độ
        questions = loadQuestions(subject, level);

        // Hiển thị câu hỏi đầu tiên
        displayQuestion();

        // Xử lý khi người dùng bấm nút trả lời
        submitButton = findViewById(R.id.submitButton);
        submitButton.setEnabled(false); // gray out

        // nếu bấm vào đáp án thì mới hiện nút trả lời
        RadioGroup answersGroup = findViewById(R.id.answersGroup);
        answersGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // khi bấm vào answer thì bật nút trả lời
                if (submitButton != null) {
                    submitButton.setEnabled(true);
                }
            }
        });

        if (submitButton != null) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkAnswer()) { // Kiểm tra câu trả lời
                        updateCorrectAnswersCount(); // Cập nhật số câu trả lời đúng
                        currentQuestionIndex++; // Di chuyển đến câu hỏi tiếp theo
                        updateScore();

                        if (currentQuestionIndex < questions.size()) {
                            displayQuestion(); // Hiển thị câu hỏi tiếp theo
                            submitButton.setEnabled(false); // set lại nút trả lời về gray
                        } else {
                            goToScoreScreen(); // Nếu hết câu hỏi, chuyển đến màn hình điểm frame 4
                        }
                    }
                    else if (!checkAnswer()&&correctAnswersCount==0) {
                        score =0;
                        saveScoreTofile(String.valueOf(score));
                        goToScoreScreen();
                    }
                    else {
                        goToScoreScreen(); // Nếu trả lời sai, chuyển đến màn hình điểm frame 4
                    }
                }
            });
        }
    }

    // Hiển thị câu hỏi lên giao diện
    private void displayQuestion() {
        Log.d("QuizApp", "Bắt đầu hiển thị câu hỏi. Index hiện tại: " + currentQuestionIndex);
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            Log.d("QuizApp", "Câu hỏi lấy từ danh sách: " + currentQuestion.getQuestionText());
            TextView questionTextView = findViewById(R.id.questionTextView);
            RadioButton answer1 = findViewById(R.id.answer1);
            RadioButton answer2 = findViewById(R.id.answer2);
            RadioButton answer3 = findViewById(R.id.answer3);
            RadioButton answer4 = findViewById(R.id.answer4);


            // Hiển thị câu hỏi và các câu trả lời
            questionTextView.setText(currentQuestion.getQuestionText());
            String[] answers = currentQuestion.getAnswers();
            answer1.setText(answers[0]);
            answer2.setText(answers[1]);
            answer3.setText(answers[2]);
            answer4.setText(answers[3]);

            // Đặt lại RadioGroup cho câu hỏi mới
            RadioGroup answersGroup = findViewById(R.id.answersGroup);
            answersGroup.clearCheck();
        } else {
            Log.d("QuizApp", "Không còn câu hỏi.");
        }
    }

    // Tải danh sách câu hỏi từ file
    private List<Question> loadQuestions(String subject, String level) {
        List<Question> loadedQuestions = new ArrayList<>();
        String fileName = getFileNameForSubject(subject);

        Log.d("QuizApp", "Tải câu hỏi, Môn học: " + subject + ", Cấp độ: " + level);
        Log.d("QuizApp", "Đang tải câu hỏi từ tệp: " + fileName);

        try {
            // Mở tệp từ thư mục assets
            InputStream is = getAssets().open(fileName);
            // Đọc tệp văn bản dùng InputStreamReader và BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            // Đọc từng dòng trong tệp văn bản để tải câu hỏi
            while ((line = reader.readLine()) != null) {
                Log.d("QuizApp", "Đọc dòng: " + line);

                // Phân tách dòng thành các phần, phân chia bằng ký tự "|"
                String[] parts = line.split("\\|");

                // log các phần vừa tách trên 1 dòng
                for (int i = 0; i < parts.length; i++) {
                    Log.d("QuizApp", "part " + i + ": " + parts[i]);
                }

                // Phân tách phần câu trả lời thành mảng, phân chia bằng ký tự "$"
                String[] answers = parts[1].split("\\$");
                // log từng đáp án
                for (int i = 0; i < answers.length; i++) {
                    Log.d("QuizApp", "Đáp án " + i + ": " + answers[i]);
                }

                // Chuyển đổi phần correct cho câu trả lời đúng thành kiểu số nguyên
                int correctAnswer = Integer.parseInt(parts[2]);

                // Lấy level của câu hỏi từ phần cuối cùng của dòng
                String questionLevel = parts[3];

                // check level
                if (level.equalsIgnoreCase(questionLevel)) {
                    // Nếu phù hợp, thêm câu hỏi vào danh sách
                    loadedQuestions.add(new Question(parts[0], answers, correctAnswer));
                    Log.d("QuizApp", "Câu hỏi được thêm: " + parts[0]);
                }
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("QuizApp", "Không tải được tệp.");
        }
        Log.d("QuizApp", "Số lượng câu hỏi tải được: " + loadedQuestions.size());
        return loadedQuestions;
    }

    // lấy tên file
    private String getFileNameForSubject(String subject) {
        String subject_name;
        switch (subject) {
            case "Địa lý":
                subject_name = "geography_questions";
                break;
            case "Lịch sử":
                subject_name = "history_questions";
                break;
            case "Khoa học":
                subject_name = "science_questions";
                break;
            case "Toán học":
                subject_name = "math_questions";
                break;
            case "Hóa học":
                subject_name = "chemistry_questions";
                break;
            default:
                subject_name = "";
                break;
        }
        return subject_name + ".txt";
    }

    // Kiểm tra câu trả lời
    private boolean checkAnswer() {
        Log.d("QuizApp", "Kiểm tra câu trả lời cho câu hỏi số: " + currentQuestionIndex);

        RadioGroup answersGroup = findViewById(R.id.answersGroup);
        int selectedId = answersGroup.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = findViewById(selectedId);

        // lấy index của đáp án vừa chọn
        int answerIndex = answersGroup.indexOfChild(selectedRadioButton);

        Log.d("QuizApp", "Index đáp án được chọn: " + answerIndex);

        // lấy index của câu trả lời đúng
        int correctAnswerIndex = questions.get(currentQuestionIndex).getCorrectAnswerIndex();

        // so sánh đáp án vừa chọn với correctanswerindex
        boolean isCorrect = (correctAnswerIndex == answerIndex);
        Log.d("QuizApp", "Return đáp án: " + isCorrect);

        return isCorrect;
    }


    // Cập nhật điểm cho frame 2
    private void updateScore() {
        if (level.equals("hard") || level.equals("HARD")) {
            score += 2;
            Log.d("testScore",String.valueOf(score));

        } else if(level.equals("easy") || level.equals("EASY")) {
            score += 1;
            Log.d("testScore",String.valueOf(score));

        }else {
            score = 0;
        }
        saveScoreTofile(String.valueOf(score));

    }

    // Cập nhật số câu trả lời đúng
    private void updateCorrectAnswersCount() {
        correctAnswersCount++;
        Log.d("QuizApp", "Số câu trả lời đúng hiện tại " + correctAnswersCount);
    }
    //lưu điểm vào file
    private void saveScoreTofile(String saveScore) {
        try {
            // lưu điểm vừa chơi vào file để hiển thị ở frame 2 (internal storage)
            FileOutputStream out = this.openFileOutput(fileScoreName, MODE_PRIVATE);
            if(correctAnswersCount >= 0){
                saveScore = String.format(("score|"+ saveScore +" pts"));
            }
            out.write(saveScore.getBytes());
            out.close();
            Log.d("score", saveScore);
        } catch (Exception e) {
            Log.d("error", String.valueOf(e));
        }
    }

    // Chuyển đến màn hình điểm frame 4
    private void goToScoreScreen() {
        Intent pass_number_of_corrected_answers = new Intent(Frame3.this, Frame4.class);
        pass_number_of_corrected_answers.putExtra("CORRECT_ANSWERS_COUNT", correctAnswersCount);
        pass_number_of_corrected_answers.putExtra("SELECTED_SUBJECT", subject);
        pass_number_of_corrected_answers.putExtra("SELECTED_LEVEL", level);
        startActivity(pass_number_of_corrected_answers);
        finish(); // Đóng màn hình
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore state của submit button
        if (submitButton != null) {
            boolean isSubmitButtonEnabled = savedInstanceState.getBoolean("SUBMIT_BUTTON_ENABLED");
            submitButton.setEnabled(isSubmitButtonEnabled);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save data de xoay screen
        outState.putInt("CURRENT_QUESTION_INDEX", currentQuestionIndex);
        outState.putInt("SCORE", score);
        outState.putInt("CORRECT_ANSWERS_COUNT", correctAnswersCount);
        outState.putString("SELECTED_LEVEL", level);
        outState.putString("SELECTED_SUBJECT", subject);
        if (submitButton != null) {
            outState.putBoolean("SUBMIT_BUTTON_ENABLED", submitButton.isEnabled());
        }
    }

}