package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private TextView welcomeMessage;
    private Button btnRecordSpider;
    private Button btnViewSpider;
    private Button btnViewPersonalDetails;
    private Button btnRecordPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeMessage = findViewById(R.id.WelcomeMessage);
        btnRecordSpider = findViewById(R.id.btnRecordSpider);
        btnViewSpider = findViewById(R.id.btnViewSpider);
        btnViewPersonalDetails = findViewById(R.id.btnViewPersonalDetails);
        btnRecordPain = findViewById(R.id.btnRecordPain);
        mDatabaseHelper = new DatabaseHelper(this);
        setWelcomeMessage();

        btnRecordSpider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, SpiderInput.class));
            }
        });

        btnViewSpider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, SpiderOutput.class));
            }
        });

        btnViewPersonalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, PersonalView.class));
            }
        });

        btnRecordPain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, RecordPain.class));
            }
        });
    }

    private void setWelcomeMessage() {
        List<String> data = mDatabaseHelper.getUserData();
        welcomeMessage.setText("WELCOME, " + data.get(0));
    }

}
