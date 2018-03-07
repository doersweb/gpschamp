package track.gpschamp.com.gpschamp.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.testfairy.TestFairy;

import butterknife.BindView;
import track.gpschamp.com.gpschamp.R;
import track.gpschamp.com.gpschamp.utils.Constants;
import track.gpschamp.com.gpschamp.utils.PrefsUtil;

public class SplashActivity extends AppCompatActivity {


    PrefsUtil prefsUtil;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TestFairy.begin(this, "e6423438be7fff48f6ff6b1a8adf02cf08d6ce59");

        prefsUtil = new PrefsUtil(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(prefsUtil.getBoolean(Constants.IS_LOGGED_IN)) {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashActivity.this, SingupActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);


    }
}
