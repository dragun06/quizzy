package fr.atelier.quizzy.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.atelier.quizzy.R;

public class QuestionAdapter extends BaseAdapter {
    private QuestionAdapterListener questionListener;
    private ListQuestion mListQ;
    private Context mContext;
    private LayoutInflater mInflater;

    public QuestionAdapter(Context context, ListQuestion aListQ) {
        mContext = context;
        mListQ = aListQ;
        mInflater = LayoutInflater.from(mContext);
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
        LinearLayout layoutItem = (LinearLayout) (convertView == null ? mInflater.inflate(R.layout.question_layout, parent, false) : convertView);

        TextView question = layoutItem.findViewById(R.id.question);
        EditText answer = layoutItem.findViewById(R.id.answer);
        question.setText(mListQ.get(position).getQuestion());

        answer.setTag(position);
        answer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    questionListener.onFocusQuestion(position, answer.getText().toString());
                }
            }
        });
        return layoutItem;
    }

    public void addListener(QuestionAdapterListener aListener) {
        questionListener = aListener;
    }
}