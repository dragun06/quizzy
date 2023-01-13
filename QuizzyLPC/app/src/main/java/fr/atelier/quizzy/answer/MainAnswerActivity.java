package fr.atelier.quizzy.answer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import fr.atelier.quizzy.MainActivity;
import fr.atelier.quizzy.R;
import fr.atelier.quizzy.course.Course;
import fr.atelier.quizzy.map.MapActivity;
import fr.atelier.quizzy.question.ListQuestion;

public class MainAnswerActivity extends AppCompatActivity implements AnswerAdapterListener {
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_answer);

        HashMap<Integer, String> answer = (HashMap<Integer, String>) getIntent().getExtras().getSerializable("answer");
        System.out.println(answer);
        final Course course =  getIntent().getExtras().getParcelable("course");
        this.course = course;
        this.setTitle(course.getTitle());
        ListQuestion listQ = new ListQuestion();
        listQ.construireListe(course.getListQuestion());
        AnswerAdapter adapter = new AnswerAdapter(this, listQ, answer);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);
        adapter.addListener(this);
    }

    public void onClickEnd(View view) {
        Intent intent = new Intent(MainAnswerActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickAnswer(String answer, HashMap<String, Double> listPoint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(answer);

        if (answer == null) {
            builder.setMessage("Vous n'avez pas répondu! " + 0 + "pt");
        } else if (listPoint.get(answer) == 0.0) {
            builder.setMessage("Ce n'est pas la bonne réponse. " + 0 + "pt");
        } else if (listPoint.get(answer) == 0.5) {
            builder.setMessage("Ce n'est pas le bon orthographe. " + 0.5 + "pt");
        } else {
            builder.setMessage("Bravo, vous avez bien répondu. " + 1 + "pt");
        }

        builder.setNegativeButton("Localisation", null);
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAnswerActivity.this, MapActivity.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });
    }
}