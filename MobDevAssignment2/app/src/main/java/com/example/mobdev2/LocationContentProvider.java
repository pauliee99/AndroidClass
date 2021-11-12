package com.example.mobdev2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

public class LocationContentProvider extends ContentProvider {
    private static UriMatcher uriMatcher;
    private DbHelper dbHelper;
    private static final String AUTHORITY = "com.example.mobdev2";
    public static final String CONTENT_URI = "content://"+AUTHORITY;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"locations",1);
        uriMatcher.addURI(AUTHORITY,"locations/#",2);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor result = null;
        switch (uriMatcher.match(uri)){
            case 1:
                result = dbHelper.selectAll();
                break;
            case 2:
                result = dbHelper.selectContactById(Integer.parseInt(uri.getLastPathSegment()));
                break;
        }
        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri result = null;
        switch(uriMatcher.match(uri)){
            case 1:
                Log.println(Log.INFO, "Database info", " here" + values.getAsDouble("latitude"));
                LocationUpdates contact = new LocationUpdates(values.getAsDouble("latitude"), values.getAsDouble("longitude"), values.getAsString("timestamp"));
                Log.println(Log.INFO, "Database info", " hreerrer--->" + values.getAsDouble("latitude"));
                long id = dbHelper.insertLocation(contact);
                result = Uri.parse(AUTHORITY+"/locations/"+id);
                break;
        }
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
