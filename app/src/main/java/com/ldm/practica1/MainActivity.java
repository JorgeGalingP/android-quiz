package com.ldm.practica1;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private ProgressBar questionsProgressBar;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        questionsProgressBar = findViewById(R.id.questionsProgress);
        questionsProgressBar.setVisibility(View.INVISIBLE);

        questionList = new ArrayList<>();

        // set RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // set Adapter
        questionAdapter = new QuestionAdapter(this, questionList);
        recyclerView.setAdapter(questionAdapter);

        // set LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // set Questions
        setQuestions();

        // set Fab
        FloatingActionButton fab = findViewById(R.id.reloadFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadActivity();
            }
        });
    }

    protected void reloadActivity() {
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void setQuestions(){
        Call<List<Question>> call = ApiClient.getQuestionService().getQuestions("5");

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body() != null){
                            questionAdapter.setQuestionsAndNotify(response.body());

                            // set ProgressBars
                            progressBar.setVisibility(View.GONE);
                            questionsProgressBar.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Error response from server.", Toast.LENGTH_SHORT).show();
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