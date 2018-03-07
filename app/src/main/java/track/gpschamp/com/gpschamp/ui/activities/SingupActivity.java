package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.core.login.LoginContractor;
import track.gpschamp.com.gpschamp.core.login.LoginPresenter;
import track.gpschamp.com.gpschamp.model.login.LoginResponse;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class SingupActivity extends AppCompatActivity implements LoginContractor.View, SurfaceHolder.Callback {

    @BindView(R.id.et_username)
    EditText etUserName;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button login;

    @BindView(R.id.help_text)
    TextView helpTv;

    @BindView(R.id.sign_up_text)
    TextView signUpTv;

    @BindView(R.id.progress_background)
    RelativeLayout progressBackground;

    LoginPresenter loginPresenter;
    PrefsUtil prefsUtil;

    @BindView(R.id.tv_forgot)
    TextView forgotTv;

    @BindView(R.id.surface)
    SurfaceView surface;

    @BindView(R.id.tag_line)
    TextView tagLineTv;

    @BindView(R.id.remember_checkbox)
    CheckBox rememberCheckbox;

    Typeface regular, semibold, bold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        ButterKnife.bind(this);

        regular = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_REGULAR);
        semibold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_SEMI_BOLD);
        bold = Typeface.createFromAsset(getAssets(), Constants.PATH_CUSTOM_FONT_BOLD);


        rememberCheckbox.setTypeface(semibold);
        tagLineTv.setTypeface(semibold);
        forgotTv.setTypeface(semibold);
        login.setTypeface(bold);
        etUserName.setTypeface(semibold);
        etPassword.setTypeface(semibold);
        signUpTv.setTypeface(semibold);
        helpTv.setTypeface(semibold);

        prefsUtil = new PrefsUtil(this);

        if (prefsUtil.getString(Constants.PASSWORD) != null)
            if (!prefsUtil.getString(Constants.PASSWORD).equals("")) {
                etPassword.setText(prefsUtil.getString(Constants.PASSWORD));
                etUserName.setText(prefsUtil.getString(Constants.USERNAME));
                rememberCheckbox.setChecked(true);
            }

        loginPresenter = new LoginPresenter(this);

        mp = new MediaPlayer();
        surface.getHolder().addCallback(this);

    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        if (etUserName.getText().toString().length() > 0) {
            if (etPassword.getText().toString().length() > 0) {

                progressBackground.setVisibility(View.VISIBLE);
                loginPresenter.loginUser(etUserName.getText().toString(), etPassword.getText().toString(), prefsUtil.getString(Constants.FCM_TOKEN));
            } else {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.tv_forgot)
    public void onForgotClick() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_up)
    public void onRegisterClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mp != null) {
            surface.getHolder().addCallback(this);
        } else {
            mp = new MediaPlayer();
            surface.getHolder().addCallback(this);
        }
    }

    @Override
    public void onLoginResponse(LoginResponse loginResponse) {
        progressBackground.setVisibility(View.GONE);
        if (loginResponse != null) {
            if (loginResponse.isStatus()) {
                if (rememberCheckbox.isChecked()) {
                    prefsUtil.saveString(Constants.PASSWORD, etPassword.getText().toString());
                    prefsUtil.saveString(Constants.USERNAME, etUserName.getText().toString());
                } else {
                    prefsUtil.saveString(Constants.PASSWORD, "");
                    prefsUtil.saveString(Constants.USERNAME, "");
                }
                prefsUtil.saveString(Constants.API_TOKEN, loginResponse.getTokenResponse().getUserToken());
                prefsUtil.saveBoolean(Constants.IS_LOGGED_IN, true);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Could not complete request, please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private MediaPlayer mp = null;

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.gps_champ_screen_video);

        try {
            mp.setDataSource(SingupActivity.this, video);
            mp.prepare();
            mp.setLooping(true);
        } catch (Exception e) {
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
        lp.height = (int) (((float) videoHeight / (float) videoWidth) * (float) screenWidth);

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

    @Override
    protected void onStop() {
        try{
            mp.stop();
        }catch (Exception e){
            e.printStackTrace();
        }

        super.onStop();

    }
}
