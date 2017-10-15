package com.example.abdallah.recyclerviewswipetodismissoredit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abdallah.recyclerviewswipetodismissoredit.R;

/**
 * Created by abdallah on 10/15/2017.
 */

public class viewHolder extends RecyclerView.ViewHolder {


    public RelativeLayout background,foreground1,foreground2;
    public TextView name,number,deleteTextWhenSwipedShow,editTextWhenSwipedShow;
    public ImageView deleteIconWhenSwipedShow,editIconWhenSwipedShow;

    public viewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameToShow);
        number = itemView.findViewById(R.id.numberToShow);
        background = itemView.findViewById(R.id.background);
        foreground1 = itemView.findViewById(R.id.foreground1);
        //foreground2 = itemView.findViewById(R.id.foreground2);
        deleteTextWhenSwipedShow = itemView.findViewById(R.id.deleteTextWhenSwipedShow);
        deleteIconWhenSwipedShow = itemView.findViewById(R.id.deleteIconWhenSwipedShow);
        editTextWhenSwipedShow = itemView.findViewById(R.id.editTextWhenSwipedShow);
        editIconWhenSwipedShow = itemView.findViewById(R.id.editIconWhenSwipedShow);
    }
}
