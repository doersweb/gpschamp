package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.object.FieldObjects;
import track.gpschamp.com.gpschamp.remote.WebServiceClient;
import track.gpschamp.com.gpschamp.ui.activities.SingleObjectActivity;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.SVG;
import track.gpschamp.com.gpschamp.utils.SVGParser;

//import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by sudhirharit on 06/02/18.
 */

public class ObjectsAdapter extends BaseAdapter {

    List<FieldObjects> fieldObjects;
    Context context;

    public ObjectsAdapter(List<FieldObjects> fieldObjects, Context context) {
        this.fieldObjects = fieldObjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fieldObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return fieldObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.object_list_item, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final FieldObjects fieldObjects = (FieldObjects) getItem(i);
        viewHolder.objectName.setText(fieldObjects.getName());
        viewHolder.dte.setText(fieldObjects.getDt_tracker());
        viewHolder.time.setVisibility(View.GONE);

        if (fieldObjects.getAcc()!=null) {
            viewHolder.engineIcon.setVisibility(View.VISIBLE);
            if (!fieldObjects.getSpeed().equals("0")) {
                viewHolder.engineIcon.setImageResource(R.drawable.ic_object_list_engine_orange_2);
            } else {
                viewHolder.engineIcon.setImageResource(R.drawable.ic_object_list_engine_status_green);
            }
        } else {
            viewHolder.engineIcon.setVisibility(View.GONE);
        }


        if (fieldObjects.getAcStatus() != null) {
            viewHolder.acIcon.setVisibility(View.VISIBLE);
            if(fieldObjects.getAcStatus().equals("0")){
                viewHolder.acIcon.setImageResource(R.drawable.ic_ac_red);
            }else {
                viewHolder.acIcon.setImageResource(R.drawable.ic_ac_green);
            }
        } else {
            viewHolder.acIcon.setVisibility(View.GONE);
        }

        if(fieldObjects.getBatteryLevel()!=null){
            viewHolder.batterIcon.setVisibility(View.VISIBLE);
            double batteryLevel = Double.parseDouble(fieldObjects.getBatteryLevel());
            if(batteryLevel<3){
                viewHolder.batterIcon.setImageResource(R.drawable.ic_object_battery_red);
            }else if(batteryLevel<5 && batteryLevel>=3){
                viewHolder.batterIcon.setImageResource(R.drawable.ic_object_battery_orange);
            }else if(batteryLevel>=5){
                viewHolder.batterIcon.setImageResource(R.drawable.ic_object_list_battery_status_full_green);
            }
        }else {
            viewHolder.batterIcon.setVisibility(View.GONE);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date dateServer = null;
        try {
            dateServer = dateFormat.parse(fieldObjects.getDateServer());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        date.setTime(System.currentTimeMillis());

        long diff = date.getTime() - dateServer.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;

        if(diffMinutes>10){
            viewHolder.signalIcon.setImageResource(R.drawable.ic_signal_red);
        }else {
            if(fieldObjects.getGpslevel() == 0){
                viewHolder.signalIcon.setImageResource(R.drawable.ic_signal_orange);
            }else {
                viewHolder.signalIcon.setImageResource(R.drawable.ic_signal_green);
            }
        }


//        RequestBuilder requestBuilder = Glide.with(context)
//                .as(PictureDrawable.class)
//                .transition(withCrossFade());
//
//        requestBuilder.load(Uri.parse(Constants.BASE_URL+fieldObjects.getIcon())).into(viewHolder.objectIcon);
        if(fieldObjects.getIcon().contains("svg")){
            String s = fieldObjects.getIcon().replace("svg", "png");
            Picasso.with(context).load(Constants.BASE_URL+s).into(viewHolder.objectIcon, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("alok", "success");
                }

                @Override
                public void onError() {
                    Log.d("alok", "error");
                }
            });
        }



        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleObjectActivity.class);
                intent.putExtra("speed", fieldObjects.getSpeed());
                intent.putExtra("name", fieldObjects.getName());
                intent.putExtra("imei", fieldObjects.getImei());
                intent.putExtra("lat", fieldObjects.getLat());
                intent.putExtra("lng", fieldObjects.getLng());
                context.startActivity(intent);
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                final URL url;
                try {
                    url = new URL(Constants.BASE_URL+fieldObjects.getIcon());
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    SVG svg = SVGParser.getSVGFromInputStream(inputStream);
                    Drawable drawable = svg.createPictureDrawable();
                    Log.d("alok", drawable.toString());
                    viewHolder.objectIcon.setImageDrawable(drawable);;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("alok", e.getMessage());
                }

            }
        }).start();



        viewHolder.addressTv.setText(fieldObjects.getAddress());
        viewHolder.speed.setText(fieldObjects.getSpeed());

        return convertView;
    }

    public class ViewHolder {

        @BindView(R.id.object_name)
        TextView objectName;

        @BindView(R.id.dte)
        TextView dte;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.engine_icon)
        ImageView engineIcon;

        @BindView(R.id.ac_icon)
        ImageView acIcon;

        @BindView(R.id.battery_icon)
        ImageView batterIcon;

        @BindView(R.id.signal_icon)
        ImageView signalIcon;

        @BindView(R.id.card)
        CardView card;

        @BindView(R.id.address)
        TextView addressTv;

        @BindView(R.id.object_icon)
        ImageView objectIcon;

        @BindView(R.id.speed)
        TextView speed;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            Typeface regular = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
            Typeface semibold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
            Typeface bold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);

            speed.setTypeface(semibold);
            addressTv.setTypeface(regular);
            objectName.setTypeface(semibold);
            dte.setTypeface(regular);
            time.setTypeface(regular);
        }

    }


}
