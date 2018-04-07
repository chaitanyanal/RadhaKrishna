package com.example.chaitanya.radhakrishna.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chaitanya.radhakrishna.R;
import com.example.chaitanya.radhakrishna.Util.SharedPref;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {

    EditText edt_name,edt_email,edt_phone;
    Button btn_Save;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        context=this;

        init();
    }

    private void init() {

        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);
        edt_phone=findViewById(R.id.edt_phone);
        btn_Save=findViewById(R.id.btn_Save);

        btn_Save.setOnClickListener(this);


        edt_phone.setText(SharedPref.getInstance(context).getUsername());
        edt_email.setText(edt_email.getText().toString());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_Save:
            {

                String name= edt_name.getText().toString();
                String email= edt_email.getText().toString();
              //  String phone= edt_name.getText().toString();

            }


        }

    }
}
