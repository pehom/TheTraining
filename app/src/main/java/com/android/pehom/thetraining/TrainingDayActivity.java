package com.android.pehom.thetraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TrainingDayActivity extends AppCompatActivity {
    private int dayNumber, thePullupsCount, setsDone;
    private TrainingDay[] sets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_day);
        final Intent intent = getIntent();
        dayNumber = intent.getIntExtra("day number", 0);
        thePullupsCount = intent.getIntExtra("count", 10);
        setsDone = intent.getIntExtra("setsDone", 0);
        TextView exerciseTextView = findViewById(R.id.exerciseTextView);
        exerciseTextView.setText("Pull-ups   Day " + (dayNumber+1));
        sets = new TrainingDay[] {new TrainingDay((TextView) findViewById(R.id.row1TextView), false, 1),
                new TrainingDay((TextView) findViewById(R.id.row2TextView), false, 2),
                new TrainingDay((TextView) findViewById(R.id.row3TextView), false, 3),
                new TrainingDay((TextView) findViewById(R.id.row4TextView), false, 4),
                new TrainingDay((TextView) findViewById(R.id.row5TextView), false, 5)};
        for (int i=0; i<5;i++) {
            sets[i].getThisDayTextView().setText(""+thePullupsCount);
            if (i<setsDone && setsDone > 0) {
                sets[i].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            } else {
                sets[i].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
            }
            final int j = i;
            sets[i].getThisDayTextView().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (j>0) {
                        if (!sets[j-1].isDone()) {
                            sets[j].setDone(true);
                            sets[j].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                            sets[j].getThisDayTextView().setClickable(false);
                            setsDone++;
                            if (j==4) {
                                Intent intent1 = new Intent(TrainingDayActivity.this, MainActivity.class);
                                intent1.putExtra("daysCompleted", dayNumber);
                                startActivity(intent1);
                               // finish();
                            }
                        }else {
                            sets[j].setDone(true);
                            sets[j].getThisDayTextView().setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
                            sets[j].getThisDayTextView().setClickable(false);
                            setsDone++;
                        }
                    }

                }
            });
        }

    }


}
