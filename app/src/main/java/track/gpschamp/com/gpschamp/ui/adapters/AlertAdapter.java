package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.images.ImagesObject;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class AlertAdapter extends BaseAdapter {

    Context context;
    List<ImagesObject> objects;
    public AlertAdapter(Context context, List<ImagesObject> objects){
        this.objects = objects;
        this.context = context;
    }
    @Override
    public int getCount() {
        return objects.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_adapter_layout, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.timeTv.setText(objects.get(i).getName());

        return convertView;
    }

    public class ViewHolder{

        @BindView(R.id.item)
        TextView timeTv;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
            Typeface regular = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
            timeTv.setTypeface(regular);
        }
    }
}
