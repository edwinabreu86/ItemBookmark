package com.edwin.abreusoft.itembookmark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static com.edwin.abreusoft.itembookmark.VersesDbHelper.VersesDbContract.BOOK;
import static com.edwin.abreusoft.itembookmark.VersesDbHelper.VersesDbContract.CONTENT;
import static com.edwin.abreusoft.itembookmark.VersesDbHelper.VersesDbContract.TABLE_NAME;
import static com.edwin.abreusoft.itembookmark.VersesDbHelper.VersesDbContract.VERSE;

public class VersesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "versesDb";
    private static final int DATABASE_VERSION = 1;

    public VersesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VersesDbContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addVerse(Verse verse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK, verse.getBook());
        values.put(VERSE, verse.getVerse());
        values.put(CONTENT, verse.getContent());

        /*
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME+ " WHERE " +CONTENT+ " = " +verse.getContent(), null);

        if(cursor.getCount() <= 0) {
            db.insert(TABLE_NAME, null, values);
        }
        cursor.close();
        */

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public void removeVerse(Verse mVerse) {
        SQLiteDatabase db = this.getWritableDatabase();
        // String book = mVerse.getBook();
        // String verse = mVerse.getVerse();

        db.delete(TABLE_NAME, "book=? AND verse=?", new String[]{mVerse.getBook(), mVerse.getVerse()});

        // db.delete(TABLE_NAME, CONTENT + " LIKE '%" + content + "%'", null);
        db.close();
    }

    public List<Verse> getVersesList() {
        List<Verse> verses = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{BOOK, VERSE, CONTENT}, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            String book = cursor.getString(cursor.getColumnIndex(BOOK));
            String verse = cursor.getString(cursor.getColumnIndex(VERSE));
            String content = cursor.getString(cursor.getColumnIndex(CONTENT));

            verses.add(new Verse(book, verse, content));
        }
        cursor.close();
        db.close();

        return verses;
    }

    class VersesDbContract {

        static final String TABLE_NAME = "favorite";
        static final String BOOK = "book";
        static final String VERSE = "verse";
        static final String CONTENT = "content";

        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, " +
                BOOK + " TEXT, " +
                VERSE + " TEXT, " +
                CONTENT + " TEXT)";
    }
}
