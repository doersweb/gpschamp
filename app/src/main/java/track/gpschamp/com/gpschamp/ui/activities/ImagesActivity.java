package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.model.images.ObjectsImagesResponse;
import track.gpschamp.com.gpschamp.ui.adapters.ImagesAdapter;
import track.gpschamp.com.gpschamp.ui.fragments.images.ImagesFragment;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class ImagesActivity extends AppCompatActivity {


    @BindView(R.id.gridview)
    GridView gridView;

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.heading)
    TextView heading;

    @BindView(R.id.back_image)
    ImageView backImage;

    ObjectsImagesResponse objectsImagesResponse;
    ImagesAdapter imagesAdapter;

    @BindView(R.id.notification_count)
    TextView notificationCount;

    Typeface bold;
    PrefsUtil prefsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


        objectsImagesResponse = ImagesFragment.objectsImagesResponse;
        ButterKnife.bind(this);

        Typeface semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        heading.setTypeface(bold);
        heading.setText(getIntent().getStringExtra("name"));

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        imagesAdapter = new ImagesAdapter(this, objectsImagesResponse.getData().getDataList());
        gridView.setAdapter(imagesAdapter);

        prefsUtil = new PrefsUtil(this);

        notificationCount.setTypeface(semibold);
        notificationCount.setText(prefsUtil.getInt(Constants.NOTIFCIATION_COUNT)+"");


    }

    @OnClick(R.id.notification_rel)
    void onNotificaitonClick(){
        Intent intent = new Intent(ImagesActivity.this, NotifcationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
