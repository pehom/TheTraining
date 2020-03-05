package com.android.pehom.thetraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final String fileName = "trainingState";

    private TrainingDay[] days;
    private boolean[] isDone = new boolean[28];
            /*day1, day2,  day3,day4,day5,day6,day7,day8,day9,day10,day11,day12,day13,day14,
            day15,day16,day17,day18,day19,day20,day21,day22,day23,day24,day25,day26,day27,day28;*/
    private LinearLayout createTableLinearLayout, tableLinearLayout;
    private TextView pullupsCountTextView, pullupsTitleTextView;
    private int pullupsCount, thePullupsCount;
    private int daysCompleted, setsDone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] readFile;
    //    readFile = readFromFile(this).split(">>");


        /*if (readFile.length>1) {
                Log.d("mylog", "readFile = " + readFile);
                daysCompleted = Integer.parseInt(readFile[0]);
                pullupsCount = Integer.parseInt(readFile[0]);
                Log.d("mylog", "daysCompleted = "+ daysCompleted + "\n" + "pullupsCount = " + pullupsCount);
                createTrainingTable(daysCompleted);
                createTable( daysCompleted);
            } else*/

                {
                final float[] startx = new float[1];
                final float[] stopx = new float[1];
                pullupsCountTextView = findViewById(R.id.pullupsCountTextView);
                pullupsCountTextView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: // нажатие
                                startx[0] = event.getRawX();
                                break;
                            case MotionEvent.ACTION_MOVE: // движение
                                break;
                            case MotionEvent.ACTION_UP: // отпускание
                                stopx[0] = event.getRawX();
                                Log.d("mylog", "startx = " + startx[0] + "  stopx = " + stopx[0]);
                                if (stopx[0] > startx[0]) {
                                    pullupsCount--;
                                    if (pullupsCount>-1){
                                        pullupsCountTextView.setText(""+ pullupsCount);
                                    }
                                } else if (stopx[0] < startx[0]) {
                                    pullupsCount++;
                                    if (pullupsCount>-1){
                                        pullupsCountTextView.setText("" + pullupsCount);
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                Log.d("mylog", "action canceled");
                                break;
                        }

                        return true;
                    }
                });
                ImageView createTrainingTableImageView = findViewById(R.id.createTrainingTableImageView);
                createTrainingTableImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        thePullupsCount = pullupsCount;
                        setsDone = 0;
                        createTrainingTable(0);
                        TextView pullupsTitleTextView = findViewById(R.id.pullupsTitleTextView);
                        pullupsTitleTextView.setText("Pull-ups. set = " + thePullupsCount);

                    }
                });

            }

    }

    public void createTrainingTable(int daysQompleted) {
        days = new TrainingDay[]{new TrainingDay((TextView) findViewById(R.id.day1TextView), false, 1),
                new TrainingDay((TextView) findViewById(R.id.day2TextView), false, 2),
                new TrainingDay((TextView) findViewById(R.id.day3TextView), false, 3),
                new TrainingDay((TextView) findViewById(R.id.day4TextView), false, 4),
                new TrainingDay((TextView) findViewById(R.id.day5TextView), false, 5),
                new TrainingDay((TextView) findViewById(R.id.day6TextView), false, 6),
                new TrainingDay((TextView) findViewById(R.id.day7TextView), false, 7),
                new TrainingDay((TextView) findViewById(R.id.day8TextView), false, 8),
                new TrainingDay((TextView) findViewById(R.id.day9TextView), false, 9),
                new TrainingDay((TextView) findViewById(R.id.day10TextView), false, 10),
                new TrainingDay((TextView) findViewById(R.id.day11TextView), false, 11),
                new TrainingDay((TextView) findViewById(R.id.day12TextView), false, 12),
                new TrainingDay((TextView) findViewById(R.id.day13TextView), false, 13),
                new TrainingDay((TextView) findViewById(R.id.day14TextView), false, 14),
                new TrainingDay((TextView) findViewById(R.id.day15TextView), false, 15),
                new TrainingDay((TextView) findViewById(R.id.day16TextView), false, 16),
                new TrainingDay((TextView) findViewById(R.id.day17TextView), false, 17),
                new TrainingDay((TextView) findViewById(R.id.day18TextView), false, 18),
                new TrainingDay((TextView) findViewById(R.id.day19TextView), false, 19),
                new TrainingDay((TextView) findViewById(R.id.day20TextView), false, 20),
                new TrainingDay((TextView) findViewById(R.id.day21TextView), false, 21),
                new TrainingDay((TextView) findViewById(R.id.day22TextView), false, 22),
                new TrainingDay((TextView) findViewById(R.id.day23TextView), false, 23),
                new TrainingDay((TextView) findViewById(R.id.day24TextView), false, 24),
                new TrainingDay((TextView) findViewById(R.id.day25TextView), false, 25),
                new TrainingDay((TextView) findViewById(R.id.day26TextView), false, 26),
                new TrainingDay((TextView) findViewById(R.id.day27TextView), false, 27),
                new TrainingDay((TextView) findViewById(R.id.day28TextView), false, 28),
        };
        daysCompleted =  daysQompleted;
        for (int i=0;i<28; i++){
            if (i<daysCompleted && daysCompleted>0)  days[i].setDone(true);
            Log.d("mylog", "day" + i + " isDone = " + days[i].isDone());
        }
        pullupsCountTextView = findViewById(R.id.pullupsCountTextView);
        pullupsCount = Integer.parseInt(pullupsCountTextView.getText().toString());
        createTableLinearLayout = findViewById(R.id.createTableLinearLayout);
        createTableLinearLayout.setVisibility(View.GONE);
        tableLinearLayout = findViewById(R.id.tableLinearLayout);
        tableLinearLayout.setVisibility(View.VISIBLE);
        pullupsTitleTextView = findViewById(R.id.pullupsTitleTextView);
        pullupsTitleTextView.setVisibility(View.VISIBLE);

        for (int j = 0; j<days.length;j++){
            days[j].getThisDayTextView().setText(""+(j+1));
            if (j <daysCompleted && daysCompleted>0){
                days[j].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            } else {
                days[j].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
            final int finalJ = j;
            days[j].getThisDayTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("mylog", "click on day"+finalJ);
                    Intent intent = new Intent(MainActivity.this, TrainingDayActivity.class);
                    intent.putExtra("count", thePullupsCount);
                    intent.putExtra("day number", finalJ);
                    intent.putExtra("setsDone", setsDone);
                    startActivity(intent);
                    /*if (finalJ > 0) {
                        if (days[finalJ-1].isDone()) {
                            days[finalJ].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                            days[finalJ].getThisDayTextView().setClickable(false);
                            days[finalJ].setDone(true);
                            daysCompleted++;
                            if (finalJ == days.length-1) {
                                buildMainScreen();
                            }
                        }
                    } else {
                        days[finalJ].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                        days[finalJ].getThisDayTextView().setClickable(false);
                        days[finalJ].setDone(true);
                        daysCompleted++;
                    }*/
                }
            });
        }

    }


    private void buildMainScreen(){
       // createTableLinearLayout = findViewById(R.id.createTableLinearLayout);
        createTableLinearLayout.setVisibility(View.VISIBLE);
     //   tableLinearLayout = findViewById(R.id.tableLinearLayout);
        tableLinearLayout.setVisibility(View.GONE);
      //  pullupsTitleTextView = findViewById(R.id.pullupsTitleTextView);
        pullupsTitleTextView.setVisibility(View.GONE);

    }
    private void writeToFile(Context context, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));

            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeToFile(this, "" + daysCompleted+ ">>" + pullupsCount);
        Log.d("mylog", "onDestroy writeToFile() = " + daysCompleted+ ">>" + pullupsCount);
        Log.d("mylog", "onDestroy readFromFile() = " +  readFromFile(this));
    }
}
