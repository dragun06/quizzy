package fr.atelier.quizzy.course;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

import fr.atelier.quizzy.R;

public class MainCourseActivity extends AppCompatActivity implements CourseAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

        final String section =  getIntent().getExtras().getString("section");
        File file = new File(this.getExternalFilesDir("external"), "seen.json");
        ListCourse listC = new ListCourse();
        CourseAdapter adapter = new CourseAdapter(this, listC, file);

        changeTitle(section);
        listC.construireListe(this, section);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
        adapter.addListener(this);
    }

    @Override
    public void onClickCourse(Course item) {
        Intent intent = new Intent(MainCourseActivity.this, CourseActivity.class);
        intent.putExtra("course", item);
        startActivity(intent);
    }

    public void changeTitle(String section) {
        if (section.equals("coursesCM1Hist")) {
            this.setTitle("CM1  Histoire");
        } else if (section.equals("coursesCM1Geo")) {
            this.setTitle("CM1  Géographie");
        } else if (section.equals("coursesCM2Hist")) {
            this.setTitle("CM2  Histoire");
        } else {
            this.setTitle("CM2  Géographie");
        }
    }
}