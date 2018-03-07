package track.gpschamp.com.gpschamp.ui.activities;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.core.forgot.ForgotContractor;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.utils.Constants;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotContractor.View , SurfaceHolder.Callback{


    @BindView(R.id.btn_sign_up)
    RelativeLayout signUp;

    @BindView(R.id.heading_tv)
    TextView headingTv;

    @BindView(R.id.detail_tv)
    TextView subHeading;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.help_text)
    TextView helpTv;

    @BindView(R.id.sign_up_text)
    TextView signUpTv;

    @BindView(R.id.tag_line)
    TextView taglineTv;

    @BindView(R.id.surface)
    SurfaceView surface;

    Typeface regular, semibold, bold;
    MediaPlayer mp =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);

        regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);


        taglineTv.setTypeface(semibold);
        headingTv.setTypeface(semibold);
        subHeading.setTypeface(regular);
        etEmail.setTypeface(regular);
        etUsername.setTypeface(regular);
        etPhone.setTypeface(regular);
        btnCancel.setTypeface(bold);
        btnSubmit.setTypeface(bold);

        signUpTv.setTypeface(semibold);
        helpTv.setTypeface(semibold);

        mp = new MediaPlayer();
        surface.getHolder().addCallback(this);
    }

    @OnClick(R.id.btn_sign_up)
    public void onSignup() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_cancel)
    public void onCancelClick(){
        Intent intent = new Intent(this, SingupActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onForgotPasswordResponse(GeneralResponse generalResponse) {
        if (generalResponse != null) {
            Toast.makeText(this, generalResponse.getResult(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please check you internet and try again!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.gps_champ_screen_video);

        try {
            mp.setDataSource(ForgotPasswordActivity.this, video);
            mp.prepare();
            mp.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Get the dimensions of the video
        int videoWidth = mp.getVideoWidth();
        int videoHeight = mp.getVideoHeight();

        //Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //Get the SurfaceView layout parameters
        android.view.ViewGroup.LayoutParams lp = surface.getLayoutParams();

        //Set the width of the SurfaceView to the width of the screen
        lp.width = screenWidth;

        //Set the height of the SurfaceView to match the aspect ratio of the video
        //be sure to cast these as floats otherwise the calculation will likely be 0
        lp.height = (int) (((float)videoHeight / (float)videoWidth) * (float)screenWidth);

        //Commit the layout parameters
        surface.setLayoutParams(lp);

        //Start video
        mp.setDisplay(surfaceHolder);
        mp.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
