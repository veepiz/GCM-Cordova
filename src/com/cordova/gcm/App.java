package com.cordova.gcm;

import android.os.Bundle;
import com.google.android.gcm.GCMRegistrar;
import org.apache.cordova.*;
import android.util.Log;

public class App extends DroidGap {

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      super.init();
      this.appView.getSettings().setPluginsEnabled(true);

      super.loadUrl("file:///android_asset/www/index.html");

      GCMRegistrar.checkDevice(this);
      GCMRegistrar.checkManifest(this);
      final String regId = GCMRegistrar.getRegistrationId(this);
      if (regId.equals("")) {
        Log.v(TAG, "registering!");
        GCMRegistrar.register(this, "your_sender_id");
      } else {
        Log.v(TAG, "Already registered: " + regId);
      }
    }
}

