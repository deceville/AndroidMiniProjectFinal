package com.example.batch31.androidminiproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static ContactDatabase INSTANCE;

    public static ContactDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class, "contactDB.db")
                            .addCallback(sContactDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    private static ContactDatabase.Callback sContactDatabaseCallback =
            new ContactDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContactDao cDao;

        PopulateDbAsync(ContactDatabase db) {
            cDao = db.contactDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            cDao.deleteAll();
            Contact word = new Contact("Firstname", "LastName" , "00000", "FirstLastName@gmail.com");
            cDao.insert(word);
            word = new Contact("Firstname", "LastName" , "00000", "FirstLastName@gmail.com");
            cDao.insert(word);
            return null;
        }
    }
}
