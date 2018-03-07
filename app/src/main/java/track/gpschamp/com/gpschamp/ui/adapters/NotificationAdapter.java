package track.gpschamp.com.gpschamp.ui.adapters;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.database.Notification;
import track.gpschamp.com.gpschamp.ui.activities.EventMapActivity;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 04/03/18.
 */

public class NotificationAdapter extends BaseAdapter {
    Context context;
    List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.notifications = notifications;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notifications.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.notification_adapter_layout, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.message.setText(notifications.get(i).getMessage());
        viewHolder.title.setText(notifications.get(i).getTitle());

        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventMapActivity.class);
                intent.putExtra("id", notifications.get(i).getEventId());
                intent.putExtra("event_name", notifications.get(i).getEventId());
                context.startActivity(intent);
            }
        });

        return convertView;

    }

    public class ViewHolder {
        Typeface semibold, bold;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.card)
        CardView card;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

            semibold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
            bold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);

            title.setTypeface(bold);
            message.setTypeface(semibold);
        }
    }
}
