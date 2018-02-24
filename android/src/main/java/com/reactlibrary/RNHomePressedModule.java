
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ActivityEventListener;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class HomeWatcher {

    static final String TAG = "hg";
    private Context mContext;
    private IntentFilter mFilter;
    private OnHomePressedListener mListener;
    private InnerRecevier mRecevier;

    public HomeWatcher(Context context) {
        mContext = context;
        mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    public void setOnHomePressedListener(OnHomePressedListener listener) {
        mListener = listener;
        mRecevier = new InnerRecevier();
    }

    public void startWatch() {
        if (mRecevier != null) {
            mContext.registerReceiver(mRecevier, mFilter);
        }
    }

    public void stopWatch() {
        if (mRecevier != null) {
            mContext.unregisterReceiver(mRecevier);
        }
    }

    class InnerRecevier extends BroadcastReceiver {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions";
        final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null) {
                    Log.e(TAG, "action:" + action + ",reason:" + reason);
                    if (mListener != null) {
                        if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                            mListener.onHomePressed();
                        } else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                            mListener.onHomeLongPressed();
                        }
                    }
                }
            }
        }
    }
}
public interface OnHomePressedListener {
    public void onHomePressed();

    public void onHomeLongPressed();
}

public class RNHomePressedModule extends ReactContextBaseJavaModule {
  private final HomeWatcher mHomeWatcher;
  private final ReactApplicationContext reactContext;
  private static final String PRESSED_ERROR = "PRESSED_ERROR";
  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.mHomeWatcher = new HomeWatcher(reactContext);
    this.reactContext = reactContext;

    this.mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
        @Override
        public void onHomePressed() {
          RNHomePressedModule.sendEvent(reactContext, RNHomePressedModule.ON_HOME_BUTTON_PRESSED);
        }
        @Override
        public void onHomeLongPressed() {
          RNHomePressedModule.sendEvent(reactContext, RNHomePressedModule.ON_HOME_BUTTON_LONG_PRESSED);
        }
    });
    this.mHomeWatcher.startWatch();
  }
  public static final String ON_HOME_BUTTON_PRESSED = "ON_HOME_BUTTON_PRESSED";
  public static final String ON_HOME_BUTTON_LONG_PRESSED = "ON_HOME_BUTTON_LONG_PRESSED";
  public static final String ON_RESUME = "ON_RESUME";
  public static final String ON_PAUSE = "ON_PAUSE";

  public static void sendEvent(ReactApplicationContext reactContext, String eventName) {
    try {
     reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(eventName, null);
    } catch (Exception e) {
       Log.e(eventName, "sendEvent called before bundle loaded");
    }
  }
  @Override
  public String getName() {
    return "RNHomePressed";
  }
}