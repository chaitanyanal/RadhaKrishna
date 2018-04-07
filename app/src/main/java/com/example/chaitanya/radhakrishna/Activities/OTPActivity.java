package com.example.chaitanya.radhakrishna.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chaitanya.radhakrishna.Model.OTPModel;
import com.example.chaitanya.radhakrishna.R;
import com.example.chaitanya.radhakrishna.Util.SharedPref;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class OTPActivity extends AppCompatActivity {

    EditText edtotp;
    String mobileNo="";
    Button buttonSubmitOTP;
    OTPModel otpModel;
    Context context;
    ProgressDialog progressDialog;
    SharedPref sharedPref;

    String url="http://radhakrishna.smarksystechnologies.com/api/verify_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        context=this;
        sharedPref= new SharedPref(context);

        mobileNo= getIntent().getStringExtra("MOBILENO");

        edtotp=findViewById(R.id.edtOtp);
        buttonSubmitOTP=findViewById(R.id.btn_submit_otp);

        buttonSubmitOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                otpModel= new OTPModel();
                otpModel.setMob_no(mobileNo);
                otpModel.setOtp(edtotp.getText().toString());

                Gson gson= new Gson();
                String requestString="";
                requestString= gson.toJson(otpModel);

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

                                    String code=jsonObject.getString("code");
                                    progressDialog.dismiss();
                                    if(code.equals("100"))
                                    {
                                        Toast.makeText(context, "OTP verified..", Toast.LENGTH_SHORT).show();

                                        sharedPref.setRememberLogin(true);

                                        Intent intent = new Intent(OTPActivity.this, HomeScreen.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "OTP not matched, please try again..", Toast.LENGTH_SHORT).show();

                                    }

                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });


            }
        });
    }

}
