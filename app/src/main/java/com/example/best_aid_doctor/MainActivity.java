package com.example.best_aid_doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
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
import com.example.best_aid_doctor.Adapter.GetQstnAdapter;
import com.example.best_aid_doctor.Model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;
    String postN = "post_question";

    TextView tvClick ;
    String key = "post_comment";
    Question question;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private ArrayList<Question> questionList;
    private RecyclerView.Adapter adapter;


    private TextView tvQuestions;
    private EditText etQuestion ;
    Button btnSubmit;
    String type = "give_him_the_fuck_of_all";
    String qu ;
    String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.rvQuestion);


        questionList = new ArrayList<>();
        adapter = new GetQstnAdapter(getApplicationContext(),questionList);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        seeQuestions();

    }

    private void seeQuestions() {


        SharedPreferences sharedPreferences = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        final String tokeRecieve = sharedPreferences.getString("token", null);
        final String idRecieve = sharedPreferences.getString("id", null);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://bestaidbd.com/app/API/get_questions.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String comment = null;

                            JSONArray dataArray = jsonObject.getJSONArray("questions");
                            for (int i = 0; i < dataArray.length(); i++) {



                                JSONObject dataobj = dataArray.getJSONObject(i);


                                String questions = dataobj.getString("questions_description");
                                String id = dataobj.getString("questions_id");
                                JSONArray commentArray = dataobj.getJSONArray("comments");
                                if(commentArray.length()!=0)
                                {
                                    for(int j=0;j<commentArray.length();j++)
                                    {

                                        JSONObject commentobj = commentArray.getJSONObject(j);
                                        comment = commentobj.getString("comment_description");
                                        Toast.makeText(MainActivity.this, comment, Toast.LENGTH_SHORT).show();






                                    } 
                                }
                                
                                question = new Question(questions,id,comment);

                                questionList.add(question);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("get_questions", type);
                params.put("user_id",idRecieve);
                params.put("token",tokeRecieve);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

}
