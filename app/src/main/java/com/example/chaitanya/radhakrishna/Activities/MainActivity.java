package com.example.chaitanya.radhakrishna.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chaitanya.radhakrishna.Adapter.AdapterMenuModel;
import com.example.chaitanya.radhakrishna.DatabaseAdapters.DBAdapter;
import com.example.chaitanya.radhakrishna.Model.MenuDetails;
import com.example.chaitanya.radhakrishna.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;
    AdapterMenuModel adapterMenuModel;
    ProgressDialog progressDialog;

    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<MenuDetails> menuDetails;
    public static final String URL = "http://radhakrishna.smarksystechnologies.com/api/get_categories";
    DBAdapter dbAdapter;

    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> hashList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        dbAdapter= new DBAdapter(context);

        recyclerView=findViewById(R.id.recyclerMain);
        recyclerView.setHasFixedSize(true);
        hashList= new ArrayList<>();


        recyclerView.setLayoutManager(new GridLayoutManager(context,3));

        sendRequest(context);


    }

    private void sendRequest(final Context context) {
        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();
       // dbAdapter= new DBAdapter(context);
        dbAdapter.deleteItemsTable();

        StringRequest obreq=new StringRequest(Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("res",response.toString());

                menuDetails=new ArrayList<>();
                try {



                    JSONObject jsonObject=new JSONObject(response);
                    progressDialog.dismiss();

                    JSONArray jsonArray=jsonObject.getJSONArray("data");


                  //  Log.e("adad",response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        MenuDetails menu=new MenuDetails();

                        if (!jsonObject1.getString("parent_id").equalsIgnoreCase("0")) {

                            menu.setId(jsonObject1.getString("id"));
                            menu.setSubItemName(jsonObject1.getString("item_name"));
                            menu.setParentId(jsonObject1.getString("parent_id"));
                            menu.setPrice(jsonObject1.getString("price"));
                            menu.setTotal("0");
                            menu.setIsSelected("false");
                            menu.setQuantity("0");

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("1"))
                            {
                                menu.setItemName("HOT BEVERAGE");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("2"))
                            {
                                menu.setItemName("SOUTH INDIAN SNACKS");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("3"))
                            {
                                menu.setItemName("FASTED FOODS");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("4"))
                            {
                                menu.setItemName("SANDWITCHES");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("5"))
                            {
                                menu.setItemName("PAV BHAJI");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("6"))
                            {
                                menu.setItemName("SALAD + RAITA");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("7"))
                            {
                                menu.setItemName("PAPAD");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("8"))
                            {
                                menu.setItemName("PAKODA");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("9"))
                            {
                                menu.setItemName("CHINESE SOUP");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("10"))
                            {
                                menu.setItemName("CHINESE STARTER");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("11"))
                            {
                                menu.setItemName("CHINESE GREVY");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("13"))
                            {
                                menu.setItemName("CHINESE RICE");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("14"))
                            {
                                menu.setItemName("NOODLES");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("15"))
                            {
                                menu.setItemName("TANDOOR STARTER");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("16"))
                            {
                                menu.setItemName("MAIN COURSE");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("17"))
                            {
                                menu.setItemName("PANEER KI SABJI");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("18"))
                            {
                                menu.setItemName("CHEF SPECIAL");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("19"))
                            {
                                menu.setItemName("SWEET DISH");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("20"))
                            {
                                menu.setItemName("ROTI");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("21"))
                            {
                                menu.setItemName("BASMATI RICE");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("22"))
                            {
                                menu.setItemName("DESERT + ICECREAM");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("23"))
                            {
                                menu.setItemName("FALOODA");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("24"))
                            {
                                menu.setItemName("MASTANI");
                            }

                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("25"))
                            {
                                menu.setItemName("COLD BEVERAGE");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("26"))
                            {
                                menu.setItemName("MILK SHAKE");
                            }


                            if(jsonObject1.getString("parent_id").equalsIgnoreCase("27"))
                            {
                                menu.setItemName("FRESH JUICE");
                            }


                          //  Log.e("adad", jsonObject1.getString("id"));

                            menuDetails.add(menu);
                        }
                        else
                        {
                            hashMap= new HashMap<>();
                            hashMap.put("ITEM",jsonObject1.getString("item_name"));
                            hashMap.put("ID",jsonObject1.getString("id"));
                            hashList.add(hashMap);




                        }
                    }

                   // Log.e("data",""+menuDetails.size());

                    for(MenuDetails m:menuDetails)
                    {
                        dbAdapter.insertIntoItemsTable(m);
                    }


                    
                    adapterMenuModel=new AdapterMenuModel(context,hashList);
                    recyclerView.setAdapter(adapterMenuModel);
                    adapterMenuModel.notifyDataSetChanged();

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(obreq);
    }

}
