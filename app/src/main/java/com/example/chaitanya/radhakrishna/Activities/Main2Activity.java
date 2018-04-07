package com.example.chaitanya.radhakrishna.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitanya.radhakrishna.Model.SubMenuResponseBean;
import com.example.chaitanya.radhakrishna.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {


    ArrayList<SubMenuResponseBean> beanList;
    String totalPrice="0";
    String itemNames="";
    TextView txtList, txtPrice, txtTotalValue;
    Button btn_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewsOfWidgets();

        beanList = new ArrayList<SubMenuResponseBean>();
        beanList = (ArrayList<SubMenuResponseBean>)getIntent().getSerializableExtra("ListString");
        totalPrice=getIntent().getStringExtra("TotalPrice");

        for(int i=0; i<beanList.size(); i++)
        {
            if(i==0)
            {
                itemNames = beanList.get(i).getItem_name();

            }
            else {
               // if (i < beanList.size()) {
                    itemNames = itemNames + ", " + beanList.get(i).getItem_name();

                /*} else {
                    itemNames = itemNames + "," + beanList.get(i).getItem_name();

                }*/
            }

        }

        txtList.setText(itemNames);
        txtPrice.setText(totalPrice);
        txtTotalValue.setText(totalPrice);
        /*Gson gson = new Gson();
        String finalListTempString = gson.toJson(beanList);

        Toast.makeText(this, ""+finalListTempString, Toast.LENGTH_SHORT).show();*/

    }

    private void findViewsOfWidgets() {
        txtList=findViewById(R.id.txt_list);
        txtPrice=findViewById(R.id.txt_price);
        txtTotalValue=findViewById(R.id.txt_toatl_value);
        btn_address=findViewById(R.id.btn_address);

        btn_address.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case  R.id.btn_address:
            {
               Intent intent=new Intent(Main2Activity.this,AddressActivity.class);
               startActivity(intent);

                break;
            }
        }
    }
}
