package fr.atelier.quizzy.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.File;

import fr.atelier.quizzy.json.JSON;
import fr.atelier.quizzy.question.MainQuestionActivity;
import fr.atelier.quizzy.R;

public class CourseActivity extends Activity {
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        final Course course =  getIntent().getExtras().getParcelable("course");
        final TextView tvTitle= findViewById(R.id.title);
        final ImageView ivImage1 = findViewById(R.id.image1);
        final TextView tvParagraph1 = findViewById(R.id.paragraph1);
        final TextView tvParagraph2 = findViewById(R.id.paragraph2);
        final ImageView ivImage2 = findViewById(R.id.image2);
        final TextView tvParagraph3 = findViewById(R.id.paragraph3);

        this.course = course;
        tvTitle.setText(course.getTitle());
        ivImage1.setImageResource(course.getImage1());
        tvParagraph1.setText(course.getParagraph1());
        tvParagraph2.setText(course.getParagraph2());
        ivImage2.setImageResource(course.getImage2());
        tvParagraph3.setText(course.getParagraph3());
    }

    public void onClickStartQuizz(View view) throws JSONException {
        File file = new File(this.getExternalFilesDir("external"), "seen.json");
        JSON.JSONWriterSeen(file, course.getId());

        Intent intent = new Intent(CourseActivity.this, MainQuestionActivity.class);
        intent.putExtra("course", course);
        startActivity(intent);
    }
}