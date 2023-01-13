package fr.atelier.quizzy.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.atelier.quizzy.R;
import fr.atelier.quizzy.answer.MainAnswerActivity;
import fr.atelier.quizzy.course.Course;

public class MainQuestionActivity extends AppCompatActivity implements QuestionAdapterListener {
    private Course course;
    private Map<Integer, String> answer = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);

        final Course course =  getIntent().getExtras().getParcelable("course");
        this.course = course;
        this.setTitle(course.getTitle());
        ListQuestion listQ = new ListQuestion();
        listQ.construireListe(course.getListQuestion());
        QuestionAdapter adapter = new QuestionAdapter(this, listQ);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
        adapter.addListener(this);
    }

    public void onClickEnd(View view) {
        Intent intent = new Intent(MainQuestionActivity.this, MainAnswerActivity.class);
        intent.putExtra("course", course);
        intent.putExtra("answer", (Serializable) answer);
        startActivity(intent);
    }

    @Override
    public void onFocusQuestion(int pos, String answer) {
        this.answer.put(pos, answer);
    }
}