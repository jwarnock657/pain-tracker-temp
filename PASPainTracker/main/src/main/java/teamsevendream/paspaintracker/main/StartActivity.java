package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private static String TAG = "StartActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnGetStarted;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnGetStarted = findViewById(R.id.btnGetStarted);
        mDatabaseHelper = new DatabaseHelper(this);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean empty = mDatabaseHelper.checkTableUserDataEmpty();
                if(empty) {
                    startActivity(new Intent(StartActivity.this, PersonalEntry.class));
                }
                else {
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                }
            }
        });
    }

}

