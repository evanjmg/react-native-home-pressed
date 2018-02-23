
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
  @ReactMethod
  public void onHomePressed(Callback callback) {
    callback.invoke()
  }
  @Override
  public String getName() {
    return "RNHomePressed";
  }
}