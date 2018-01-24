package com.example.lenovo.khaadi.Adapters;
import com.example.lenovo.khaadi.Database.DBHelper;
import com.example.lenovo.khaadi.Models.DressInfo;
import com.example.lenovo.khaadi.R;
import com.example.lenovo.khaadi.UpdateDress;
import com.example.lenovo.khaadi.addKidsDresses;
import com.example.lenovo.khaadi.addManDresses;
import com.example.lenovo.khaadi.addWomanDress;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by abc on 1/20/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Adapters
 * @project Database
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList<DressInfo> dressInfos;
    Context context;
    DBHelper dbHelper;
    String category;
    Intent in;
    public DressInfo ui;
    public int currPosition;

    public DataAdapter(Context context, ArrayList<DressInfo> dressInfos,String cat) {
        this.dressInfos = dressInfos;

        this.context = context;
        dbHelper=new DBHelper(context);
        this.category=cat;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dress_info_layout,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        ui = dressInfos.get(position);
        currPosition=position;
       // Toast.makeText(context, ""+ui.getEmail(), Toast.LENGTH_SHORT).show();

        holder.dresscode.setText("Dress Code:  "+ui.getDcoe()+"");
        holder.dresstype.setText("Dress Type:  "+ui.getDtype()+"");
        holder.dressCategory.setText("Category:  "+ui.getCategory()+"");
        holder.dressQuantity.setText("Total Quantity:  "+ui.getQuantity()+"");
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    in = new Intent(context, UpdateDress.class);

                ui = dressInfos.get(position);
                in.putExtra("dcode", ui.getDcoe().toString());
                in.putExtra("dtype", ui.getDtype().toString());
                in.putExtra("category", ui.getCategory().toString());
                in.putExtra("quantity", ui.getQuantity());
                in.putExtra("id", ui.getId());
               // in.putExtra("id", ui.getId());

                context.startActivity(in);
            }
        });

        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog delItem =new AlertDialog.Builder(context)
                        //set message, title, and icon
                        .setTitle("Delete")
                        .setMessage("Do you want to Delete")
                        .setIcon(R.drawable.delete)

                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {


                                dbHelper.Delete_Item(dressInfos.get(position));
                                dressInfos.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, getItemCount());
                         //       holder.itemView.setVisibility(view.GONE);
                                dialog.dismiss();
                            }

                        })



                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        })
                        .create();
                    delItem.show();
            }
        });
       // holder.ll_ui.setBackgroundColor(position%2==1? Color.parseColor("#461F00"): Color.BLACK);
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
        public ImageView delButton,editButton;
        public LinearLayout ll_ui;

        public ViewHolder(View view) {
            super(view);
            dresscode = (TextView) view.findViewById(R.id.txt_did);
            dresstype = (TextView) view.findViewById(R.id.txt_dtype);
            dressCategory = (TextView) view.findViewById(R.id.txt_category);
            dressQuantity = (TextView) view.findViewById(R.id.txt_quantity);
            delButton=(ImageView) view.findViewById(R.id.deleteButton);
            editButton=(ImageView) view.findViewById(R.id.editButton);
            ll_ui = (LinearLayout) view.findViewById(R.id.ll_ui_layout);
        }
    }


}
