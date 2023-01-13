package fr.atelier.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.atelier.quizzy.course.MainCourseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickHistoireCM1(View view) {
        Intent intent = new Intent(MainActivity.this, MainCourseActivity.class);
        intent.putExtra("section", "coursesCM1Hist");
        startActivity(intent);
    }

    public void onClickHistoireCM2(View view) {
        Intent intent = new Intent(MainActivity.this, MainCourseActivity.class);
        intent.putExtra("section", "coursesCM2Hist");
        startActivity(intent);
    }

    public void onClickGeographieCM1(View view) {
        Intent intent = new Intent(MainActivity.this, MainCourseActivity.class);
        intent.putExtra("section", "coursesCM1Geo");
        startActivity(intent);
    }

    public void onClickGeographieCM2(View view) {
        Intent intent = new Intent(MainActivity.this, MainCourseActivity.class);
        intent.putExtra("section", "coursesCM2Geo");
        startActivity(intent);
    }
}