package track.gpschamp.com.gpschamp.ui.fragments.command;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.model.object.FieldObjects;
import track.gpschamp.com.gpschamp.model.object.ObjectResponse;
import track.gpschamp.com.gpschamp.remote.GpsWebService;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.adapters.PlainAdapter;
import track.gpschamp.com.gpschamp.ui.adapters.SimpleListAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommandFragment extends Fragment {


    public CommandFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.command)
    Button command;

    @BindView(R.id.command_et)
    EditText commandEt;

    @BindView(R.id.gateway_tv)
    TextView gatewayTv;

    @BindView(R.id.type_tv)
    TextView typeTv;

    @BindView(R.id.object_tv)
    TextView objectTv;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    Typeface bold, regular;
    PrefsUtil prefsUtil;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_command, container, false);
        ButterKnife.bind(this, rootView);


        bold = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        regular = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);

        command.setTypeface(bold);
        commandEt.setTypeface(regular);
        gatewayTv.setTypeface(regular);

        typeTv.setTypeface(regular);
        objectTv.setTypeface(regular);

        prefsUtil = new PrefsUtil(getActivity());
        progressBackground.setVisibility(View.VISIBLE);
        finObjects(prefsUtil.getString(Constants.API_TOKEN));
        return rootView;
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
                        Toast.makeText(getActivity(), "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ObjectResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    int position = 0;
    AlertDialog alertDialog;
    @OnClick(R.id.obj_rel)
    void onObjectsClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);

        SimpleListAdapter simpleListAdapter = new SimpleListAdapter(objects, getActivity());
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

    String[] gatewayD = {"GPRS", "SMS"};
    String[] typeD = {"ASCII", "HEX"};
    String[] templteD = {"CUSTOM","", ""};

    @OnClick(R.id.gateway_rel)
    void onGatewayClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);
        PlainAdapter simpleListAdapter = new PlainAdapter(gatewayD, getActivity());
        listView.setAdapter(simpleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertDialog.dismiss();
                gatewayTv.setText(gatewayD[i]);
            }
        });


        alertDialogBuilder.setView(alertView);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @OnClick(R.id.type_rel)
    void onTypeClick() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertView = layoutInflater.inflate(R.layout.alert_list_dialog_layout, null);

        ListView listView = (ListView) alertView.findViewById(R.id.list_view);
        PlainAdapter simpleListAdapter = new PlainAdapter(typeD, getActivity());
        listView.setAdapter(simpleListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                alertDialog.dismiss();
                typeTv.setText(typeD[i]);
            }
        });


        alertDialogBuilder.setView(alertView);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    @OnClick(R.id.command)
    void onCommandClick(){
        GpsWebService webService = WebServiceClient.getRetrofitClient().create(GpsWebService.class);
        Call<GeneralResponse> call = webService.sendCommand(objects.get(position).getImei(),"", gatewayTv.getText().toString(), typeTv.getText().toString(),
                commandEt.getText().toString());

        progressBackground.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                progressBackground.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Command Sent Successfully", Toast.LENGTH_SHORT).show();
                    typeTv.setText("");
                    gatewayTv.setText("");
                    objectTv.setText("");
                    commandEt.setText("");
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                progressBackground.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Could not process your request, please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
