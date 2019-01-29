package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.SeekBar;
import android.widget.EditText;
import android.text.InputType;


import java.util.List;

public class RecordPain extends AppCompatActivity {
    private static String TAG = "RecordPain";
    DatabaseHelper mDatabaseHelper;
    private Spinner dropdown;
    private SeekBar painAnswer;
    private EditText input1;
    private EditText input2;
    private DatePickerDialog picker;
    private EditText eText;
    private Button btnSubmitPainData;
    private String dateInput;
    private String fixedMonth;
    private String fixedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_input);
        mDatabaseHelper = new DatabaseHelper(this);
        dropdown = findViewById(R.id.spinner1);
        String items[] = new String[]{"Head", "Neck", "Chest", "Abdomen", "Hip", "Left Shoulder", "Right Shoulder", "Left Arm", "Right Arm",
                "Left Elbow", "Right Elbow", "Left Hand", "Right Hand", "Left Leg", "Right Leg", "Left Foot", "Right Foot"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        painAnswer = findViewById(R.id.seekBar);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        btnSubmitPainData =findViewById(R.id.btnSubmitPainData);
        eText = (EditText) findViewById(R.id.DateEdit);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(RecordPain.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                fixedDay = Integer.toString(dayOfMonth);
                                fixedMonth = Integer.toString(monthOfYear + 1);
                                if (dayOfMonth <= 9){
                                    fixedDay = "0"+Integer.toString(dayOfMonth);
                                }
                                if ((monthOfYear+ 1) <= 9) {
                                    fixedMonth = "0"+Integer.toString(monthOfYear + 1);
                                }
                                dateInput = fixedDay + "/" + fixedMonth + "/" +Integer.toString(year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnSubmitPainData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedEntry = dropdown.getSelectedItem().toString();
                int seekerAnswer = painAnswer.getProgress();
                String contextInput = input1.getText().toString();
                String whatHelpedInput = input2.getText().toString();
                if (contextInput.length() != 0 && whatHelpedInput.length() != 0 && selectedEntry.length() != 0
                        && dateInput.length() != 0){
                    AddData(selectedEntry, contextInput, whatHelpedInput, dateInput, seekerAnswer);
                }
                else {
                    toastMessage("You must fill all the fields!");
                }
            }
        });
    }
    public void AddData(String selectedEntry, String contextInput, String whatHelpedInput, String dateInput, int seekerAnswer){
        boolean insertData;
        insertData = mDatabaseHelper.createPainData(seekerAnswer, selectedEntry, contextInput, whatHelpedInput, dateInput);
        if (insertData){
            toastMessage("Data added successfully!");
        }
        else{
            toastMessage("Error with insertion!");
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}