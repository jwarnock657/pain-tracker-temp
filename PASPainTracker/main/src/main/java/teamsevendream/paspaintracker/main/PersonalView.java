package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PersonalView extends AppCompatActivity {

    private static String TAG = "PersonalView";

    DatabaseHelper mDatabaseHelper;
    private Button btnChangePersonalData;
    private Button btnGoHome;
    private TextView nameView;
    private TextView surnameView;
    private TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_view);
        nameView = findViewById(R.id.nameView);
        surnameView = findViewById(R.id.surnameView);
        dateView = findViewById(R.id.dateView);
        btnChangePersonalData = findViewById(R.id.btnChangePersonalData);
        btnGoHome = findViewById(R.id.btnGoHome);
        mDatabaseHelper = new DatabaseHelper(this);
        viewPersonalData();

        btnChangePersonalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalView.this, PersonalEntry.class));
            }
        });

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalView.this, MainActivity.class));
            }
        });
    }

    private void viewPersonalData() {
        List<String> data = mDatabaseHelper.getUserData();
        nameView.setText("NAME: " + data.get(0));
        surnameView.setText("SURNAME: " + data.get(1));
        dateView.setText("DATE OF BIRTH: " + data.get(2));
    }

}
