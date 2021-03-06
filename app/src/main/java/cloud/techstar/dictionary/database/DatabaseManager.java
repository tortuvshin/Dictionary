package cloud.techstar.dictionary.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

public class DatabaseManager {

    private Integer mOpenCounter = 0;
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private static SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        Logger.d("Database manager initialize .. ");
        if (instance == null){
            instance = new DatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance () {
        if (instance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName()+ "is not initialize error");
        }
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase (){
        mOpenCounter+=1;
        if (mOpenCounter == 1) {
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase () {
        mOpenCounter-=1;
        if (mOpenCounter == 0){
            mDatabase.close();
        }
    }
}
