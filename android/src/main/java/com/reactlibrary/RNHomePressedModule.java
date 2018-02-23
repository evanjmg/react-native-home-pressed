
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNHomePressedModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public static final String ON_HOME_BUTTON_PRESSED = "ON_HOME_BUTTON_PRESSED";

  public void onHomePressed(ReactContext reactContext,
                       @Nullable WritableMap params) {
    try {
      reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(ON_HOME_BUTTON_PRESSED, params);
    } catch (Exception e) {
       Log.e("sendEvent called before bundle loaded");
    }
  }
  @Override
  public String getName() {
    return "RNHomePressed";
  }
}