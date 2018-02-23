
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import android.util.Log;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.app.Activity;

public class RNHomePressedModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

  private final ReactApplicationContext reactContext;
  private static final String PRESSED_ERROR = "PRESSED_ERROR";
  public RNHomePressedModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addLifecycleEventListener(this);
    this.reactContext = reactContext;
  }
  public static final String ON_HOME_BUTTON_PRESSED = "ON_HOME_BUTTON_PRESSED";
  public static final String ON_RESUME = "ON_RESUME";
  public static final String ON_PAUSE = "ON_PAUSE";

  public void onUserLeaveHint() {
    try {
     this.reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(ON_HOME_BUTTON_PRESSED, null);
    } catch (Exception e) {
       Log.e(PRESSED_ERROR, "sendEvent called before bundle loaded");
    }
  }
  @Override
  public void onHostResume() {
    this.reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(ON_PAUSE, null);
  }

  @Override
  public void onHostPause() {
    this.reactContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      .emit(ON_RESUME, null);
  }
  @Override
  public void onHostDestroy() {

  }
  @Override
  public String getName() {
    return "RNHomePressed";
  }
}