package com.example.best_aid_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityQuestion extends AppCompatActivity {

    String des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        des = intent.getStringExtra("question");
        TextView tvQuestion = findViewById(R.id.tvQuestionsSingle);
        tvQuestion.setText(des);
        //getIncoming();
    }

    private void getIncoming(){

        if(getIntent().hasExtra("question")){

            String question = getIntent().getStringExtra("question");

            setImage(question);
        }
    }

    private void setImage(String question){


    }
}
