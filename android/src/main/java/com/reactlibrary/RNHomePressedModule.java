
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

public class RNHomePressedModule extends ReactContextBaseJavaModule {
  private final HomeWatcher mHomeWatcher;
  private final ReactApplicationContext reactContext;
  private static final String PRESSED_ERROR = "PRESSED_ERROR";
  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.mHomeWatcher = new HomeWatcher(reactContext);
    this.reactContext = reactContext;
    final ReactApplicationContext rcontext
    rcontext = reactContext
    this.mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
        @Override
        public void onHomePressed() {
          RNHomePressedModule.sendEvent(rcontext, RNHomePressedModule.ON_HOME_BUTTON_PRESSED);
        }
        @Override
        public void onHomeLongPressed() {
          RNHomePressedModule.sendEvent(rcontext, RNHomePressedModule.ON_HOME_BUTTON_LONG_PRESSED);
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