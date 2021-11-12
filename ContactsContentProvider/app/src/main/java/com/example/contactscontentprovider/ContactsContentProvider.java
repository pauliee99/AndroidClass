package com.example.contactscontentprovider;

import android.content.UriMatcher;

import androidx.annotation.Nullable;

public class ContactsContentProvider {
    private UriMatcher urimatcher;
    private DbHelper dbHelper;

    @Override
    public boolean onCreate(){
        urimatcher = new UriMatcher(UriMatcher.NO_MATCH);
        urimatcher.addURI("content://com.example.contactscontentprovider", "/", 1);

        dbHeler = new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query
}
