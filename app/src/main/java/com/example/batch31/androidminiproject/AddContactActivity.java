package com.example.batch31.androidminiproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {
    private EditText firstName, lastName, contactNumber, contactEmail, contactImage;
    private Button saveButton;
    private Contact contact;
    private ContactDatabase contactDatabase;
    private boolean update;
    private ContactViewModel contactViewModel;
    private List<Contact> contacts;
    private int pos;

    //public static final String EXTRA_REPLY = "com.example.batch31.androidminiproject.REPLY";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        contactNumber = (EditText) findViewById(R.id.contactNumber);
        contactEmail = (EditText) findViewById(R.id.contactEmail);
        //contactImage = (EditText) findViewById(R.id.contactImage);

        contactViewModel = new ContactViewModel(getApplication());
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
                    contactViewModel.insert(contact);
                }/*
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(firstName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra("firstName", sFirstName);
                    replyIntent.putExtra("lastName", sLastName);
                    replyIntent.putExtra("contactNum", sContactNum);
                    replyIntent.putExtra("contactEmail", sContactEmail);
                    setResult(RESULT_OK, replyIntent);
                }*/
                finish();
            }
        });

    }


    private void setResult(Contact contact, int flag){
        setResult(flag,new Intent().putExtra("contact", contact));
        finish();
    }

}
