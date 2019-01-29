package teamsevendream.paspaintracker.main;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class ViewPain extends AppCompatActivity {

    private static String TAG = "ViewPain";

    DatabaseHelper mDatabaseHelper;

    private TextView bodyPart;
    private TextView context;
    private TextView whatHelped;
    private SeekBar viewIntensity;
    private Button btnGoHome;
    private DatePickerDialog selectDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pain);

        bodyPart.findViewById(R.id.viewBodyPart);
        context.findViewById(R.id.viewPainContext);
        whatHelped.findViewById(R.id.viewPainWhatHelped);


        btnGoHome = findViewById(R.id.btnGoHome);
        mDatabaseHelper = new DatabaseHelper(this);
        viewPainData();


    }

    private void viewPainData() {
        List<String> data = mDatabaseHelper.getPainData();
        //viewIntensity.("NAME: " + data.get(0));


        bodyPart.setText("BODY PART: " + data.get(1));
        context.setText("CONTEXT: " + data.get(2));
        whatHelped.setText("WHAT HELPED: " + data.get(3));

    }




}
