package quizz_app.quizz_project.quizz;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends BaseAdapter {
//    Khai báo listSubject
    private ArrayList<Subject> listSubject;
    public SubjectAdapter(ArrayList<Subject> listSubject ){
        this.listSubject = listSubject;
    }
//  Lấy số lượng subject
    @Override
    public int getCount() {
        return listSubject.size();
    }
//  Lấy vij trí subject
    @Override
    public Object getItem(int position) {
        return listSubject.get(position);
    }
//  Lấy id của subject
    @Override
    public long getItemId(int position) {
        return listSubject.get(position).getSubId();
    }
    //  render subject, subject description và img
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem;
        if (view == null) {
            viewItem = View.inflate(viewGroup.getContext(),R.layout.frame2_subject, null);
        }else{
            viewItem = view;
        }
        Subject subject = (Subject) getItem(i);

        ((TextView) viewItem.findViewById(R.id.subDes)).setText(subject.getSubDes());
        ((TextView) viewItem.findViewById(R.id.subName)).setText(subject.getNameSub());
        ImageView img =  viewItem.findViewById(R.id.imgSubject);
        img.setImageResource(subject.getSrcImgPosition());
        return viewItem;
    }
}
