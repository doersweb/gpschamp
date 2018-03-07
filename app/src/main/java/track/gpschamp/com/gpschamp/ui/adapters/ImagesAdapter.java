package track.gpschamp.com.gpschamp.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.images.ImagesData;
import track.gpschamp.com.gpschamp.utils.Constants;

/**
 * Created by sudhirharit on 27/02/18.
 */

public class ImagesAdapter extends BaseAdapter {

    Context context;
    List<ImagesData> imagesData;
    public ImagesAdapter(Context context, List<ImagesData> imagesData){
        this.context = context;
        this.imagesData = imagesData;
    }


    @Override
    public int getCount() {
        return imagesData.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.image_layout_adapter, viewGroup, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.dte.setText(imagesData.get(i).getDateServer());
        Picasso.with(context).load(Constants.BASE_URL+imagesData.get(i).getImageFile()).into(viewHolder.imageView);

        return convertView;
    }

    public class ViewHolder{

        @BindView(R.id.dte)
        TextView dte;

        @BindView(R.id.image_view)
        ImageView imageView;

        Typeface regular;
        public ViewHolder(View view){
            ButterKnife.bind(this, view);

            regular = Typeface.createFromAsset(context.getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
            dte.setTypeface(regular);
        }
    }


}
