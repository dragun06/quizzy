package fr.atelier.quizzy.course;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fr.atelier.quizzy.question.Question;

public class ListCourse {
    static ArrayList<Course> listCourse;

    public ListCourse() {
        listCourse = new ArrayList<>();
    }

    public int size() {
        return listCourse.size();
    }

    public static Course get(int pos) {
        return listCourse.get(pos);
    }

    public void construireListe(Context context, String section) {
        try {
            JSONArray jsonArray = new JSONArray(getJSONFromAsset(context, section));

            for (int i = 0; i < jsonArray.length(); i++) {
                listCourse.add(getCoursesFromJSONObject(jsonArray.getJSONObject(i), context));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJSONFromAsset(Context context, String section) {
        String json;

        try {
            InputStream is = context.getAssets().open(section + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    private Course getCoursesFromJSONObject(JSONObject jsonObject, Context context) throws JSONException {
        int id = jsonObject.getInt("id");
        String image1 = jsonObject.getString("image1");
        String image2 = jsonObject.getString("image2");
        String title = jsonObject.getString("title");
        String paragraph1 = jsonObject.getString("paragraph1");
        String paragraph2 = jsonObject.getString("paragraph2");
        String paragraph3 = jsonObject.getString("paragraph3");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");
        ArrayList<Question> listQuestion = new ArrayList<>();

        int image1Id = context.getResources().getIdentifier(image1, "mipmap",  context.getPackageName());
        int image2Id = context.getResources().getIdentifier(image2, "mipmap",  context.getPackageName());

        for (int i = 0; i < jsonObject.getJSONArray("quizz").length(); i++) {
            Question questionAnswer = new Question(
                    jsonObject.getJSONArray("quizz").getJSONObject(i).getString("question"),
                    jsonObject.getJSONArray("quizz").getJSONObject(i).getString("answer"),
                    jsonObject.getJSONArray("quizz").getJSONObject(i).getString("answerPrint")
            );
            listQuestion.add(questionAnswer);
        }

        return new Course(id, image1Id, image2Id, title, paragraph1, paragraph2, paragraph3, latitude, longitude, listQuestion);
    }
}