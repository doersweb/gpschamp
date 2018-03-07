package track.gpschamp.com.gpschamp.ui.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.core.history.HistoryContractor;
import track.gpschamp.com.gpschamp.core.history.HistoryPresenter;
import track.gpschamp.com.gpschamp.model.history.HistoryResponse;
import track.gpschamp.com.gpschamp.model.object.FieldObjects;
import track.gpschamp.com.gpschamp.model.object.ObjectResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.adapters.PlainAdapter;
import track.gpschamp.com.gpschamp.ui.adapters.SimpleListAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class PlayBackFormAcitivity extends AppCompatActivity implements HistoryContractor.View {


    @BindView(R.id.object)
    TextView objectTv;

    @BindView(R.id.start_date)
    TextView startDateTv;

    @BindView(R.id.end_date)
    TextView endDate;

    @BindView(R.id.start_time)
    TextView startTime;

    @BindView(R.id.end_time)
    TextView endTime;

    @BindView(R.id.stop_duration)
    TextView stopDurationTv;

    @BindView(R.id.stops)
    TextView stopsTv;

    @BindView(R.id.events)
    TextView eventsTv;

    @BindView(R.id.app_bar)
    Toolbar appBar;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @BindView(R.id.heading)
    TextView headingTv;

    @BindView(R.id.back_image)
    ImageView backImage;

    @BindView(R.id.submit)
    Button submit;
    Typeface bold, regular, semibold;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    PrefsUtil prefsUtil;
    HistoryPresenter historyPresenter;

    String[] minStopDuration = {"1min", "2min", "5min", "10min", "20min", "30min", "1 hour", "2 hour", "5 hour"};
    String[] stops = {"Yes", "No"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_back_form_acitivity);

        ButterKnife.bind(this);

        setSupportActionBar(appBar);
        prefsUtil = new PrefsUtil(this);

        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);

        headingTv.setTypeface(bold);
        headingTv.setText("PLAYBACK");

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        objectTv.setTypeface(regular);
        startTime.setTypeface(regular);
        endTime.setTypeface(regular);
        startDateTv.setTypeface(regular);
        endDate.setTypeface(regular);
        stopDurationTv.setTypeface(regular);
        eventsTv.setTypeface(regular);
        stopsTv.setTypeface(regular);

        submit.setTypeface(semibold);


        historyPresenter = new HistoryPresenter(this);


        notificationCount.setTypeface(semibold);
        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");
        notificationCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayBackFormAcitivity.this, NotifcationActivity.class);
                startActivity(intent);
            }
        });

        progressBackground.setVisibility(View.VISIBLE);
        finObjects(new PrefsUtil(this).getString(Constants.API_TOKEN));
    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(PlayBackFormAcitivity.this, NotifcationActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        finish();
    }

    List<FieldObjects> objects = new ArrayList<>();

    private void finObjects(String token) {

        GpsWebService gpsWebService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        retrofit2.Call<ObjectResponse> call = gpsWebService.getObjectList(token);

        call.enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ObjectResponse> call, Response<ObjectResponse> response) {
                progressBackground.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    if (response.body().isStatus()) {

                        objects = response.body().getObjectsData();

                    } else {
                        Toast.makeText(PlayBackFormAcitivity.this, "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ObjectResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
                Toast.makeText(PlayBackFormAcitivity.this, "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    int position = 0;
    @OnClick(R.id.object)
    void onObjectsClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);


        SimpleListAdapter simpleListAdapter = new SimpleListAdapter(objects, this);
        listView.setAdapter(simpleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                alertDialog.dismiss();
                objectTv.setText(objects.get(i).getName());
            }
        });


        alertDialogBuilder.setView(alertView);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    AlertDialog alertDialog;

    @OnClick(R.id.events)
    void onEventsClick() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setCancelable(true);
//
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);
//
//        ListView listView = (ListView) alertView.findViewById(R.id.list_view);
//        PlainAdapter simpleListAdapter = new PlainAdapter(stops, this);
//        listView.setAdapter(simpleListAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                alertDialog.dismiss();
//                eventsTv.setText(stops[i]);
//            }
//        });
//
//
//        alertDialogBuilder.setView(alertView);
//
//        alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
    }


    @OnClick(R.id.stops)
    void onStopsClick() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setCancelable(true);
