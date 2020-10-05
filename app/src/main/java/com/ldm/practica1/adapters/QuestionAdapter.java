package com.ldm.practica1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ldm.practica1.R;
import com.ldm.practica1.models.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView questionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            questionTextView = itemView.findViewById(R.id.questionTextView);
        }
    }

    private List<Question> questionList;

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // usually involves inflating a layout from XML and returning the holder
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View questionView = layoutInflater.inflate(R.layout.item_question, parent, false);
        ViewHolder questionViewHolder = new ViewHolder(questionView);

        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // involves populating data into the item through holder
        Question question = questionList.get(position);

        // set item views
        TextView textView = holder.questionTextView;
        textView.setText(question.getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
