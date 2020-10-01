package com.ldm.practica1;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.ldm.practica1.interfaces.QuestionsAPI;
import com.ldm.practica1.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call API GET method
        find();
    }

    private void find(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://quizapi.io/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        QuestionsAPI api = retrofit.create(QuestionsAPI.class);

        Call<List<Question>> call = api.findQuestions();

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                try {
                    if (response.isSuccessful()){
                        List<Question> questionList = response.body();

                        if (questionList != null){
                        for (Question q : questionList){
                                Log.println(Log.VERBOSE, "TESTING", q.toString());
                            }
                        }
                    }
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}