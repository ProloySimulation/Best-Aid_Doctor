package com.example.best_aid_doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.best_aid_doctor.Model.Question;
import com.example.best_aid_doctor.R;

import java.util.List;

public class GetQstnAdapter extends RecyclerView.Adapter <GetQstnAdapter.ViewHolder> {

    private Context context;
    private List<Question> questionList;


    public GetQstnAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GetQstnAdapter.ViewHolder holder, int position) {
        Question question = questionList.get(position);

        holder.tvQuestion.setText(question.getDescription());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvQuestion;

        public ViewHolder(View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tvQuestions);


        }
    }
}
