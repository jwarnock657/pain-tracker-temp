package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PersonalEntry extends AppCompatActivity {

    private static String TAG = "PersonalEntry";

    DatabaseHelper mDatabaseHelper;
    private Button btnSubmitPersonalData;
    private EditText nameEntry;
    private EditText dateEntry;
    private EditText genderEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_entry);
        nameEntry = findViewById(R.id.nameEntry);
        dateEntry = findViewById(R.id.dateEntry);
        genderEntry = findViewById(R.id.genderEntry);
        btnSubmitPersonalData = findViewById(R.id.btnSubmitPersonalData);
        Log.d(TAG, "Creating database...");
        mDatabaseHelper = new DatabaseHelper(this);

        btnSubmitPersonalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEntry.getText().toString();
                String dateOfBirth = dateEntry.getText().toString();
                String gender = genderEntry.getText().toString();
                if(nameEntry.length() != 0 && dateEntry.length() != 0 && genderEntry.length() != 0){
                    nameEntry.setText("");
                    dateEntry.setText("");
                    genderEntry.setText("");
                    AddData(name, dateOfBirth, gender);
                }
                else{
                    toastMessage("You must fill all the fields!");
                }
            }
        });
    }

    public void AddData(String name, String dateOfBirth, String gender){
        boolean insertData = mDatabaseHelper.addPersonalData(name, dateOfBirth, gender);

        if(insertData){
            toastMessage("Data added successfully!");
            startActivity(new Intent(PersonalEntry.this, SpiderInput.class));
        }
        else{
            toastMessage("Error with insertion!");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
