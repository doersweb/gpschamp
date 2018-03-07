package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 25/02/18.
 */

public class PlainAdapter extends BaseAdapter {

    Context context; String[] data;
    public PlainAdapter(String[] data,Context context){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.length;
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

        viewHolder.timeTv.setText(data[i]);

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
