package com.ldm.practica1;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ldm.practica1.adapters.QuestionAdapter;
import com.ldm.practica1.models.Question;
import com.ldm.practica1.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private ProgressBar progressBar;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        questionList = new ArrayList<>();

        // call API GET method
        Call<List<Question>> call = ApiClient.getQuestionService().getQuestions("10");
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                try {
                    if (response.isSuccessful()) {
                        questionList = response.body();

                        // set ProgressBar
                        progressBar.setVisibility(View.INVISIBLE);

                        // set RecyclerView
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setHasFixedSize(true);

                        // set Adapter
                        questionAdapter = new QuestionAdapter(questionList);
                        recyclerView.setAdapter(questionAdapter);

                        // set the LinearLayoutManager
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
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