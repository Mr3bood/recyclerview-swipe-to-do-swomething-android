package com.example.abdallah.recyclerviewswipetodismissoredit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdallah.recyclerviewswipetodismissoredit.R;
import com.example.abdallah.recyclerviewswipetodismissoredit.editExistsContact;
import com.example.abdallah.recyclerviewswipetodismissoredit.model;

import java.util.List;

/**
 * Created by abdallah on 10/15/2017.
 */

public class adapter extends RecyclerView.Adapter<viewHolder> {

    List<model> list;
    Context context;


    public adapter(List<model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_item,parent,false);
        viewHolder holder = new viewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {

        model model = list.get(position);
        holder.name.setText(model.getName());
        holder.number.setText(model.getNumber());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,editExistsContact.class);
                i.putExtra("name",holder.name.getText().toString());
                i.putExtra("number",holder.number.getText().toString());
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}
