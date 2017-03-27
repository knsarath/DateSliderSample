package com.sarath.dateslidersample;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sarath.dateslidersample.widgets.DateSlider.AlternativeDateSlider;
import com.sarath.dateslidersample.widgets.DateSlider.CustomDateSlider;
import com.sarath.dateslidersample.widgets.DateSlider.DateSlider;
import com.sarath.dateslidersample.widgets.DateSlider.DateTimeSlider;
import com.sarath.dateslidersample.widgets.DateSlider.DefaultDateSlider;
import com.sarath.dateslidersample.widgets.DateSlider.MonthYearDateSlider;
import com.sarath.dateslidersample.widgets.DateSlider.TimeSlider;
import com.sarath.dateslidersample.widgets.DateSlider.labeler.TimeLabeler;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static final int DEFAULTDATESELECTOR_ID = 0;
    static final int DEFAULTDATESELECTOR_WITHLIMIT_ID = 6;
    static final int ALTERNATIVEDATESELECTOR_ID = 1;
    static final int CUSTOMDATESELECTOR_ID = 2;
    static final int MONTHYEARDATESELECTOR_ID = 3;
    static final int TIMESELECTOR_ID = 4;
    static final int TIMESELECTOR_WITHLIMIT_ID = 7;
    static final int DATETIMESELECTOR_ID = 5;

    private TextView dateText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // load and initialise the Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dateText = (TextView) this.findViewById(R.id.selectedDateLabel);
        Button defaultButton = (Button) this.findViewById(R.id.defaultDateSelectButton);
        // set up a listener for when the button is pressed
        defaultButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(DEFAULTDATESELECTOR_ID);
            }
        });

        Button defaultLimitButton = (Button) this.findViewById(R.id.defaultDateLimitSelectButton);
        // set up a listener for when the button is pressed
        defaultLimitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(DEFAULTDATESELECTOR_WITHLIMIT_ID);
            }
        });

        Button alternativeButton = (Button) this.findViewById(R.id.alternativeDateSelectButton);
        // set up a listener for when the button is pressed
        alternativeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(ALTERNATIVEDATESELECTOR_ID);
            }
        });

        Button customButton = (Button) this.findViewById(R.id.customDateSelectButton);
        // set up a listener for when the button is pressed
        customButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(CUSTOMDATESELECTOR_ID);
            }
        });

        Button monthYearButton = (Button) this.findViewById(R.id.monthYearDateSelectButton);
        // set up a listener for when the button is pressed
        monthYearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(MONTHYEARDATESELECTOR_ID);
            }
        });

        Button timeButton = (Button) this.findViewById(R.id.timeSelectButton);
        // set up a listener for when the button is pressed
        timeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(TIMESELECTOR_ID);
            }
        });

        Button timeLimitButton = (Button) this.findViewById(R.id.timeLimitSelectButton);
        // set up a listener for when the button is pressed
        timeLimitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(TIMESELECTOR_WITHLIMIT_ID);
            }
        });

        Button dateTimeButton = (Button) this.findViewById(R.id.dateTimeSelectButton);
        // set up a listener for when the button is pressed
        dateTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(DATETIMESELECTOR_ID);
            }
        });
    }

    // define the listener which is called once a user selected the date.
    private DateSlider.OnDateSetListener mDateSetListener =
            new DateSlider.OnDateSetListener() {
                public void onDateSet(DateSlider view, Calendar selectedDate) {
                    // update the dateText view with the corresponding date
                    dateText.setText(String.format("The chosen date:%n%te. %tB %tY", selectedDate, selectedDate, selectedDate));
                }
            };

    private DateSlider.OnDateSetListener mMonthYearSetListener =
            new DateSlider.OnDateSetListener() {
                public void onDateSet(DateSlider view, Calendar selectedDate) {
                    // update the dateText view with the corresponding date
                    dateText.setText(String.format("The chosen date:%n%tB %tY", selectedDate, selectedDate));
                }
            };

    private DateSlider.OnDateSetListener mTimeSetListener =
            new DateSlider.OnDateSetListener() {
                public void onDateSet(DateSlider view, Calendar selectedDate) {
                    // update the dateText view with the corresponding date
                    dateText.setText(String.format("The chosen time:%n%tR", selectedDate));
                }
            };

    private DateSlider.OnDateSetListener mDateTimeSetListener =
            new DateSlider.OnDateSetListener() {
                public void onDateSet(DateSlider view, Calendar selectedDate) {
                    // update the dateText view with the corresponding date
                    int minute = selectedDate.get(Calendar.MINUTE) /
                            TimeLabeler.MINUTEINTERVAL*TimeLabeler.MINUTEINTERVAL;
                    dateText.setText(String.format("The chosen date and time:%n%te. %tB %tY%n%tH:%02d",
                            selectedDate, selectedDate, selectedDate, selectedDate, minute));
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        // this method is called after invoking 'showDialog' for the first time
        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller

        final Calendar c = Calendar.getInstance();
        switch (id) {
            case DEFAULTDATESELECTOR_ID:
                return new DefaultDateSlider(this,mDateSetListener,c);
            case DEFAULTDATESELECTOR_WITHLIMIT_ID:
                final Calendar maxTime = Calendar.getInstance();
                maxTime.add(Calendar.DAY_OF_MONTH, 14);
                return new DefaultDateSlider(this,mDateSetListener,c,c,maxTime);
            case ALTERNATIVEDATESELECTOR_ID:
                return new AlternativeDateSlider(this,mDateSetListener,c,c,null);
            case CUSTOMDATESELECTOR_ID:
                return new CustomDateSlider(this,mDateSetListener,c);
            case MONTHYEARDATESELECTOR_ID:
                return new MonthYearDateSlider(this,mMonthYearSetListener,c);
            case TIMESELECTOR_ID:
                return new TimeSlider(this,mTimeSetListener,c,15);
            case TIMESELECTOR_WITHLIMIT_ID:
                final Calendar minTime = Calendar.getInstance();
                minTime.add(Calendar.HOUR, -2);
                return new TimeSlider(this,mTimeSetListener,c,minTime,c,5);
            case DATETIMESELECTOR_ID:
                return new DateTimeSlider(this,mDateTimeSetListener,c);
        }
        return null;
    }
}
