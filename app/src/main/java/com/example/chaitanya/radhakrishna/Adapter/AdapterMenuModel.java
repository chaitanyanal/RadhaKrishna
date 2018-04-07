package com.example.chaitanya.radhakrishna.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitanya.radhakrishna.Activities.Details;
import com.example.chaitanya.radhakrishna.Activities.MainActivity;
import com.example.chaitanya.radhakrishna.Activities.SubMenuActivity;
import com.example.chaitanya.radhakrishna.Model.MenuDetails;
import com.example.chaitanya.radhakrishna.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterMenuModel extends RecyclerView.Adapter<AdapterMenuModel.MyViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> menuDetailsList;
    MainActivity mainActivity;

    public AdapterMenuModel(Context context, ArrayList<HashMap<String, String>> menuDetailsList) {
        this.context = context;
        this.menuDetailsList = menuDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

       // MenuDetails  menuDetails=menuDetailsList.get(position);

       // holder.mtxtId.setText(menuDetails.getId());
        holder.mtxtName.setText(menuDetailsList.get(position).get("ITEM"));
        holder.mtxtId.setTag(position);

        holder.mtxtId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos= (int) holder.mtxtId.getTag();
                int itemPosition=pos+1;

               // Log.e("pos", String.valueOf(pos));

              //  Toast.makeText(context, ""+String.valueOf(itemPosition), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(),SubMenuActivity.class);
                intent.putExtra("ID",String.valueOf(itemPosition));
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return menuDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mtxtName;
        ImageView mtxtId;

        public MyViewHolder(View itemView) {
            super(itemView);

            mtxtId=itemView.findViewById(R.id.txtId);
            mtxtName=itemView.findViewById(R.id.txtName);
        }
    }
}
