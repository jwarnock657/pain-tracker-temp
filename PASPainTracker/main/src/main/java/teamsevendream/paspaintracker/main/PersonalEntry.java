package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class PersonalEntry extends AppCompatActivity {

    private static String TAG = "PersonalEntry";

    DatabaseHelper mDatabaseHelper;
    private Button btnSubmitPersonalData;
    private EditText nameEntry;
    private EditText surnameEntry;
    private EditText dateEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_entry);
        nameEntry = findViewById(R.id.nameEntry);
        surnameEntry = findViewById(R.id.surnameEntry);
        dateEntry = findViewById(R.id.dateEntry);
        btnSubmitPersonalData = findViewById(R.id.btnSubmitPersonalData);
        Log.d(TAG, "Creating database...");
        mDatabaseHelper = new DatabaseHelper(this);
        getUserData();

        btnSubmitPersonalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEntry.getText().toString();
                String surname = surnameEntry.getText().toString();
                String dateOfBirth = dateEntry.getText().toString();
                if(nameEntry.length() != 0 && surnameEntry.length() != 0 && dateEntry.length() != 0) {
                    nameEntry.setText("");
                    surnameEntry.setText("");
                    dateEntry.setText("");
                    AddData(name, surname, dateOfBirth);
                }
                else {
                    toastMessage("You must fill all the fields!");
                }
            }
        });
    }

    public void AddData(String name, String surname, String dateOfBirth){
        boolean empty = mDatabaseHelper.checkTableUserDataEmpty();
        boolean insertData;
        boolean checkStart;
        if(empty) {
            insertData = mDatabaseHelper.createUserData(name, surname, dateOfBirth);
            checkStart = true;
        }
        else {
            insertData = mDatabaseHelper.updateUserData(name, surname, dateOfBirth);
            checkStart = false;
        }

        if(insertData){
            toastMessage("Data added successfully!");
            if(checkStart) {
                startActivity(new Intent(PersonalEntry.this, SpiderInput.class));
            }
            else {
                startActivity(new Intent(PersonalEntry.this, MainActivity.class));
            }
        }
        else{
            toastMessage("Error with insertion!");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getUserData() {
        if(!mDatabaseHelper.checkTableUserDataEmpty()){
            List<String> data = mDatabaseHelper.getUserData();
            nameEntry.setText(data.get(0));
            surnameEntry.setText(data.get(1));
            dateEntry.setText(data.get(2));
        }

    }
}