//
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);
//
//        ListView listView = (ListView) alertView.findViewById(R.id.list_view);
//        PlainAdapter simpleListAdapter = new PlainAdapter(stops, this);
//        listView.setAdapter(simpleListAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                alertDialog.dismiss();
//                stopsTv.setText(stops[i]);
//            }
//        });
//
//        alertDialogBuilder.setView(alertView);
//
//        alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
    }

    @OnClick(R.id.stop_duration)
    void onStopDuration() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);
        PlainAdapter simpleListAdapter = new PlainAdapter(minStopDuration, this);
        listView.setAdapter(simpleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertDialog.dismiss();
                stopDurationTv.setText(minStopDuration[i]);
            }
        });

        alertDialogBuilder.setView(alertView);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @OnClick(R.id.start_date_view)
    void onStartDateClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10 && dayOfMonth < 10) {
                    startDateTv.setText(year + "-0" + month + "-0" + dayOfMonth);
                } else if (month > 9 && dayOfMonth < 10) {
                    startDateTv.setText(year + "-" + month + "-0" + dayOfMonth);
                } else if (month < 10 && dayOfMonth > 9) {
                    startDateTv.setText(year + "-0" + month + "-" + dayOfMonth);
                } else {
                    startDateTv.setText(year + "-" + month + "-" + dayOfMonth);
                }

                linearStart.requestFocus();
            }
        }, mYear, mMonth, mDate);


        datePickerDialog.getDatePicker();

        datePickerDialog.show();
    }

    @OnFocusChange(R.id.start_time_view)
    void onStartTimeFocus(){
        if(linearStart.isFocused()) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startHour = hourOfDay;
                    startMin = minute;

                    if (hourOfDay < 10 && minute < 10) {
                        startTime.setText("0" + hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                    } else if (hourOfDay > 9 && minute < 10) {
                        startTime.setText(hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                    } else if (hourOfDay < 10 && minute > 9) {
                        startTime.setText("0" + hourOfDay + ":" + minute /*+ ":00"*/);
                    } else {
                        startTime.setText(hourOfDay + ":" + minute  /*+ ":00"*/);
                    }
                }
            }, mHour, mMinutes, false);
            timePickerDialog.show();
        }
    }

    @BindView(R.id.start_time_view)
    LinearLayout linearStart;

    @BindView(R.id.end_time_view)
    LinearLayout linearEnd;


    @OnFocusChange(R.id.end_time_view)
    void onEndTimeFocus(){
        if(linearEnd.isFocused()) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endHour = hourOfDay;
                    endMin = minute;

                    if (hourOfDay < 10 && minute < 10) {
                        endTime.setText("0" + hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                    } else if (hourOfDay > 9 && minute < 10) {
                        endTime.setText(hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                    } else if (hourOfDay < 10 && minute > 9) {
                        endTime.setText("0" + hourOfDay + ":" + minute /*+ ":00"*/);
                    } else {
                        endTime.setText(hourOfDay + ":" + minute  /*+ ":00"*/);
                    }
                }
            }, mHour, mMinutes, false);
            timePickerDialog.show();
        }
    }

    @OnClick(R.id.end_date_view)
    void onEndDateClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10 && dayOfMonth < 10) {
                    endDate.setText(year + "-0" + month + "-0" + dayOfMonth);
                } else if (month > 9 && dayOfMonth < 10) {
                    endDate.setText(year + "-" + month + "-0" + dayOfMonth);
                } else if (month < 10 && dayOfMonth > 9) {
                    endDate.setText(year + "-0" + month + "-" + dayOfMonth);
                } else {
                    endDate.setText(year + "-" + month + "-" + dayOfMonth);
                }

                linearEnd.requestFocus();
            }
        }, mYear, mMonth, mDate);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 31536000);
        datePickerDialog.getDatePicker();
        datePickerDialog.show();
    }

    @OnClick(R.id.start_time_view)
    void onStartTimeClick() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                startHour = hourOfDay;
                startMin = minute;

                if (hourOfDay < 10 && minute < 10) {
                    startTime.setText("0" + hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                } else if (hourOfDay > 9 && minute < 10) {
                    startTime.setText(hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                } else if (hourOfDay < 10 && minute > 9) {
                    startTime.setText("0" + hourOfDay + ":" + minute /*+ ":00"*/);
                } else {
                    startTime.setText(hourOfDay + ":" + minute  /*+ ":00"*/);
                }
            }
        }, mHour, mMinutes, false);
        timePickerDialog.show();
    }

    int startHour, endHour;
    int startMin, endMin;

    int mHour, mMinutes, mDate, mMonth, mYear;

    @OnClick(R.id.end_time_view)
    void onEndTimeClick() {


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                endHour = hourOfDay;
                endMin = minute;

                if (hourOfDay < 10 && minute < 10) {
                    endTime.setText("0" + hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                } else if (hourOfDay > 9 && minute < 10) {
                    endTime.setText(hourOfDay + ":" + "0" + minute /*+ ":00"*/);
                } else if (hourOfDay < 10 && minute > 9) {
                    endTime.setText("0" + hourOfDay + ":" + minute /*+ ":00"*/);
                } else {
                    endTime.setText(hourOfDay + ":" + minute  /*+ ":00"*/);
                }
            }
        }, mHour, mMinutes, false);
        timePickerDialog.show();
    }

    @OnClick(R.id.submit)
    void onPlaybackDataRequest() {
        progressBackground.setVisibility(View.VISIBLE);
        String s = "";
        if (stopDurationTv.getText().toString().contains("min")) {
            s = stopDurationTv.getText().toString().substring(0, 1);
        } else if (stopDurationTv.getText().toString().contains(" ")) {
            s = stopDurationTv.getText().toString().substring(0, 1);
        }

        historyPresenter.fetchHistory(new PrefsUtil(this).getString(Constants.API_TOKEN), objects.get(position).getImei(), startDateTv.getText().toString() + " " + startTime.getText().toString(),
                endDate.getText().toString() + " " + endTime.getText().toString(), s);

    }

    public static HistoryResponse response;

    @Override
    public void onHistoryResponse(HistoryResponse historyResponse) {
        progressBackground.setVisibility(View.GONE);
        if (historyResponse != null) {
            response = historyResponse;
            Intent intent = new Intent(this, PlaybackActivity.class);
            intent.putExtra("imei", objectTv.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
