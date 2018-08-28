package com.example.batch31.androidminiproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactListAdapter.OnContactItemClick{

    public static final int ADD_CONTACT_ACTIVITY_REQUEST_CODE = 1;

    private ContactDatabase contactDatabase;
    private ContactViewModel contactViewModel;
    private List<Contact> contacts;
    private int pos;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        contacts = new ArrayList<>();
        final ContactListAdapter adapter = new ContactListAdapter(contacts,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Get a new or existing ViewModel from the ViewModelProvider.
        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        contactViewModel.getcAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contact) {
                // Update the cached copy of the words in the adapter.
                adapter.setcContacts(contact);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, ADD_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            contact = new Contact(data.getStringExtra("firstName"),data.getStringExtra("lastName"),
                    data.getStringExtra("contactNum"),data.getStringExtra("email"));
            contactViewModel.insert(contact);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Contact not saved!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onContactClick(final int pos) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                contactDatabase.contactDao().delete(contact);
                                contacts.remove(pos);
                                break;
                            case 1:
                                MainActivity.this.pos = pos;
                                startActivityForResult(
                                        new Intent(MainActivity.this,
                                                AddContactActivity.class).putExtra("contact",contacts.get(pos)),
                                        100);

                                break;
                        }
                    }
                }).show();
    }
}
