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
    private Button btnViewSpider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeMessage = findViewById(R.id.WelcomeMessage);
        btnViewSpider = findViewById(R.id.btnViewSpider);
        mDatabaseHelper = new DatabaseHelper(this);
        welcomeMessage.setText(getMessage());

        btnViewSpider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, SpiderOutput.class));
            }
        });
    }


    private String getMessage() {
        String data = mDatabaseHelper.getPersonal();
        String message = "WELCOME, " + data;
        Log.d(TAG, "Message: " + message);
        return message;
    }

}
