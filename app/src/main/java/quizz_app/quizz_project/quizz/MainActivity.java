package quizz_app.quizz_project.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {
    private String level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       Lấy dữ liệu từ string để hiển thị
        TextView intro = findViewById(R.id.introduction);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intro.setText(Html.fromHtml(getString(R.string.intro), Html.FROM_HTML_MODE_COMPACT));
        }
//      Vào frame2
        Button start = findViewById(R.id.startBtn);
        start.setOnClickListener(view ->{
            Intent action = new Intent(this, Frame2.class);
            startActivity(action);

        });
    }
}