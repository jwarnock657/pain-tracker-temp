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

import java.util.List;

public class SpiderOutput extends AppCompatActivity {

    private static String TAG = "SpiderOutput";

    DatabaseHelper mDatabaseHelper;
    private Button btnGoHome;
    private TextView spiderAnswer1;
    private TextView spiderAnswer2;
    private TextView spiderAnswer3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_output);
        spiderAnswer1 = findViewById(R.id.spiderAnswer1);
        spiderAnswer2 = findViewById(R.id.spiderAnswer2);
        spiderAnswer3 = findViewById(R.id.spiderAnswer3);
        btnGoHome = findViewById(R.id.btnGoHome);
        mDatabaseHelper = new DatabaseHelper(this);
        viewSpiderOutput();

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(SpiderOutput.this, MainActivity.class));
            }
        });
    }

    private void viewSpiderOutput() {
        List<Integer> data = mDatabaseHelper.getSpiderData();
        spiderAnswer1.setText("Answer: " + data.get(0).toString());
        spiderAnswer2.setText("Answer: " + data.get(1).toString());
        spiderAnswer3.setText("Answer: " + data.get(2).toString());
    }

}
