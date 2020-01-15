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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin ;
    TextView tvSignUp ;
    EditText etLoginEmail , etLoginPassword ;
    TextView tvShow ;
    String key = "authenticate";
    SharedPreferences sharedPreferences ;
    String tokeRecieve ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        tokeRecieve = sharedPreferences.getString("token", null);

        if(!tokeRecieve.isEmpty())
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }


            setContentView(R.layout.activity_login);
            btnLogin=findViewById(R.id.btnLogin);
            tvSignUp = findViewById(R.id.tvSignUp);
            etLoginEmail = findViewById(R.id.etLoginEmail);
            etLoginPassword = findViewById(R.id.etLoginPassword);



            tvShow = findViewById(R.id.tvShow);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //            String email = etLoginEmail.getText().toString().trim();
                    //            String password = etLoginPassword.getText().toString().trim();
                    login();

                }
            });

    }

    private void login() {

        final String email = etLoginEmail.getText().toString().trim();
        final String password = etLoginPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://bestaidbd.com/app/API/user_authenticate.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String token = jsonObject.getString("token");
                            JSONArray dataArray = jsonObject.getJSONArray("user_authenticate");

                            if (status.equals("1")) {

                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    String id = dataobj.getString("user_id");
                                    //              String token = dataobj.getString("token");
                                    String type = dataobj.getString("user_type_Name");

                                    sharedPreferences.edit().putString("token", token).putString("id", id).apply();


                                    Toast.makeText(getApplicationContext(),"id :"+token,Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);

                                    tvShow.setText(id);

                                }

                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //        adapter.notifyDataSetChanged();
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("login", key);
                //   params.put("questions_description",question);

                return params;
            }
        };

        //  Volley.newRequestQueue(this).add(stringRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
