package com.ldm.practica1.interfaces;

import com.ldm.practica1.models.Question;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface QuestionsAPI {

    @Headers({"X-Api-Key: 9TYKinH2FetAWIHJrauI6wAUqNCLmUX1WyVOpT3Q"})
    @GET("api/v1/questions?limit=10")
    public Call<List<Question>> findQuestions();
}
