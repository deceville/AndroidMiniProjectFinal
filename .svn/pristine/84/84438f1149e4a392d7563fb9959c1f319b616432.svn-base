package com.example.batch31.androidminiproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ContactRepository {

    private ContactDao cContactDao;
    private LiveData<List<Contact>> cAllContacts;

    ContactRepository(Application application){
        ContactDatabase db = ContactDatabase.getDatabase(application);
        cContactDao = db.contactDao();
        cAllContacts = cContactDao.getAllContacts();
    }

    LiveData<List<Contact>> getAllWords() {
        return cAllContacts;
    }

    public void insert (Contact contact){
        new insertAsyncTask(cContactDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void>{
        private ContactDao cAsyncTaskDao;

        insertAsyncTask(ContactDao dao){
            cAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            cAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

}
