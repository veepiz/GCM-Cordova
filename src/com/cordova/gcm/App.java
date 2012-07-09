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

      
    }
}

