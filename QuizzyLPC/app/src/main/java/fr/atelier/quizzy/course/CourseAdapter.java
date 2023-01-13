package fr.atelier.quizzy.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.File;

import fr.atelier.quizzy.R;

public class CourseAdapter extends BaseAdapter {
    private CourseAdapterListener courseListener;
    private ListCourse mListC;
    private Context mContext;
    private LayoutInflater mInflater;
    final File file;
    private JSONArray jsonArray;

    public CourseAdapter(Context context, ListCourse aListC, File file) {
        mContext = context;
        mListC = aListC;
        mInflater = LayoutInflater.from(mContext);
        this.file = file;
    }

    public int getCount() {
        return mListC.size();
    }

    public Object getItem(int position) {
        return mListC.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem = (LinearLayout) (convertView == null ? mInflater.inflate(R.layout.course_layout, parent, false) : convertView);
        ImageView course_img = layoutItem.findViewById(R.id.imageCourse);
        TextView course_title = layoutItem.findViewById(R.id.title);

        course_img.setImageResource(mListC.get(position).getImage1());
        course_title.setText(mListC.get(position).getTitle());

        /*try {
            course_title.setBackgroundColor(isSeen(file, mListC.get(position).getId()) ? Color.LTGRAY : Color.BLUE);
            course_title.setTextColor(isSeen(file, mListC.get(position).getId()) ? Color.GRAY : Color.GREEN);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        course_title.setTag(position);
        course_title.setOnClickListener(click -> {
            courseListener.onClickCourse(mListC.get(position));
        });

        return layoutItem;
    }

    public void addListener(CourseAdapterListener aListener) {
        courseListener = aListener;
    }
}