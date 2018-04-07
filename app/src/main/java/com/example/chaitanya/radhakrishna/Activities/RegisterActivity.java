package com.example.chaitanya.radhakrishna.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chaitanya.radhakrishna.Model.RegistrationPojo;
import com.example.chaitanya.radhakrishna.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText fname,Lname,Mobno,Pass;
    AppCompatButton btn_register;
    RequestQueue queue;
    String url="http://radhakrishna.smarksystechnologies.com/api/registration";
    String password;

    RegistrationPojo registrationPojo;

    private static final String TAG = "RegisterActivity.java";

    JSONObject jsonObject;
    Context context;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context=this;

        fname = findViewById(R.id.fname);
        Lname = findViewById(R.id.Lname);
        Mobno = findViewById(R.id.Mobno);
        Pass = findViewById(R.id.Pass);
        btn_register = findViewById(R.id.btn_register);

      //  queue = Volley.newRequestQueue(RegisterActivity.this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               call();


            }
        });
    }

            private void call() {

                 /*final String fnme=fname.getText().toString();
                 final String lnme=Lname.getText().toString();
                 final String mono=Mobno.getText().toString();
                 final String passs=Pass.getText().toString();*/


                jsonObject = new JSONObject();

                try {

                    password=Pass.getText().toString();

                    registrationPojo= new RegistrationPojo();
                    registrationPojo.setFname(fname.getText().toString());
                    registrationPojo.setLname(Lname.getText().toString());
                    registrationPojo.setMob_no(Mobno.getText().toString());
                    registrationPojo.setPassword(password);

                    Gson gson= new Gson();
                    String requestString= "";
                    requestString= gson.toJson(registrationPojo);
                  //  Toast.makeText(this, ""+requestString, Toast.LENGTH_SHORT).show();

                    progressDialog= new ProgressDialog(context);
                    progressDialog.setCancelable(false);
                    progressDialog.setTitle("Please wait");
                    progressDialog.show();

                    Ion.with(context)
                            .load(url)
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
                                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                            intent.putExtra("PASSWORD", password);
                                            startActivity(intent);
                                        }

                                    } catch (JSONException e1) {
                                        e1.printStackTrace();
                                    }


                                }
                            });

                   /* jsonObject.put("fname", fname.getText().toString());
                    jsonObject.put("lname", Lname.getText().toString());
                    jsonObject.put("mob_no", Mobno.getText().toString());
                    jsonObject.put("password", Pass.getText().toString());*/

                } catch (Exception e) {
                    e.printStackTrace();
                }



                /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String message, code, data;
                            message = jsonObject.getString("message");
                            code = jsonObject.getString("code");
                           // data = jsonObject.getString("data");

                            if (code.equals("100")&&  message.equals("success")) {

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Volley.newRequestQueue(this).add(jsonObjectRequest);*/




/*                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response Data", response);


                                Toast.makeText(RegisterActivity.this, "Account Created Successfully......", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String,String> params = new HashMap<>();
                        //params.put("fname","Shubham");
                       // params.put("lname","Kakde");
                       // params.put("mob_no","9049342407");
                       // params.put("password","1234");
                       *//* params.put("","\t\"fname\": \"AAA\",\n" +
                                "\t\"lname\": \"AAA\",\n" +
                                "\t\"mob_no\": \"9049342407\",\n" +
                                "\t\"password\": \"1234\"\n" +
                                "");*//*


                        params.put("fname ", fname.getText().toString().trim());
                        params.put("lname ", Lname.getText().toString().trim());
                        params.put("mob_no ", Mobno.getText().toString().trim());
                        params.put("password ", Pass.getText().toString().trim());

                       // Log.e("fname",fname.getText().toString().trim());

                        Log.e("param", String.valueOf(params));


                    *//*    JSONObject jsonObject=new JSONObject();
                        try {
                            jsonObject.put("data",params.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*//*
                        return params;

                    }

                };

                //queue.add(postRequest);

                postRequest.setRetryPolicy(
                        new DefaultRetryPolicy(
                                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                                0,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
              //  getRequestQueue().add(postRequest);
               // queue.add(postRequest);
                VolleySingleton.getInstance(RegisterActivity.this).addToRequestQueue(postRequest);
            }*/

            }



    }
