package fr.atelier.quizzy;

import java.util.List;

import fr.atelier.quizzy.course.Course;

public interface api_service {

    @GET("/CM1Geo")
    Call<List<Course>> getCM1Geo();

    @GET("/CM1Hist")
    Call<List<Course>> getCM1Hist();

    @GET("/CM2Geo")
    Call<List<Course>> getCM2Geo();

    @GET("/CM2Hist")
    Call<List<Course>> getCM2Hist();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    api_service service = retrofit.create(api_service.class);
}
