package com.example.batch31.androidminiproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;
    private LiveData<List<Contact>>  cAllContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        cAllContacts = contactRepository.getAllWords();
    }

    LiveData<List<Contact>> getcAllContacts(){
        return cAllContacts;
    }

    public void insert(Contact contact){
        contactRepository.insert(contact);
    }
}
