package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 06/02/18.
 */

public class DrawerListAdapter extends BaseAdapter {

    Context context;
    String[] items;
    int[] imageData;
    public DrawerListAdapter(Context context, String[] items, int[] imageData){
        this.context = context;
        this.items = items;
        this.imageData = imageData;
    }
    @Override
    public int getCount() {
        return items.length;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_adapter_layout, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(imageData[position]);
        viewHolder.textView.setText(items[position]);

        return convertView;
    }

    public class ViewHolder {

        @BindView(R.id.image)
        ImageView imageView;

        @BindView(R.id.text)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);

            Typeface semibold = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
            textView.setTypeface(semibold);
        }

    }
}
