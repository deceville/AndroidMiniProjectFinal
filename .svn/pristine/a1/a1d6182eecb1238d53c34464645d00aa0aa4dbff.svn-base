package com.example.batch31.androidminiproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddContactActivity extends AppCompatActivity {
    private EditText firstName, lastName, contactNumber, contactEmail, contactImage;
    private Button saveButton;
    private Contact contact;
    private ContactDatabase contactDatabase;
    private boolean update;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        contactNumber = (EditText) findViewById(R.id.contactNumber);
        contactEmail = (EditText) findViewById(R.id.contactEmail);
        //contactImage = (EditText) findViewById(R.id.contactImage);

        saveButton = (Button) findViewById(R.id.button_save);

        if((contact = (Contact) getIntent().getSerializableExtra("contact")) != null){
            getSupportActionBar().setTitle("Update contact");
            update = true;
            saveButton.setText("Update");
            firstName.setText(contact.getFirstName());
            lastName.setText(contact.getLastName());
            contactNumber.setText(contact.getContactNumber());
            contactEmail.setText(contact.getContactEmail());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sFirstName = firstName.getText().toString();
                String sLastName = lastName.getText().toString();
                String sContactNum = contactNumber.getText().toString();
                String sContactEmail = contactEmail.getText().toString();
                if(update){
                    contact.setFirstName(sFirstName);
                    contact.setLastName(sLastName);
                    contact.setContactNumber(sContactNum);
                    contact.setContactEmail(sContactEmail);
                    contactDatabase.contactDao().update(contact);
                    setResult(contact, RESULT_OK);
                }else{
                    contact = new Contact(sFirstName, sLastName, sContactNum, sContactEmail);
                }

                finish();
            }
        });

    }


    private void setResult(Contact contact, int flag){
        setResult(flag,new Intent().putExtra("contact", contact));
        finish();
    }

}
