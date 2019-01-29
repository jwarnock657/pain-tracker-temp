package teamsevendream.paspaintracker.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.RadarChart;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SpiderOutput extends AppCompatActivity {

    private static String TAG = "SpiderOutput";

    DatabaseHelper mDatabaseHelper;
    private Button btnGoHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spider_output);

        RadarChart radarChart = (RadarChart) findViewById(R.id.radar_chart);
        btnGoHome = findViewById(R.id.btnGoHome);
        mDatabaseHelper = new DatabaseHelper(this);
        ArrayList<String> label = new ArrayList();
        label.add("Question1");
        label.add("Question2");
        label.add("Question3");
        label.add("Question4");
        label.add("Question5");
        label.add("Question6");
        label.add("Question7");
        label.add("Question8");
        label.add("Question9");
        label.add("Question10");
        label.add("Question11");
        label.add("Question12");
        ArrayList<Float> entries = new ArrayList<>();
        List<Integer> data = mDatabaseHelper.getSpiderData();
        //entries.add(0f);
        //entries.add(1f);
        //entries.add(2f);
        //entries.add(3f);
        //entries.add(4f);
        //entries.add(5f);

        entries.add((float) (data.get(0)));
        entries.add(data.get(1).floatValue());
        entries.add((float) (data.get(1)));
        entries.add((float) data.get(2));
        entries.add((float) data.get(3));
        entries.add((float) data.get(4));
        entries.add((float) data.get(5));
        entries.add((float) data.get(6));
        entries.add((float) data.get(7));
        entries.add((float) data.get(8));
        entries.add((float) data.get(9));
        entries.add((float) data.get(10));
        entries.add((float) data.get(11));

        try {
            JSONObject dataSet = new JSONObject();
            dataSet.put("labels", label.toString());
            JSONObject val = new JSONObject();
            val.put("PAIN", entries.toString());
            dataSet.put("values", val.toString());
            ArrayList<ChartData> values = new ArrayList();
            values.add(new ChartData(dataSet));
            radarChart.setData(values);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        //viewSpiderOutput();

    //    btnGoHome.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View view){
    //            startActivity(new Intent(SpiderOutput.this, MainActivity.class));
    //        }
    //    });
    }

    //private void viewSpiderOutput() {
        //List<Integer> data = mDatabaseHelper.getSpiderData();
        //spiderAnswer1.setText("Answer: " + data.get(0).toString());
        //spiderAnswer2.setText("Answer: " + data.get(1).toString());
        //spiderAnswer3.setText("Answer: " + data.get(2).toString());
    //}

}