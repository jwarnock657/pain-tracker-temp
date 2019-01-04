package teamsevendream.paspaintracker.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "userTable";
    private static final String COL1 = "name";
    private static final String COL2 = "dateOfBirth";
    private static final String COL3 = "gender";
    private static final String COL4 = "answer1";
    private static final String COL5 = "answer2";
    private static final String COL6 = "answer3";

    public DatabaseHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d(TAG, "Creating...");
        String createTable = "CREATE TABLE " + DatabaseHelper.TABLE_NAME + " (" + DatabaseHelper.COL1 + " PRIMARY KEY, "
                + DatabaseHelper.COL2 + " DATE, " + DatabaseHelper.COL3 + " TEXT, " + DatabaseHelper.COL4 + " INT, "
                + DatabaseHelper.COL5 + " INT, " + DatabaseHelper.COL6 + " INT" + ");";
        Log.d(TAG, "Creating table...");
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    public boolean addPersonalData(String name, String dateOfBirth, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, dateOfBirth);
        contentValues.put(COL3, gender);

        Log.d(TAG, "addPersonalData: (NAME: " + name + ", DATE OF BIRTH: " + dateOfBirth + ", GENDER: " + gender + ") to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else{
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public boolean addSpiderData(int answer1, int answer2, int answer3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, answer1);
        contentValues.put(COL5, answer2);
        contentValues.put(COL6, answer3);

        Log.d(TAG, "addSpiderData: (ANSWER1: " + answer1 + ", ANSWER2: " + answer2 + ", ANSWER3: " + answer3 + ") to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else{
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public String getPersonal(){
        Log.d(TAG, "Getting Personal data...");
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> personalList = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        data.moveToNext();
        String name = data.getString(0);

        return name;
    }

    public List<Integer> getSpider(){
        Log.d(TAG, "Getting Spider data...");
        SQLiteDatabase db = this.getWritableDatabase();
        List<Integer> spiderList = new ArrayList<Integer>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        data.moveToPosition(1);

        Log.d(TAG, "Adding to spiderlist");
        spiderList.add(data.getInt(3));
        spiderList.add(data.getInt(4));
        spiderList.add(data.getInt(5));
//        int i = 0;
//
//        while(i < 6){
//            if(i > 2){
//                Log.d(TAG, "Loop");
//                String sData = data.getString(0);
//                spiderList.add(sData);
//                Log.d(TAG, "Added: " + sData);
//            }
//            data.moveToNext();
//            i += 1;
//        }

        return spiderList;
    }

}
