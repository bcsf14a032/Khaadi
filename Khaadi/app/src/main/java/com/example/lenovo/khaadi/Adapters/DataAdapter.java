package com.example.lenovo.khaadi.Adapters;
import com.example.lenovo.khaadi.Models.DressInfo;
import com.example.lenovo.khaadi.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Adapters
 * @project Database
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<DressInfo> dressInfos;
    Context context;

    public DataAdapter(Context context, ArrayList<DressInfo> dressInfos) {
        this.dressInfos = dressInfos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dress_info_layout,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        DressInfo ui = dressInfos.get(position);
       // Toast.makeText(context, ""+ui.getEmail(), Toast.LENGTH_SHORT).show();
        holder.dresscode.setText("Dress Code:  "+ui.getDcoe()+"");
        holder.dresstype.setText("Dress Type:  "+ui.getDtype()+"");
        holder.dressCategory.setText("Category:  "+ui.getCategory()+"");
        holder.dressQuantity.setText("Total Quantity:  "+ui.getQuantity()+"");
        holder.ll_ui.setBackgroundColor(position%2==1? Color.parseColor("#461F00"): Color.BLACK);
    }


    @Override
    public int getItemCount() {
        return dressInfos.size();
    }


    /**
     * DataAdapter.ViewHolder Class is below It will be used for designing
     * and setting the data entries in the adapter for recyclerview
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dresscode;
        public TextView dresstype;
        public TextView dressCategory;
        public TextView dressQuantity;
        public LinearLayout ll_ui;

        public ViewHolder(View view) {
            super(view);
            dresscode = (TextView) view.findViewById(R.id.txt_did);
            dresstype = (TextView) view.findViewById(R.id.txt_dtype);
            dressCategory = (TextView) view.findViewById(R.id.txt_category);
            dressQuantity = (TextView) view.findViewById(R.id.txt_quantity);
            ll_ui = (LinearLayout) view.findViewById(R.id.ll_ui_layout);
        }
    }


}
