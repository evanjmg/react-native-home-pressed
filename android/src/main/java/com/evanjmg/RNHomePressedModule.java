
package com.evanjmg;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNHomePressedModule extends ReactContextBaseJavaModule {
  private final HomeWatcher mHomeWatcher;
  private final ReactApplicationContext reactContext;
  private static final String PRESSED_ERROR = "PRESSED_ERROR";
  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.mHomeWatcher = new HomeWatcher(reactContext);
    this.reactContext = reactContext;
    final ReactApplicationContext rcontext;
    rcontext = reactContext;
    this.mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
        @Override
        public void onHomePressed() {
          RNHomePressedModule.sendEvent(rcontext, RNHomePressedModule.ON_HOME_BUTTON_PRESSED);
        }
        @Override
        public void onRecentAppPressed() {
          RNHomePressedModule.sendEvent(rcontext, RNHomePressedModule.ON_RECENT_APP_BUTTON_PRESSED);
        }
    });
    this.mHomeWatcher.startWatch();
  }
  public static final String ON_HOME_BUTTON_PRESSED = "ON_HOME_BUTTON_PRESSED";
  public static final String ON_RECENT_APP_BUTTON_PRESSED = "ON_RECENT_APP_BUTTON_PRESSED";

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