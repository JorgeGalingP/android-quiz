package com.ldm.practica1.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.ldm.practica1.MainActivity;
import com.ldm.practica1.R;
import com.ldm.practica1.ResultActivity;
import com.ldm.practica1.models.Question;
import com.ldm.practica1.models.Tag;
import com.ldm.practica1.utils.AnswerChecker;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private MainActivity mainActivity;
    private int answeredQuestions;

    public QuestionAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.answeredQuestions = 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView questionTextView;
        public Button checkButton;
        public RadioGroup radioGroup;
        public RadioButton radioButtonA;
        public RadioButton radioButtonB;
        public RadioButton radioButtonC;
        public RadioButton radioButtonD;
        public RadioButton radioButtonE;
        public RadioButton radioButtonF;
        public RadioButton radioButtonG;
        public ChipGroup chipGroup;
        public ProgressBar questionProgressBar;
        private Context context;

        public ViewHolder(MainActivity mainActivity,
                          Context context,
                          View itemView) {
            super(itemView);
            // set views
            questionTextView = itemView.findViewById(R.id.questionTextView);
            checkButton = itemView.findViewById(R.id.check_answer);
            radioGroup = itemView.findViewById(R.id.answers);
            radioButtonA = itemView.findViewById(R.id.answer_a);
            radioButtonB = itemView.findViewById(R.id.answer_b);
            radioButtonC = itemView.findViewById(R.id.answer_c);
            radioButtonD = itemView.findViewById(R.id.answer_d);
            radioButtonE = itemView.findViewById(R.id.answer_e);
            radioButtonF = itemView.findViewById(R.id.answer_f);
            radioButtonG = itemView.findViewById(R.id.answer_g);
            chipGroup = itemView.findViewById(R.id.tagGroup);

            // set MainActivity views
            questionProgressBar = mainActivity.findViewById(R.id.questionsProgress);

            // store the context
            this.context = context;

            // attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Question question = mainActivity.getQuestionList().get(position);

                if (question.getCategory() != null
                    && question.getDifficulty() != null){
                    String message = question.getMessage();

                    if (!message.isEmpty())
                        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // usually involves inflating a layout from XML and returning the holder
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View questionView = layoutInflater.inflate(R.layout.item_question, parent, false);
        ViewHolder questionViewHolder = new ViewHolder(this.mainActivity, context, questionView);

        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // involves populating data into the item through holder
        Question question = mainActivity.getQuestionList().get(position);

        // set TextView
        TextView textView = holder.questionTextView;
        textView.setText(question.getQuestion());

        // set RadioGroup
        RadioGroup radioGroupAnswers = holder.radioGroup;

        // set RadioButton
        RadioButton radioButtonAnswerA = holder.radioButtonA;
        RadioButton radioButtonAnswerB = holder.radioButtonB;
        RadioButton radioButtonAnswerC = holder.radioButtonC;
        RadioButton radioButtonAnswerD = holder.radioButtonD;
        RadioButton radioButtonAnswerE = holder.radioButtonE;
        RadioButton radioButtonAnswerF = holder.radioButtonF;
        RadioButton radioButtonAnswerG = holder.radioButtonG;

        // set Chip Group
        ChipGroup chipGroup = holder.chipGroup;

        // set ProgressBar
        ProgressBar questionProgressBar = holder.questionProgressBar;
        if (question.getAnswers().getAnswer_a() != null){
            radioButtonAnswerA.setVisibility(View.VISIBLE);
            radioButtonAnswerA.setText(question.getAnswers().getAnswer_a());
        }
        if (question.getAnswers().getAnswer_b() != null){
            radioButtonAnswerB.setVisibility(View.VISIBLE);
            radioButtonAnswerB.setText(question.getAnswers().getAnswer_b());
        }
        if (question.getAnswers().getAnswer_c() != null){
            radioButtonAnswerC.setVisibility(View.VISIBLE);
            radioButtonAnswerC.setText(question.getAnswers().getAnswer_c());
        }
        if (question.getAnswers().getAnswer_d() != null){
            radioButtonAnswerD.setVisibility(View.VISIBLE);
            radioButtonAnswerD.setText(question.getAnswers().getAnswer_d());
        }
        if (question.getAnswers().getAnswer_e() != null){
            radioButtonAnswerE.setVisibility(View.VISIBLE);
            radioButtonAnswerE.setText(question.getAnswers().getAnswer_e());
        }
        if (question.getAnswers().getAnswer_f() != null){
            radioButtonAnswerF.setVisibility(View.VISIBLE);
            radioButtonAnswerF.setText(question.getAnswers().getAnswer_f());
        }

        if (position % 2 == 0){
            radioButtonAnswerG.setVisibility(View.VISIBLE);
            radioButtonAnswerE.setText("Wrong answer!");
        }

        // set Chips
        for (Tag tag : question.getTags()){
            Chip chip = new Chip(holder.context);
            chip.setText(tag.getName());
            chip.setChipBackgroundColorResource(R.color.colorAccent);
            chip.setTextColor(Color.WHITE);

            chipGroup.addView(chip);
        }


        // set Button
        Button checkAnswerButton = holder.checkButton;
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answeredRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();

                if (answeredRadioButtonId != -1) {
                    // answer logic
                    RadioButton answer = radioGroupAnswers.findViewById(answeredRadioButtonId);
                    boolean check = AnswerChecker.CheckCorrectAnswer(answer.getText().toString(), question);

                    questionProgressBar.setProgress(questionProgressBar.getProgress() + (Math.round((float)100 / mainActivity.getQuestionList().size())));
                    answeredQuestions += 1;

                    if (check){
                        mainActivity.addPoints();

                        Toast.makeText(holder.context, "Correct answer! Current points: " + mainActivity.getPoints(), Toast.LENGTH_LONG).show();
                        answer.setTextColor(Color.parseColor("#03DAC5"));
                    }
                    else{
                        mainActivity.subtractPoints();
                        if (answeredQuestions != mainActivity.getQuestionCount()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                            builder
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            mainActivity.reloadActivity();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setMessage("Current points: " + mainActivity.getPoints() + ".\nWant to start over?")
                                    .setTitle("Incorrect answer");
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }

                    for (int i = 0; i < radioGroupAnswers.getChildCount(); i++) {
                        ((RadioButton) radioGroupAnswers.getChildAt(i)).setEnabled(false);
                    }

                } else {
                    Toast.makeText(holder.context, "You have to select an answer.", Toast.LENGTH_SHORT).show();
                }

                if (answeredQuestions == mainActivity.getQuestionCount()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                    builder
                            .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(mainActivity, ResultActivity.class);
                                    intent.putExtra("result", String.valueOf(mainActivity.getPoints())); // result parameter

                                    mainActivity.startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })
                            .setMessage(mainActivity.getPoints() + " points.")
                            .setTitle("Game over");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mainActivity.getQuestionList().size();
    }

    public int getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setQuestionsAndNotify(List<Question> questionList){
        mainActivity.getQuestionList().clear();
        mainActivity.setQuestionList(questionList);

        notifyDataSetChanged();
    }
}
