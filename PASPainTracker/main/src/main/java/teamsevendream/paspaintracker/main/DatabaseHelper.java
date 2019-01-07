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

    private static final String DATABASE_NAME = "PASPainTrackerDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER_DATA = "userData";
    private static final String USER_ID = "userID";
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String USER_DOB = "dateOfBirth";

    private static final String TABLE_SPIDER_DATA = "spiderData";
    private static final String SPIDER_ID = "spiderID";
    private static final String SPIDER_QUESTION1 = "question1";
    private static final String SPIDER_QUESTION2 = "question2";
    private static final String SPIDER_QUESTION3 = "question3";

    private static final String CREATE_TABLE_USER_DATA = "CREATE TABLE " + TABLE_USER_DATA + " (" + USER_ID + " INTEGER PRIMARY KEY, " +
            USER_NAME + " TEXT, " + USER_SURNAME + " TEXT, " + USER_DOB + " DATE" + ");";

    private static final String CREATE_TABLE_SPIDER_DATA = "CREATE TABLE " + TABLE_SPIDER_DATA + " (" + SPIDER_ID + " INTEGER PRIMARY KEY, " +
            SPIDER_QUESTION1 + " INTEGER, " + SPIDER_QUESTION2 + " INTEGER, " + SPIDER_QUESTION3 + " INTEGER" + ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER_DATA);
        db.execSQL(CREATE_TABLE_SPIDER_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPIDER_DATA);
        onCreate(db);
    }

    public boolean createUserData(String name, String surname, String dateOfBirth){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_SURNAME, surname);
        contentValues.put(USER_DOB, dateOfBirth);

        Log.d(TAG, "createUserData: (NAME: " + name + ", SURNAME: " + surname + ", DATE OF BIRTH: " + dateOfBirth + ") to " + TABLE_USER_DATA);

        long result = db.insert(TABLE_USER_DATA, null, contentValues);

        if(result == -1) {
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else {
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public List<String> getUserData(){
        Log.d(TAG, "Getting user data...");
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> userDataList = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_USER_DATA;
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        Log.d(TAG, "Adding to userDataList...");
        userDataList.add(data.getString(1));
        userDataList.add(data.getString(2));
        userDataList.add(data.getString(3));
        data.close();
        return userDataList;
    }

    public boolean updateUserData(String name, String surname, String dateOfBirth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_SURNAME, surname);
        contentValues.put(USER_DOB, dateOfBirth);
        int result = db.update(TABLE_USER_DATA, contentValues, null, null);
        if(result == 0) {
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else {
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public boolean checkTableUserDataEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_DATA;
        Cursor data = db.rawQuery(query, null);
        boolean empty = data.moveToFirst();
        data.close();
        return !empty;
    }

    public boolean createSpiderData(int answer1, int answer2, int answer3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SPIDER_QUESTION1, answer1);
        contentValues.put(SPIDER_QUESTION2, answer2);
        contentValues.put(SPIDER_QUESTION3, answer3);

        Log.d(TAG, "createSpiderData: (QUESTION1: " + answer1 + ", QUESTION2: " + answer2 + ", QUESTION3: " + answer3 + ") to " + TABLE_SPIDER_DATA);

        long result = db.insert(TABLE_SPIDER_DATA, null, contentValues);

        if(result == -1) {
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else {
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public List<Integer> getSpiderData(){
        Log.d(TAG, "Getting spider data...");
        SQLiteDatabase db = this.getWritableDatabase();
        List<Integer> spiderDataList = new ArrayList<Integer>();
        String query = "SELECT * FROM " + TABLE_SPIDER_DATA;
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        Log.d(TAG, "Adding to spiderList...");
        spiderDataList.add(data.getInt(1));
        spiderDataList.add(data.getInt(2));
        spiderDataList.add(data.getInt(3));
        data.close();
        return spiderDataList;
    }

    public boolean updateSpiderData(int answer1, int answer2, int answer3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SPIDER_QUESTION1, answer1);
        contentValues.put(SPIDER_QUESTION2, answer2);
        contentValues.put(SPIDER_QUESTION3, answer3);
        int result = db.update(TABLE_SPIDER_DATA, contentValues, null, null);
        if(result == 0) {
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else {
            Log.d(TAG, "Data added successfully!");
            return true;
        }
    }

    public boolean checkTableSpiderDataEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SPIDER_DATA;
        Cursor data = db.rawQuery(query, null);
        boolean empty = data.moveToFirst();
        data.close();
        return !empty;
    }

}
