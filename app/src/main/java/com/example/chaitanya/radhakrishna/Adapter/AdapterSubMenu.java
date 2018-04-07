package com.example.chaitanya.radhakrishna.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitanya.radhakrishna.Model.SubMenuResponseBean;
import com.example.chaitanya.radhakrishna.R;

import java.util.ArrayList;

public class AdapterSubMenu extends RecyclerView.Adapter<AdapterSubMenu.ViewHOlder> {

    ArrayList<SubMenuResponseBean>subMenuResponseBeanArrayList ;
    Context context;
    private onCheckedChangeListener mcheckListener;
    private onItemClickListener mListener;
    int minteger = 1;

    public AdapterSubMenu(ArrayList<SubMenuResponseBean> subMenuResponseBeanArrayList, Context context) {
        this.subMenuResponseBeanArrayList = subMenuResponseBeanArrayList;
        this.context = context;
    }

    public interface onItemClickListener
    {
        void onItemClick(int position, boolean b, int items);

    }

    public interface onCheckedChangeListener
    {
        void checkedChage(int position, boolean b, int iems);
       // mcheckListener=checklistener;

    }

    public void setOnItemClickListener(onItemClickListener listener)
    {

        mListener=listener;

    }

    public void setOnCheckedChangeListener(onCheckedChangeListener changeListener)
    {
        mcheckListener=changeListener;
    }

    @Override
    public ViewHOlder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sub_menu,parent,false);

        return new ViewHOlder(v, mListener,mcheckListener );
    }

    @Override
    public void onBindViewHolder(final ViewHOlder holder, int position) {
        SubMenuResponseBean bean=subMenuResponseBeanArrayList.get(position);

        holder.txtItemName.setText(bean.getItem_name());
        holder.txtPrice.setText(bean.getPrice());
        holder.checkBox.setChecked(bean.isSelected());
        holder.checkBox.setTag(subMenuResponseBeanArrayList.get(position));

        /*holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((CheckBox) v).isChecked()){


                }else {

                }
            }
        });*/



/*        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                minteger=minteger+1;
                holder.txtNo.setText(String.valueOf(minteger));

            }
        });*/

        holder.decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // int minteger = 1;
                if (minteger<1)
                {

                    minteger = 1;
                    holder.txtNo.setText(String.valueOf(minteger));

                }
                if (minteger > 1)
                {

                    minteger = minteger - 1;
                    holder.txtNo.setText(String.valueOf(minteger));
                }




            }
        });


    }

    @Override
    public int getItemCount() {
        return subMenuResponseBeanArrayList.size();
    }

    public class ViewHOlder extends RecyclerView.ViewHolder {

        TextView txtItemName,txtPrice,txtNo;
        Button increase,decrease;
        CheckBox checkBox;
        boolean whetherChecked;
        int quantity=1;

        public ViewHOlder(final View itemView,  final onItemClickListener listener, final onCheckedChangeListener checkLIstener) {
            super(itemView);

            txtItemName=itemView.findViewById(R.id.txtItemName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtNo=itemView.findViewById(R.id.txtNo);

            increase=itemView.findViewById(R.id.increase);
            decrease=itemView.findViewById(R.id.decrease);
            checkBox=itemView.findViewById(R.id.chechItem);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    whetherChecked=isChecked;
                   // quantity=1;

                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(checkLIstener!=null)
                    {
                        int position= getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            checkLIstener.checkedChage(position, whetherChecked, quantity);

                        }
                    }

                }
            });

            increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION)
                    {

                        quantity=Integer.parseInt(txtNo.getText().toString());

                        listener.onItemClick(pos, whetherChecked, 1);

                    }


                }
            });

            decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION)
                    {



                        quantity=Integer.parseInt(txtNo.getText().toString());

                        listener.onItemClick(pos, whetherChecked, 2);

                    }
                }
            });


        }
    }
}
