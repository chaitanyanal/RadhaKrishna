package com.example.chaitanya.radhakrishna.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chaitanya.radhakrishna.Model.LoginBean;
import com.example.chaitanya.radhakrishna.R;
import com.example.chaitanya.radhakrishna.Util.SharedPref;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ImageView mimageview;
    EditText minput_email,minput_password;
    AppCompatButton AppCompatButton;
    TextView link_signup;
    JSONArray jsonArray;
    Context context;
    LoginBean loginBean;
    ProgressDialog progressDialog;

    String Url="http://radhakrishna.smarksystechnologies.com/api/login";
    RequestQueue requestQueue;
    String mRequestBody;

    private String mUserPassword, mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context= this;

        mimageview=findViewById(R.id.imageview);
        minput_email=findViewById(R.id.input_email);
        minput_password=findViewById(R.id.input_password);
        link_signup=findViewById(R.id.link_signup);

        AppCompatButton=findViewById(R.id.btn_login);

        jsonArray = new JSONArray();

        if (SharedPref.getInstance(LoginActivity.this).getRememberLogin() == true) {

           // mCheckBoxRememberMe.setChecked(true);
            minput_email.setText(SharedPref.getInstance(LoginActivity.this).getUsername());
            minput_password.setText(SharedPref.getInstance(LoginActivity.this).getPassword());

        } else {

            //mCheckBoxRememberMe.setChecked(false);
            minput_email.setText("");
            minput_password.setText("");
        }

        AppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NetworkUtil.getConnectivityStatus(LoginActivity.this) != NetworkUtil.TYPE_NOT_CONNECTED) {

                    mUserName = minput_email.getText().toString().trim();
                    mUserPassword = minput_password.getText().toString().trim();


                    loginBean= new LoginBean();
                    loginBean.setMob_no(mUserName);
                    loginBean.setPassword(mUserPassword);

                    Gson gson= new Gson();
                    String requestString="";
                    requestString=gson.toJson(loginBean);

                    progressDialog= new ProgressDialog(context);
                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("Please wait");
                    progressDialog.show();

                    Ion.with(context)
                            .load(Url)
                            .setStringBody(requestString)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                    try {
                                        JSONObject jsonObject= new JSONObject(String.valueOf(result));
                                        String code= jsonObject.getString("code");
                                        progressDialog.dismiss();

                                        if(code.equals("100"))
                                        {
                                            Toast.makeText(LoginActivity.this, "Login Success..", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(LoginActivity.this, OTPActivity.class);
                                            intent.putExtra("MOBILENO", mUserName);
                                            startActivity(intent);
                                        }
                                        else if(code.equals("101"))
                                        {
                                            Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();

                                        }

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });





/*
                    StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {

                            Log.e("res",s);


                            Intent intent=new Intent(LoginActivity.this,OTPActivity .class);
                            startActivity(intent);

                           */
/* if(s.equals("success")){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                            }*//*

                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(LoginActivity.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("email", minput_email.getText().toString());
                            parameters.put("password", minput_password.getText().toString());
                            return parameters;
                        }
                    };

                    RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
                    rQueue.add(request);
                    */
                }


                // checkLogin(mUserName, mUserPassword);

                else {
                    Toast.makeText(LoginActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                }
            }


        });

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

   /* private void checkLogin(final String email, final String pass) {

      //  final ProgressDialog mProgressDialog = ProgressDialog.show(LoginActivity.this, "", "Loading...", false, false);

        StringRequest sr = new StringRequest(Request.Method.POST,Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("login response",response);

                if(response.equals("success")){
                    Toast.makeText(LoginActivity.this, "login sucessfull...", Toast.LENGTH_SHORT).show();
                }

                *//*try{
                    JSONObject object = new JSONObject(response);
                    Log.d("test","Login response : "+object);


                    if (object.getInt("success")==1) {
                        SharedPref.getInstance(LoginActivity.this).setIsLogined(true);
                        SharedPref.getInstance(LoginActivity.this).setUsername(minput_email.getText().toString());
                        SharedPref.getInstance(LoginActivity.this).setPassword(minput_password.getText().toString().trim());

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid login credentials...Please try again!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }*//*

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("test", "error for thread login...." + error);
               *//* if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }*//*
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("user", email);
                params.put("pass", pass);

                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }*/
}
