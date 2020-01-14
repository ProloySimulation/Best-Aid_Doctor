package com.example.best_aid_doctor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.best_aid_doctor.ActivityQuestion;
import com.example.best_aid_doctor.Model.Question;
import com.example.best_aid_doctor.R;

import java.util.ArrayList;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GetQstnAdapter.ViewHolder holder, final int position) {
        Question question = questionList.get(position);

        holder.tvQuestion.setText(question.getDescription());
        holder.tvComment.setText(question.getComment());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityQuestion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("question", questionList.get(position).getDescription());
                intent.putExtra("id", questionList.get(position).getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvQuestion , tvComment;
        LinearLayout parentLayout ;

        public ViewHolder(View itemView) {
            super(itemView);

            tvQuestion = itemView.findViewById(R.id.tvQuestions);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            tvComment = itemView.findViewById(R.id.tvComment);


        }
    }
}
