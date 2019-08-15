package app.test.com;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnOk;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk = (Button) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceIntent != null){
                    finish();
                }
            }
        });

        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(getPackageName(), getPackageName() + ".AliasSplash"); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        Log.w("lockmonitor", "Starting service...");
        serviceIntent = new Intent(this, LockMonitor.class);
        serviceIntent.setAction(LockMonitor.ACTION_CHECK_LOCK);
        startService(serviceIntent);

//        PackageManager p = getPackageManager();
//        ComponentName componentName = new ComponentName(this, com.apps.MainActivity.class);
//        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}
