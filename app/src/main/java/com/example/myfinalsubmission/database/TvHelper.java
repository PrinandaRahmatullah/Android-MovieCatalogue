package com.example.myfinalsubmission.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.myfinalsubmission.database.DatabaseContract.TABLE_TV;

public class TvHelper {
    private static final String DATABASE_TABLE = TABLE_TV;
    private static DatabaseHelper databaseHelper;
    private static TvHelper INTANCE;

    private static SQLiteDatabase database;

    public TvHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static TvHelper getInstance(Context context) {
        if (INTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INTANCE == null) {
                    INTANCE = new TvHelper(context);
                }
            }
        }
        return INTANCE;
    }

    public static Cursor queryByName(String name) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.TvColumns.TITLE_TV + " = ?",
                new String[]{name},
                null,
                null,
                _ID + " ASC");
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public long insert(ContentValues contentValues) {
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, _ID + "= ?", new String[]{id});
    }

    public int deleteByName(String name) {
        return database.delete(DATABASE_TABLE, DatabaseContract.TvColumns.TITLE_TV + "= ?", new String[]{name});
    }
}
