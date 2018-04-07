package com.example.chaitanya.radhakrishna.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chaitanya.radhakrishna.R;
import com.example.chaitanya.radhakrishna.Util.SharedPref;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {


    Button btnNewOrder;
    ImageView img_call,img_user_details;
    SharedPref sharedPref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        context=this;
        sharedPref= new SharedPref(context);
        if(sharedPref.getRememberLogin()==false)
        {

            Intent intent= new Intent(HomeScreen.this, LoginActivity.class);
            startActivity(intent);
        }

        findViewsOfWidgets();
    }

    private void findViewsOfWidgets()
    {
        btnNewOrder=findViewById(R.id.btn_new_order);
        img_call=findViewById(R.id.img_call);
        img_user_details=findViewById(R.id.img_user_details);

        btnNewOrder.setOnClickListener(this);
        img_call.setOnClickListener(this);
        img_user_details.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_new_order:
            {
                Intent intent= new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent);

                break;
            }

            case R.id.img_call:
            {

                String phone = "9623963792";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                
                break;
            }

            case R.id.img_user_details:
            {

                Intent intent= new Intent(HomeScreen.this, UserDetails.class);
                startActivity(intent);

                break;
            }

        }

    }
}
