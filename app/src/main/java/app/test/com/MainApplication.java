package app.test.com;

import android.app.Application;
import android.util.Log;

import com.flurry.android.FlurryAgent;

public class MainApplication extends Application {

    private String FLURRY_API_KEY = "M6PTWPN9669XR4NF4PGG";

    @Override
    public void onCreate() {
        super.onCreate();

        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .withCaptureUncaughtExceptions(true)
                .withContinueSessionMillis(10000)
                .withLogLevel(Log.VERBOSE)
                .build(this, FLURRY_API_KEY);
    }
}
