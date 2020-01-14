package com.example.best_aid_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityQuestion extends AppCompatActivity {

    String des , id;
    EditText etAns ;
    Button btnAnsSubmit ;
    String key = "post_comment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();

        des = intent.getStringExtra("question");
        id = intent.getStringExtra("id");

        TextView tvQuestion = findViewById(R.id.tvQuestionsSingle);
        TextView tvId = findViewById(R.id.tvId);
        etAns = findViewById(R.id.etAnswer);
        btnAnsSubmit = findViewById(R.id.btnAnsSubmit);
        tvQuestion.setText(des);
        tvId.setText(id);

        btnAnsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postComment();
            }
        });
        //getIncoming();
    }

    private void postComment()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        final String tokeRecieve = sharedPreferences.getString("token", null);
        final String idRecieve = sharedPreferences.getString("id", null);

        final String answer = etAns.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, "https://bestaidbd.com/app/API/post_comment.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");

                            if (status.equals("1"))
                            {
                                Toast.makeText(ActivityQuestion.this,message,Toast.LENGTH_SHORT).show();

                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            //    Toast.makeText(ActivityRegistration.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //    Toast.makeText(ActivityRegistration.this,"Register Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("user_id",idRecieve);
                params.put("token",tokeRecieve);
                params.put("comment",answer);
                params.put("post",key);
                params.put("question_id",id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
