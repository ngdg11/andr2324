package quizz_app.quizz_project.quizz;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionDisplayAdapterFrame5 extends BaseAdapter {

    private ArrayList<QuestionDisplayFrame5> listQuestion;

    public QuestionDisplayAdapterFrame5(ArrayList<QuestionDisplayFrame5> listQuestion) {
        this.listQuestion = listQuestion;
    }


    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return listQuestion.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listQuestion.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null) {
            viewItem = View.inflate(viewGroup.getContext(),R.layout.all_quiz_item_frame5, null);
        }else{
            viewItem = view;
        }

        QuestionDisplayFrame5 question = (QuestionDisplayFrame5) getItem(i);
        ((TextView) viewItem.findViewById(R.id.question)).setText(question.getQuestion());

        return viewItem;
    }
}
