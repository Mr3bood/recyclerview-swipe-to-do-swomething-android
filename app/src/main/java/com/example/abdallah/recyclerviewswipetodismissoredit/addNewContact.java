package com.example.abdallah.recyclerviewswipetodismissoredit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by abdallah on 10/15/2017.
 */

public class addNewContact extends AppCompatActivity {

    EditText name,number;

    DataBase handler = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        name = (EditText) findViewById(R.id.nameToWrite);
        number = (EditText) findViewById(R.id.numberToWrite);
    }

    public void cancelCreateNewContactClicked(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void createNewContactClicked(View view) {
        model model = new model();
        model.setName(name.getText().toString());
        model.setNumber(number.getText().toString());
        handler.addContact(model);
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        Toast.makeText(this, "item added successfully\nlist's size became: "+handler.getAllContacts().size(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
