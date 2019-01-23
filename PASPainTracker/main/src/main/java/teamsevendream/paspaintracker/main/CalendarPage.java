package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.DrawableUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarPage extends AppCompatActivity {

    private static String TAG = "CalendarPage";

    DatabaseHelper mDatabaseHelper;

    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    List<EventDay> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);
        mDatabaseHelper = new DatabaseHelper(this);
        Calendar calendar = Calendar.getInstance();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 2);
//        events.add(new EventDay(calendar, R.drawable.ic_pet));

//        events.add(new EventDay(calendar1, R.drawable.ic_pet));

        Log.i(TAG, Integer.toString(events.size()));
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        //mCalendarView.setEvents(events);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(CalendarPage.this, RecordPain.class));
            }
        });

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
//                previewNote(eventDay);
                Toast.makeText(getApplicationContext(),
                        eventDay.getCalendar().getTime().toString() + " ",
                        Toast.LENGTH_SHORT).show();
            }
        });

        getEntries(mCalendarView);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
//            MyEventDay myEventDay = data.getParcelableExtra(RESULT);
//            mCalendarView.setDate(myEventDay.getCalendar());
//            mEventDays.add(myEventDay);
//            mCalendarView.setEvents(mEventDays);
//        }
//    }

    private void getEntries(CalendarView calendar){
        List<String> dates = mDatabaseHelper.getPainDates();
        Log.i(TAG, "Adding to calendar...");
        Log.i(TAG, Integer.toString(dates.size()));
//        MyEventDay myEventDay = data.getParcelableExtra(RESULT);
//        MyEventDay myEventDay = new MyEventDay(Calendar.getInstance(), R.drawable.ic_launcher, "Hello");
        for(int i=0; i<dates.size(); i++){
            String[] parts = dates.get(i).split("/");
            Log.i(TAG, parts[0]);
            Log.i(TAG, parts[1]);
            Log.i(TAG, parts[2]);
            Date date = null;

            SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
            try {
                date = curFormater.parse(dates.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            Calendar entryCalendar = Calendar.getInstance();
//            entryCalendar.add(Calendar.MONTH, Integer.parseInt(parts[1]));
//            entryCalendar.add(Calendar.YEAR, Integer.parseInt(parts[2]));
//            entryCalendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(parts[0]));
            entryCalendar.setTime(date);
            Log.i(TAG, "LOOP...");
            events.add(new EventDay(entryCalendar, R.drawable.ic_baseline_add_24px));
        }
        Log.i(TAG, "Setting dates...");
//        mCalendarView.setDate(myEventDay.getCalendar());
//        mEventDays.add(myEventDay);
        Log.i(TAG, Integer.toString(events.size()));
        calendar.setEvents(events);
    }
//
//    private void addNote() {
//        Intent intent = new Intent(this, AddNoteActivity.class);
//        startActivityForResult(intent, ADD_NOTE);
//    }
//
//    private void previewNote(EventDay eventDay) {
//        Intent intent = new Intent(this, NotePreviewActivity.class);
//        if(eventDay instanceof MyEventDay){
//            intent.putExtra(EVENT, (MyEventDay) eventDay);
//        }
//        startActivity(intent);
//    }
}

//class MyEventDay extends EventDay implements Parcelable {
//    private String mNote;
//
//    MyEventDay(Calendar day, int imageResource, String note) {
//        super(day, imageResource);
//        mNote = note;
//    }
//
//    String getNote() {
//        return mNote;
//    }
//
//    private MyEventDay(Parcel in) {
//        super((Calendar) in.readSerializable(), in.readInt());
//        mNote = in.readString();
//    }
//
//    public static final Creator<MyEventDay> CREATOR = new Creator<MyEventDay>() {
//        @Override
//        public MyEventDay createFromParcel(Parcel in) {
//            return new MyEventDay(in);
//        }
//
//        @Override
//        public MyEventDay[] newArray(int size) {
//            return new MyEventDay[size];
//        }
//    };
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeSerializable(getCalendar());
//        parcel.writeInt(getImageResource());
//        parcel.writeString(mNote);
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//}
