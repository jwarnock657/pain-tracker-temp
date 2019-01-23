package teamsevendream.paspaintracker.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
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
    private static final String SPIDER_QUESTION4 = "question4";
    private static final String SPIDER_QUESTION5 = "question5";
    private static final String SPIDER_QUESTION6 = "question6";
    private static final String SPIDER_QUESTION7 = "question7";
    private static final String SPIDER_QUESTION8 = "question8";
    private static final String SPIDER_QUESTION9 = "question9";
    private static final String SPIDER_QUESTION10 = "question10";
    private static final String SPIDER_QUESTION11 = "question11";
    private static final String SPIDER_QUESTION12 = "question12";

    private static final String TABLE_PAIN_DATA = "painData";
    private static final String PAIN_ID = "painID";
    private static final String PAIN_DATA = "painData";
    private static final String INTENSITY = "intensity";
    private static final String AREA = "area";
    private static final String DETAILS = "details";
    private static final String WHAT_HELPED = "whatHelped";
    private static final String DATE = "date";
    //private static final String WHAT_DOING = "whatDoing";


    private static final String CREATE_TABLE_USER_DATA = "CREATE TABLE " + TABLE_USER_DATA + " (" + USER_ID + " INTEGER PRIMARY KEY, " +
            USER_NAME + " TEXT, " + USER_SURNAME + " TEXT, " + USER_DOB + " DATE" + ");";

    private static final String CREATE_TABLE_SPIDER_DATA = "CREATE TABLE " + TABLE_SPIDER_DATA + " (" + SPIDER_ID + " INTEGER PRIMARY KEY, " +
            SPIDER_QUESTION1 + " INTEGER, " + SPIDER_QUESTION2 + " INTEGER, " + SPIDER_QUESTION3 + " INTEGER, " + SPIDER_QUESTION4
            + " INTEGER" + ");";
//    + SPIDER_QUESTION5 + " INTEGER, " + SPIDER_QUESTION6 + " INTEGER, " + SPIDER_QUESTION7 + " INTEGER, "
//            + SPIDER_QUESTION8 + " INTEGER, " + SPIDER_QUESTION9 + " INTEGER, " + SPIDER_QUESTION10 + " INTEGER, " + SPIDER_QUESTION11
//            + " INTEGER, " + SPIDER_QUESTION12 + " INTEGER" + ");";

    /*
    private static final String CREATE_TABLE_PAIN_DATA = "CREATE TABLE " + TABLE_PAIN_DATA + " (" + PAIN_ID + " INTEGER PRIMARY KEY, "
            + PAIN_DATA + " TEXT, " + INTENSITY + " INTEGER, " + AREA + " TEXT, " + DETAILS + " TEXT, " + WHAT_HELPED + " TEXT, "
            + WHAT_DOING + " TEXT" + ");";
     */

    private static final String CREATE_TABLE_PAIN_DATA = "CREATE TABLE " + TABLE_PAIN_DATA + " (" + PAIN_ID + " INTEGER PRIMARY KEY, "
            + INTENSITY + " INTEGER, " + AREA + " TEXT, " + DETAILS + " TEXT, " + WHAT_HELPED + " TEXT, " + DATE + " TEXT" + ");";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_USER_DATA);
        db.execSQL(CREATE_TABLE_SPIDER_DATA);
        db.execSQL(CREATE_TABLE_PAIN_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPIDER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAIN_DATA);
        onCreate(db);
    }

    public boolean createPainData(int intensity, String area, String details, String what_helped, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INTENSITY, intensity);
        contentValues.put(AREA, area);
        contentValues.put(DETAILS, details);
        contentValues.put(WHAT_HELPED, what_helped);
        contentValues.put(DATE, date);
        //contentValues.put(WHAT_DOING, what_doing)
        Log.i(TAG, date);

        long result = db.insert(TABLE_PAIN_DATA, null, contentValues);

        if(result == -1) {
            Log.e(TAG, "Error with insertion!");
            return false;
        }
        else {
            Log.d(TAG, "Pain Data added successfully!");
            return true;
        }
    }

    public List<String> getPainDates() {
        Log.d(TAG, "Getting pain dates...");
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> painDateList = new ArrayList<String>();
        String query = "SELECT " + DATE + " FROM " + TABLE_PAIN_DATA;
        Cursor data = db.rawQuery(query, null);

        data.moveToFirst();

        //if the first value is null then no dates in data, returns empty list
        try{
            //do once outside so it adds the first date
            painDateList.add(data.getString(0));

            while(data.moveToNext()){
                painDateList.add(data.getString(0));
                Log.i(TAG, data.getString(0));
            }
        }
        catch (CursorIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        data.close();
        Log.i(TAG, "Returning Dates...");
        return painDateList;
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

    public boolean createSpiderData(int answer1, int answer2, int answer3, int answer4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SPIDER_QUESTION1, answer1);
        contentValues.put(SPIDER_QUESTION2, answer2);
        contentValues.put(SPIDER_QUESTION3, answer3);
        contentValues.put(SPIDER_QUESTION4, answer4);

        Log.d(TAG, "createSpiderData: (QUESTION1: " + answer1 + ", QUESTION2: " + answer2 + ", QUESTION3: " + answer3 + " QUESTION4: " + answer4 + ") to " + TABLE_SPIDER_DATA);

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
        spiderDataList.add(data.getInt(4));
        data.close();
        return spiderDataList;
    }

    public boolean updateSpiderData(int answer1, int answer2, int answer3, int answer4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SPIDER_QUESTION1, answer1);
        contentValues.put(SPIDER_QUESTION2, answer2);
        contentValues.put(SPIDER_QUESTION3, answer3);
        contentValues.put(SPIDER_QUESTION4, answer4);
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
