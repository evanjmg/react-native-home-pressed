
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNHomePressedModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private static final String PRESSED_ERROR = "PRESSED_ERROR";
  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }
  public static final String ON_HOME_BUTTON_PRESSED = "ON_HOME_BUTTON_PRESSED";

  public void onHomePressed() {
    try {
      this.reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(ON_HOME_BUTTON_PRESSED);
    } catch (Exception e) {
       Log.e(PRESSED_ERROR, "sendEvent called before bundle loaded");
    }
  }
  @Override
  public String getName() {
    return "RNHomePressed";
  }
}