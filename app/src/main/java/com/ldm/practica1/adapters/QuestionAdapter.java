package com.ldm.practica1.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import com.ldm.practica1.R;
import com.ldm.practica1.models.Question;
import com.ldm.practica1.utils.AnswerChecker;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questionList;

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
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
        private Context context;

        public ViewHolder(Context context,
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

            // store the context
            this.context = context;

            // attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Question question = questionList.get(position);
                String message = "Category: " + question.getCategory() + "\n" + "Difficulty: " + question.getDifficulty();

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // usually involves inflating a layout from XML and returning the holder
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View questionView = layoutInflater.inflate(R.layout.item_question, parent, false);
        ViewHolder questionViewHolder = new ViewHolder(context, questionView);

        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // involves populating data into the item through holder
        Question question = questionList.get(position);

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

        // set Button
        Button checkAnswerButton = holder.checkButton;
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answeredRadioButtonId = radioGroupAnswers.getCheckedRadioButtonId();
                if (answeredRadioButtonId != -1) {
                    RadioButton answer = radioGroupAnswers.findViewById(answeredRadioButtonId);
                    boolean check = AnswerChecker.CheckCorrectAnswer(answer.getText().toString(), question);

                    if (check){
                        Toast.makeText(holder.context, "Correct answer!", Toast.LENGTH_LONG).show();
                        answer.setTextColor(Color.GREEN);
                    }
                    else
                        Toast.makeText(holder.context, "Incorrect answer!", Toast.LENGTH_LONG).show();

                    for (int i = 0; i < radioGroupAnswers.getChildCount(); i++) {
                        radioGroupAnswers.getChildAt(i).setEnabled(false);
                    }

                } else {
                    Toast.makeText(holder.context, "You have to select an answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setQuestionsAndNotify(List<Question> questionList){
        this.questionList.clear();
        this.questionList = questionList;

        notifyDataSetChanged();
    }
}
