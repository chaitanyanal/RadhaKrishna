package com.example.chaitanya.radhakrishna.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chaitanya.radhakrishna.Adapter.AdapterSubMenu;
import com.example.chaitanya.radhakrishna.DatabaseAdapters.DBAdapter;
import com.example.chaitanya.radhakrishna.Model.MenuDetails;
import com.example.chaitanya.radhakrishna.Model.SubMenuPojo;
import com.example.chaitanya.radhakrishna.Model.SubMenuResponseBean;
import com.example.chaitanya.radhakrishna.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubMenuActivity extends AppCompatActivity implements View.OnClickListener {

    String ClickedItem="";
    SubMenuPojo subMenuPojo;
    Context context;
    SubMenuResponseBean bean;
   // ArrayList<SubMenuResponseBean> subMenuResponseBeanArrayList;
    ArrayList<MenuDetails> list;
    RecyclerView recyclerView;
    AdapterSubMenu adapterSubMenu;
    RecyclerView.LayoutManager layoutManager;
    Button btnPrice, btnOrder;
    ProgressDialog progressDialog;
    ArrayList<MenuDetails> finalListTemp;
    int totalPrice=0;
    int minteger=1;
    DBAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submenu);
        context=this;
        dbAdapter = new DBAdapter(context);


        recyclerView=findViewById(R.id.recyclersubmenu);
        btnOrder=findViewById(R.id.btn_order);
        btnPrice=findViewById(R.id.button_price);

        btnOrder.setOnClickListener(this);
        btnPrice.setOnClickListener(this);


        list= new ArrayList<>();
        ClickedItem= getIntent().getStringExtra("ClickedItem");

        subMenuPojo= new SubMenuPojo();
      //  subMenuPojo.setId(PID);
        list= dbAdapter.getAllListOfAllSubItems(ClickedItem);

      //  subMenuResponseBeanArrayList= d
        setDataToRecyclerView(list);

      /*  Gson gson= new Gson();
        String requestID="";
        requestID=gson.toJson(subMenuPojo);
        String url="http://radhakrishna.smarksystechnologies.com/api/get_sub_categories";

        progressDialog= new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait");
        progressDialog.show();

        Ion.with(context)
                .load(url)
                .setStringBody(requestID)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {


                        try {
                            progressDialog.dismiss();
                            JSONObject jsonObject= new JSONObject(String.valueOf(result));

                            String code=jsonObject.getString("code");
                            if(code.equals("100"))
                            {
                                JSONArray jsonArray= jsonObject.getJSONArray("data");
                                for(int i=0; i<jsonArray.length(); i++)
                                {
                                    JSONObject object= jsonArray.getJSONObject(i);

                                    bean= new SubMenuResponseBean();

                                    bean.setId(object.getString("id"));
                                    bean.setItem_name(object.getString("item_name"));
                                    bean.setPrice(object.getString("price"));
                                    bean.setParent_id(object.getString("parent_id"));
                                    bean.setQuantity("1");
                                    bean.setSelected(false);

                                    subMenuResponseBeanArrayList.add(bean);

                                }

                                setDataToRecyclerView(subMenuResponseBeanArrayList);

                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                });*/
    }

    private void setDataToRecyclerView(final ArrayList<MenuDetails> list)
    {
        layoutManager= new LinearLayoutManager(context);
        adapterSubMenu= new AdapterSubMenu(list, context);
        recyclerView.setAdapter(adapterSubMenu);
        recyclerView.setLayoutManager(layoutManager);
        adapterSubMenu.notifyDataSetChanged();


        ArrayList<MenuDetails> listTemp;
        listTemp= new ArrayList<>();

        listTemp=list;

        finalListTemp= new ArrayList<>();

        adapterSubMenu.setOnCheckedChangeListener(new AdapterSubMenu.onCheckedChangeListener() {
            @Override
            public void checkedChage(int position, boolean b, int quantity) {
                if(b)
                {
                    list.get(position).setIsSelected("true");

                    MenuDetails bean1= list.get(position);
                    finalListTemp.add(bean1);
                    if(finalListTemp.size()>0)
                    {
                        if(bean1.getIsSelected().equals("true"))
                        {
                            totalPrice=totalPrice+(Integer.valueOf(bean1.getPrice())*Integer.valueOf(bean1.getQuantity()));

                            btnPrice.setText("PRICE:  "+String.valueOf(totalPrice)+" Rs.");
                        }

                    }


                    // Toast.makeText(context, String.valueOf(subMenuResponseBeanArrayList.get(position).getItem_name())+" Checked", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    list.get(position).setIsSelected("false");
                    MenuDetails bean2=list.get(position);
                    finalListTemp.remove(bean2);
                    if(bean2.getIsSelected().equals("false"))
                    {
                        totalPrice=totalPrice-(Integer.valueOf(bean2.getPrice())*Integer.valueOf(bean2.getQuantity()));
                        btnPrice.setText("PRICE:  "+String.valueOf(totalPrice)+" Rs.");
                    }
                    //  Toast.makeText(context, String.valueOf(subMenuResponseBeanArrayList.get(position).getItem_name())+" UnChecked", Toast.LENGTH_SHORT).show();

                }


            }
        });


        adapterSubMenu.setOnItemClickListener(new AdapterSubMenu.onItemClickListener() {
            @Override
            public void onItemClick(int position, boolean b, int flag) {

                if(flag==1)
                {
                    minteger=minteger+1;
                }
                else if(flag==2)
                {
                    if (minteger<1)
                    {

                        minteger = 1;
                       // txtNo.setText(String.valueOf(minteger));

                    }
                    if (minteger > 1)
                    {

                        minteger = minteger - 1;
                       // txtNo.setText(String.valueOf(minteger));
                    }

                }


                Toast.makeText(context, ""+String.valueOf(minteger), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_order:
            {

               /* ArrayList<SubMenuResponseBean> list;
                list= new ArrayList<>();

                for(SubMenuResponseBean beanTemp:subMenuResponseBeanArrayList)
                {
                    if(beanTemp.isSelected()==true)
                    {
                        list.add(beanTemp);
                    }

                }*/

               if(finalListTemp.size()==0)
               {
                   Toast.makeText(context, "Please select at least one item", Toast.LENGTH_SHORT).show();
               }
               else {
                   /*Gson gson = new Gson();
                   String finalListTempString = gson.toJson(finalListTemp);*/
                   Intent intent= new Intent(SubMenuActivity.this, Main2Activity.class);
                   intent.putExtra("ListString", finalListTemp);
                   intent.putExtra("TotalPrice", String.valueOf(totalPrice));
                   startActivity(intent);
                 //  Toast.makeText(context, "" + requestString, Toast.LENGTH_SHORT).show();
                 //  Toast.makeText(context, "" + String.valueOf(totalPrice), Toast.LENGTH_SHORT).show();
               }


                break;
            }

            case R.id.button_price:
            {


                break;
            }
        }
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
