package app.test.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MoPubViewActivity extends AppCompatActivity implements MoPubInterstitial.InterstitialAdListener {

    private MoPubInterstitial mInterstitial;
    private String INTERSTITIAL_AD_UNIT_ID = "0b51059ac34f499d97f5c5f4270cf2d2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_pub_view);

        SdkConfiguration.Builder configBuilder = new SdkConfiguration.Builder(INTERSTITIAL_AD_UNIT_ID);
        if (BuildConfig.DEBUG) {
            configBuilder.withLogLevel(MoPubLog.LogLevel.DEBUG);
        } else {
            configBuilder.withLogLevel(MoPubLog.LogLevel.INFO);
        }

        MoPub.initializeSdk(this, configBuilder.build(), initSdkListener());
    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                mInterstitial = new MoPubInterstitial(MoPubViewActivity.this, INTERSTITIAL_AD_UNIT_ID);
                mInterstitial.setInterstitialAdListener(MoPubViewActivity.this);
                mInterstitial.setKeywords("");
                mInterstitial.setUserDataKeywords("");
                mInterstitial.load();
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mInterstitial != null) {
            mInterstitial.destroy();
            mInterstitial = null;
        }
    }

    // InterstitialAdListener methods
    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        // The interstitial has been cached and is ready to be shown.
        mInterstitial.show();
        Toast.makeText(this, "Interstitial is loaded. OK", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        // The interstitial has failed to load. Inspect errorCode for additional information.
        Toast.makeText(this, "Interstitial is failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        // The interstitial has been shown. Pause / save state accordingly.
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {}

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        // The interstitial has being dismissed. Resume / load state accordingly.
    }

    @Override
    public void onBackPressed() {

    }
}
