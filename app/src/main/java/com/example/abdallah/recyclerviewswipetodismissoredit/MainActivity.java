package com.example.abdallah.recyclerviewswipetodismissoredit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdallah.recyclerviewswipetodismissoredit.adapters.adapter;
import com.example.abdallah.recyclerviewswipetodismissoredit.adapters.viewHolder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rV;
    List<model> list;

    DataBase handler=new DataBase(this);
    public static adapter adapter;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);
        toolbar.setTitle("swipe to delete/edit R.V.");

        rV = (RecyclerView) findViewById(R.id.myRecycler);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rV.setLayoutManager(manager);
        list = handler.getAllContacts();
        adapter = new adapter(list,this);
        adapter.notifyDataSetChanged();
        rV.setAdapter(adapter);
        //rV.smoothScrollToPosition(list.size()-1);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallBackToLeft());
        itemTouchHelper.attachToRecyclerView(rV);
        ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(createHelperCallBackToRight());
        itemTouchHelper1.attachToRecyclerView(rV);
    }

    public void exit(View view) {
        finish();
    }

    public void addNewTask(View view) {
        Intent i = new Intent(this,addNewContact.class);
        startActivity(i);
        finish();
    }

    private ItemTouchHelper.Callback createHelperCallBackToLeft(){
        ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                final View foregroundView = ((viewHolder) viewHolder).background;
                final View background = ((viewHolder) viewHolder).foreground1;
                background.setBackgroundResource(R.color.bg_row_background);
                final ImageView deleteIcon = ((viewHolder) viewHolder).deleteIconWhenSwipedShow;
                deleteIcon.setVisibility(View.VISIBLE);
                final TextView deleteText = ((viewHolder) viewHolder).deleteTextWhenSwipedShow;
                deleteText.setVisibility(View.VISIBLE);
                final ImageView editIcon = ((viewHolder) viewHolder).editIconWhenSwipedShow;
                editIcon.setVisibility(View.INVISIBLE);
                final TextView editText = ((viewHolder) viewHolder).editTextWhenSwipedShow;
                editText.setVisibility(View.INVISIBLE);
                getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((viewHolder) viewHolder).background;
                getDefaultUIUtil().clearView(foregroundView);
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((viewHolder) viewHolder).background;
                getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("delete");
                alert.setMessage("are you sure ?");
                alert.setIcon(R.drawable.delete_icon);
                alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Cancel clicked !", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "No clicked !", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        handler.deleteContact(list.get(position).name);
                        Log.e("deleted at ",position+"");
                        list.remove(position);
                        Log.e("deleted from list at ",position+"");
                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "Yes clicked !", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }
        };
        return simpleCallback;
    } // SWIPE RIGHT FUNCTION

    private ItemTouchHelper.Callback createHelperCallBackToRight(){//SWIPE LEFT FUNCTION
        ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                final View foregroundView = ((viewHolder) viewHolder).background;
                final View background = ((viewHolder) viewHolder).foreground1;
                background.setBackgroundResource(R.color.description);
                final ImageView editIcon = ((viewHolder) viewHolder).editIconWhenSwipedShow;
                editIcon.setVisibility(View.VISIBLE);
                final TextView editText = ((viewHolder) viewHolder).editTextWhenSwipedShow;
                editText.setVisibility(View.VISIBLE);
                final ImageView deleteIcon = ((viewHolder) viewHolder).deleteIconWhenSwipedShow;
                deleteIcon.setVisibility(View.INVISIBLE);
                final TextView deleteText = ((viewHolder) viewHolder).deleteTextWhenSwipedShow;
                deleteText.setVisibility(View.INVISIBLE);
                getDefaultUIUtil().onDrawOver(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
            }
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((viewHolder) viewHolder).background;
                getDefaultUIUtil().clearView(foregroundView);
            }
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((viewHolder) viewHolder).background;
                getDefaultUIUtil().onDraw(c,recyclerView,foregroundView,dX,dY,actionState,isCurrentlyActive);
            }
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                model model = list.get(position);
                Intent i = new Intent(MainActivity.this,editExistsContact.class);
                i.putExtra("name",model.getName());
                i.putExtra("number",model.getNumber());
                startActivity(i);
                finish();
            }
            @Override
            public int convertToAbsoluteDirection(int flags, int layoutDirection) {
                return super.convertToAbsoluteDirection(flags, layoutDirection);
            }
        };
        return simpleCallback;
    }


}
