package com.example.abdallah.recyclerviewswipetodismissoredit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by abdallah on 10/15/2017.
 */

public class editExistsContact extends AppCompatActivity {

    EditText name,number;
    String getName,getNumber;

    DataBase handler = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exists_contact);

        name = (EditText) findViewById(R.id.nameToEdit);
        number = (EditText) findViewById(R.id.numberToEdit);

        Intent i = getIntent();
        getName = i.getStringExtra("name");
        getNumber = i.getStringExtra("number");

        name.setText(getName);
        number.setText(getNumber);
    }

    public void cancelEditExistsContactClicked(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void EditExistsContactClicked(View view) {
        String oldName = getName;
        String oldNumber = getNumber;
        String newName = name.getText().toString();
        String newNumber = number.getText().toString();
        handler.editContact(oldName,newName,oldNumber,newNumber);
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }
}
