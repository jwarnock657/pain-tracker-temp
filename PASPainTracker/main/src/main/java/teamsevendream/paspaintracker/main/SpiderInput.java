package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SpiderInput extends AppCompatActivity {

    private static String TAG = "SpiderInput";

    DatabaseHelper mDatabaseHelper;
    private Button btnSubmitSpiderData;
    private SeekBar spiderAnswer1;
    private SeekBar spiderAnswer2;
    private SeekBar spiderAnswer3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_input);
        spiderAnswer1 = findViewById(R.id.spiderAnswer1);
        spiderAnswer2 = findViewById(R.id.spiderAnswer2);
        spiderAnswer3 = findViewById(R.id.spiderAnswer3);
        btnSubmitSpiderData = findViewById(R.id.btnSubmitSpiderData);
        mDatabaseHelper = new DatabaseHelper(this);

        btnSubmitSpiderData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                int answer1 = spiderAnswer1.getProgress();
                int answer2 = spiderAnswer2.getProgress();
                int answer3 = spiderAnswer3.getProgress();
                AddData(answer1, answer2, answer3);
            }
        });
    }

    public void AddData(int answer1, int answer2, int answer3){
        boolean insertData = mDatabaseHelper.addSpiderData(answer1, answer2, answer3);

        if(insertData){
            toastMessage("Data added successfully!");
            startActivity(new Intent(SpiderInput.this, MainActivity.class));
        }
        else{
            toastMessage("Error with insertion!");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
