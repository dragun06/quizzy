package fr.atelier.quizzy.answer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;

import fr.atelier.quizzy.R;
import fr.atelier.quizzy.question.ListQuestion;

public class AnswerAdapter extends BaseAdapter {
    private AnswerAdapterListener answerListener;
    private ListQuestion mListQ;
    private Context mContext;
    private LayoutInflater mInflater;
    private HashMap<Integer, String> mAnswer;
    private HashMap<String, Double> listPoint;

    public AnswerAdapter(Context context, ListQuestion aListQ, HashMap<Integer, String> answer) {
        mContext = context;
        mListQ = aListQ;
        mInflater = LayoutInflater.from(mContext);
        mAnswer = answer;
        listPoint = new HashMap<>();
    }

    public int getCount() {
        return mListQ.size();
    }

    public Object getItem(int position) {
        return mListQ.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem = (LinearLayout) (convertView == null ? mInflater.inflate(R.layout.answer_layout, parent, false) : convertView);

        TextView question = layoutItem.findViewById(R.id.question);
        TextView answer = layoutItem.findViewById(R.id.answer);

        question.setText(mListQ.get(position).getQuestion());
        answer.setText(mListQ.get(position).getAnswerPrint());

        if (mAnswer.get(position) == null) {
            answer.setBackgroundColor(Color.rgb(255,0,0));
        } else if (mAnswer.get(position).contains(mListQ.get(position).getAnswer())) {
            answer.setBackgroundColor(Color.rgb(0,255,0));
            this.listPoint.put(mAnswer.get(position), 1.0);
        } else if (Normalizer.normalize(mAnswer.get(position).toLowerCase(Locale.ROOT), Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "").contains(Normalizer.normalize(mListQ.get(position).getAnswer().toLowerCase(Locale.ROOT), Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", ""))) {
            answer.setBackgroundColor(Color.rgb(255,165,0));
            this.listPoint.put(mAnswer.get(position), 0.5);
        } else {
            answer.setBackgroundColor(Color.rgb(255,0,0));
            this.listPoint.put(mAnswer.get(position), 0.0);
        }

        question.setTag(position);
        question.setOnClickListener(click -> {
            answerListener.onClickAnswer(mAnswer.get(position), this.listPoint);
        });

        return layoutItem;
    }

    public void addListener(AnswerAdapterListener aListener) {
        answerListener = aListener;
    }
}