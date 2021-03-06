package track.gpschamp.com.gpschamp.ui.fragments.object;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.object.FieldObjects;
import track.gpschamp.com.gpschamp.ui.activities.SingleObjectActivity;
import track.gpschamp.com.gpschamp.ui.adapters.ObjectsAdapter;
import track.gpschamp.com.gpschamp.utils.Constants;


public class RunningFragment extends Fragment {

    public RunningFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.object_list)
    ListView objectListView;

    @BindView(R.id.fallback)
    TextView fallback;

    List<FieldObjects> runningObjects = new ArrayList<>();
    ObjectsAdapter objectsAdapter;

    @BindView(R.id.search)
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_running, container, false);
        ButterKnife.bind(this, rootView);


        Typeface semibold = Typeface.createFromAsset(getActivity().getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        fallback.setTypeface(semibold);
        search.setTypeface(semibold);

        if (runningObjects.size() > 0)
            runningObjects.clear();

        for (FieldObjects fieldObjects : ObjectListFragment.objects) {
            if (Integer.parseInt(fieldObjects.getSpeed()) > 0) {
                runningObjects.add(fieldObjects);
            }
        }

        if (runningObjects.size() > 0) {
            objectsAdapter = new ObjectsAdapter(runningObjects, getActivity());
            objectListView.setAdapter(objectsAdapter);
        } else {
            fallback.setVisibility(View.VISIBLE);
        }

        objectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SingleObjectActivity.class);
                intent.putExtra("speed", runningObjects.get(i).getSpeed());
                intent.putExtra("name", runningObjects.get(i).getName());
                intent.putExtra("imei", runningObjects.get(i).getImei());
                intent.putExtra("lat", runningObjects.get(i).getLat());
                intent.putExtra("lng", runningObjects.get(i).getLng());
                startActivity(intent);
            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<FieldObjects> filteredobjects = new ArrayList<>();
                if(runningObjects.size()>1){
                    String s = search.getText().toString();


                    for (FieldObjects obj : runningObjects) {
                        if (obj.getName().contains(s) || obj.getImei().contains(s)) {
                            filteredobjects.add(obj);
                        }
                    }

                    if (filteredobjects.size() > 0)
                        setUpAdapter(filteredobjects);
                    else
                        restoreList();
                }
            }
        });


        return rootView;
    }

    private void setUpAdapter(List<FieldObjects> filteredobjects) {
        objectsAdapter = new ObjectsAdapter(filteredobjects, getActivity());
        objectListView.setAdapter(objectsAdapter);
        objectsAdapter.notifyDataSetChanged();
    }


    private void restoreList(){
        objectsAdapter = new ObjectsAdapter(runningObjects, getActivity());
        objectListView.setAdapter(objectsAdapter);
        objectsAdapter.notifyDataSetChanged();
    }

}
