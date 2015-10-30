package yue.self.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import tatteam.com.app_common.AppCommon;
import yue.self.hangman.database.DataSource;

/**
 * Created by the_e_000 on 10/27/2015.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        switchToMainActivity();
        initAppCommon();
        initDataBase();
    }

    private void initAppCommon() {
        AppCommon.getInstance().initIfNeeded(getApplicationContext());
        AppCommon.getInstance().increaseLaunchTime();
    }

    private void initDataBase() {
        DataSource.getInstance().init(getApplicationContext());
        DataSource.getInstance().createDatabaseIfNeed();
    }

    private void switchToMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
