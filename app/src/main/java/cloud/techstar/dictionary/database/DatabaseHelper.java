package cloud.techstar.dictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

import cloud.techstar.dictionary.AppMain;
import cloud.techstar.dictionary.models.Words;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper() {
        super(AppMain.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WordsTable.create());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Words.TABLE_WORDS);
        onCreate(db);
        Logger.d("Database upgraded new version "+DATABASE_VERSION);
    }
}
