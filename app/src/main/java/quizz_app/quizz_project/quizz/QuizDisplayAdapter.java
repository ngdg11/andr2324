package quizz_app.quizz_project.quizz;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class QuizDisplayAdapter extends BaseAdapter {

    private ArrayList<QuizDisplay> listQuiz;
    public QuizDisplayAdapter(ArrayList<QuizDisplay> listQuiz) {
        this.listQuiz = listQuiz;
    }


    @Override
    public int getCount() {
        return listQuiz.size();
    }

    @Override
    public Object getItem(int position) {
        return listQuiz.get(position);
    }

    @Override
    public long getItemId(int i) {
        return listQuiz.get(i).getQuizId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null) {
            viewItem = View.inflate(viewGroup.getContext(),R.layout.all_quiz_item, null);
        }else{
            viewItem = view;
        }
        QuizDisplay quiz = listQuiz.get(i);
        ((TextView) viewItem.findViewById(R.id.subject_item)).setText(quiz.getSubject());
        ((TextView) viewItem.findViewById(R.id.question)).setText(quiz.getQuiz());

        return viewItem;
    }
}
