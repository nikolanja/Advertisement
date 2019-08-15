package app.test.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StateReceiver extends BroadcastReceiver {

    public static final String TAG = "LockMonitor-SR";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: redirect intent to LockMonitor");
        final Intent newIntent = new Intent(context, LockMonitor.class);
        newIntent.setAction(LockMonitor.ACTION_CHECK_LOCK);
        newIntent.putExtra(LockMonitor.EXTRA_STATE, intent.getAction());
        context.startService(newIntent);
    }
}