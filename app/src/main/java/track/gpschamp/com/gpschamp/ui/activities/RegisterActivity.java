package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.core.register.RegisterContractor;
import track.gpschamp.com.gpschamp.core.register.RegisterPresenter;
import track.gpschamp.com.gpschamp.model.GeneralResponse;
import track.gpschamp.com.gpschamp.utils.Constants;

public class RegisterActivity extends AppCompatActivity implements RegisterContractor.View, SurfaceHolder.Callback {

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    @BindView(R.id.surface)
    SurfaceView surface;

    @BindView(R.id.help_text)
    TextView helpTv;

    @BindView(R.id.sign_in_text)
    TextView signInTv;

    @BindView(R.id.tag_line)
    TextView tagline;


    MediaPlayer mediaPlayer;

    RegisterPresenter registerPresenter;

    Typeface regular, bold, semibold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);
        semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);

        tagline.setTypeface(semibold);
        btnRegister.setTypeface(bold);
        etEmail.setTypeface(regular);
        etUsername.setTypeface(regular);
        etPhone.setTypeface(regular);

        helpTv.setTypeface(semibold);
        signInTv.setTypeface(semibold);

        registerPresenter = new RegisterPresenter(this);

        mediaPlayer = new MediaPlayer();
        surface.getHolder().addCallback(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(this, SingupActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_register)
    public void onRegister() {
        if (etUsername.getText().toString().trim().length() > 0) {
            if (etEmail.getText().toString().trim().length() > 0) {
                if (etPhone.getText().toString().trim().length() == 10) {
                    progressBackground.setVisibility(View.VISIBLE);
                    registerPresenter.registerUser(etUsername.getText().toString().trim(), etEmail.getText().toString().trim(), etPhone.getText().toString().trim());
                }
            }
        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onLogin() {
        Intent intent = new Intent(this, SingupActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRegisterResponse(GeneralResponse generalResponse) {

        progressBackground.setVisibility(View.GONE);
        if (generalResponse != null) {
            Toast.makeText(this, generalResponse.getResult(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some problem occured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.gps_champ_screen_video);

        try {
            mediaPlayer.setDataSource(this, video);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Get the dimensions of the video
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();

        //Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //Get the SurfaceView layout parameters
        android.view.ViewGroup.LayoutParams lp = surface.getLayoutParams();

        //Set the width of the SurfaceView to the width of the screen
        lp.width = screenWidth;

        //Set the height of the SurfaceView to match the aspect ratio of the video
        //be sure to cast these as floats otherwise the calculation will likely be 0
        lp.height = (int) (((float) videoHeight / (float) videoWidth) * (float) screenWidth);

        //Commit the layout parameters
        surface.setLayoutParams(lp);

        //Start video
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
