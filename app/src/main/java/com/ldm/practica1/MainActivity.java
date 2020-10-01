package com.ldm.practica1;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
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
    private List<Question> questionList = null;
    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call API GET method
        findQuestions("10");
    }

    private void findQuestions(String limit){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://quizapi.io/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        QuestionsAPI api = retrofit.create(QuestionsAPI.class);

        Call<List<Question>> call = api.findQuestions(limit);

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                try {
                    if (response.isSuccessful()) {
                        questionList = response.body();

                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        textView = findViewById(R.id.firstTextView);
                        textView.setText(questionList.get(0).toString());

                        if (questionList != null) {
                            for (Question q : questionList) {
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