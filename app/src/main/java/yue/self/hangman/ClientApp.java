package yue.self.hangman;

import android.app.Application;

import tatteam.com.app_common.AppCommon;
import tatteam.com.app_common.util.AppSpeaker;

/**
 * Created by the_e_000 on 10/27/2015.
 */
public class ClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        AppCommon.getInstance().destroy();
        super.onTerminate();
    }

}
