package track.gpschamp.com.gpschamp.ui.fragments.images;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.images.ImagesObject;
import track.gpschamp.com.gpschamp.model.images.ImagesObjectsResponse;
import track.gpschamp.com.gpschamp.model.images.ObjectsImagesResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.activities.ImagesActivity;
import track.gpschamp.com.gpschamp.ui.adapters.AlertAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {


    public ImagesFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.object_tv)
    TextView objectTv;


    @BindView(R.id.start_tv)
    TextView startTv;

    @BindView(R.id.end_tv)
    TextView endTv;

    @BindView(R.id.show_images)
    Button showImageBtn;

    Typeface regular, semibold;
    PrefsUtil prefsUtil;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_images, container, false);
        ButterKnife.bind(this, rootView);

        regular = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        semibold = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        objectTv.setTypeface(regular);
        startTv.setTypeface(regular);
        endTv.setTypeface(regular);


        showImageBtn.setTypeface(semibold);
        prefsUtil = new PrefsUtil(getActivity());
        progressBackground.setVisibility(View.VISIBLE);

        findObject(prefsUtil.getString(Constants.API_TOKEN));
        return rootView;
    }


    List<ImagesObject> imagesObjects = new ArrayList<>();

    private void findObject(String string) {

        GpsWebService gpsWebService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<ImagesObjectsResponse> call = gpsWebService.getObjectWithImages(string);

        call.enqueue(new Callback<ImagesObjectsResponse>() {
            @Override
            public void onResponse(Call<ImagesObjectsResponse> call, Response<ImagesObjectsResponse> response) {
                progressBackground.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    imagesObjects = response.body().getImagesObjectList();
                }
            }

            @Override
            public void onFailure(Call<ImagesObjectsResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    int position = 0;
    AlertDialog alertDialog;

    @OnClick(R.id.obj_rel)
    void onObjectClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);


        AlertAdapter simpleListAdapter = new AlertAdapter(getActivity(), imagesObjects);
        listView.setAdapter(simpleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                alertDialog.dismiss();
                objectTv.setText(imagesObjects.get(i).getName());
            }
        });


        alertDialogBuilder.setView(alertView);
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    int mHour, mMinutes, mDate, mMonth, mYear;

    @OnClick(R.id.type_rel)
    void onStartDateClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10 && dayOfMonth < 10) {
                    startTv.setText(year + "-0" + month + "-0" + dayOfMonth);
                } else if (month > 9 && dayOfMonth < 10) {
                    startTv.setText(year + "-" + month + "-0" + dayOfMonth);
                } else if (month < 10 && dayOfMonth > 9) {
                    startTv.setText(year + "-0" + month + "-" + dayOfMonth);
                } else {
                    startTv.setText(year + "-" + month + "-" + dayOfMonth);
                }


            }
        }, mYear, mMonth, mDate);


        datePickerDialog.getDatePicker();

        datePickerDialog.show();
    }

    @OnClick(R.id.command_rel)
    void onEndDateClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10 && dayOfMonth < 10) {
                    endTv.setText(year + "-0" + month + "-0" + dayOfMonth);
                } else if (month > 9 && dayOfMonth < 10) {
                    endTv.setText(year + "-" + month + "-0" + dayOfMonth);
                } else if (month < 10 && dayOfMonth > 9) {
                    endTv.setText(year + "-0" + month + "-" + dayOfMonth);
                } else {
                    endTv.setText(year + "-" + month + "-" + dayOfMonth);
                }


            }
        }, mYear, mMonth, mDate);


        datePickerDialog.getDatePicker();

        datePickerDialog.show();
    }


    int page = 1;

    public static ObjectsImagesResponse objectsImagesResponse;

    @OnClick(R.id.show_images)
    void onShowImagesClick() {
        progressBackground.setVisibility(View.VISIBLE);
        GpsWebService gpsWebService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<ObjectsImagesResponse> call = gpsWebService.getImages(prefsUtil.getString(Constants.API_TOKEN), 25, page,
                "dt_tracker", "asc", endTv.getText().toString(), startTv.getText().toString(), imagesObjects.get(position).getImei());

        call.enqueue(new Callback<ObjectsImagesResponse>() {
            @Override
            public void onResponse(Call<ObjectsImagesResponse> call, Response<ObjectsImagesResponse> response) {
                progressBackground.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getData().getRecords() > 0) {
                        objectsImagesResponse = response.body();
                        Intent intent = new Intent(getActivity(), ImagesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "No images to display for the object", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ObjectsImagesResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
            }
        });
    }
}
