package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.events.EventRow;
import track.gpschamp.com.gpschamp.model.events.EventsObject;
import track.gpschamp.com.gpschamp.ui.activities.EventMapActivity;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 19/02/18.
 */

public class EventsAdapter extends BaseAdapter {

    Context context;
    List<EventRow> eventRows;
    Communicator communicator;
    public EventsAdapter(Context context, List<EventRow> eventRows, Communicator communicator){
        this.context = context;
        this.communicator = communicator;
        this.eventRows = eventRows;
    }
    @Override
    public int getCount() {
        return eventRows.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.event_row_adapter_layout, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.objectName.setText(eventRows.get(i).getEventCell().getObjectName());
        viewHolder.dtTime.setText(eventRows.get(i).getEventCell().getEventTime());
        viewHolder.eventName.setText(eventRows.get(i).getEventCell().getEventName());

        if(i == eventRows.size()-1){
            viewHolder.loadMoreBtn.setVisibility(View.VISIBLE);
        }else {
            viewHolder.loadMoreBtn.setVisibility(View.GONE);
        }

        viewHolder.loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicator.loadMore();
            }
        });
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventMapActivity.class);
                intent.putExtra("id", eventRows.get(i).getId());
                intent.putExtra("event_name", eventRows.get(i).getEventCell().getEventName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder{

        @BindView(R.id.object_name)
        TextView objectName;

        @BindView(R.id.event_name)
        TextView eventName;

        @BindView(R.id.dt_time)
        TextView dtTime;

        @BindView(R.id.card)
        CardView cardView;

        @BindView(R.id.load_more)
        Button loadMoreBtn;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
            Typeface regular = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
            Typeface  bold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
            objectName.setTypeface(regular);
            loadMoreBtn.setTypeface(bold);
            eventName.setTypeface(bold);
            dtTime.setTypeface(regular);
        }
    }

    public interface Communicator{
        void loadMore();
    }
}
