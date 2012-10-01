package com.cordova2.gcm;

import com.google.android.gcm.*;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.plugin.GCM.GCMPlugin;


public class GCMIntentService extends GCMBaseIntentService {

  public static final String ME="GCMReceiver";

  public GCMIntentService() {
    super("GCMIntentService");
  }
  private static final String TAG = "GCMIntentService";

  @Override
  public void onRegistered(Context context, String regId) {

    Log.v(ME + ":onRegistered", "Registration ID arrived!");
    Log.v(ME + ":onRegistered", regId);

    JSONObject json;

    try
    {
      json = new JSONObject().put("event", "registered");
      json.put("regid", regId);

      Log.v(ME + ":onRegisterd", json.toString());

      // Send this JSON data to the JavaScript application above EVENT should be set to the msg type
      // In this case this is the registration ID
      GCMPlugin.sendJavascript( json );

    }
    catch( JSONException e)
    {
      // No message to the user is sent, JSON failed
      Log.e(ME + ":onRegisterd", "JSON exception");
    }
  }

  @Override
  public void onUnregistered(Context context, String regId) {
    Log.d(TAG, "onUnregistered - regId: " + regId);
  }

  @Override
  protected void onMessage(Context context, Intent intent) {
    Log.d(TAG, "onMessage - context: " + context);

    // Extract the payload from the message
    Bundle extras = intent.getExtras();
    if (extras != null) {
      try
      {
        Log.v(ME + ":onMessage extras ", extras.getString("message"));

        JSONObject json;
        json = new JSONObject().put("event", "message");

  			// create status-bar notification
				// the message we want to display
				CharSequence tickerText;
				final String NOTIFICATION_BOOT = "notificationBoot";
				try {
					tickerText = extras.getString("message");
				} catch (NullPointerException npe) {
					tickerText = "Dude, no key=msg has been provided. ";
				}

				NotificationManager nm = (NotificationManager) context
						.getApplicationContext().getSystemService(
								Context.NOTIFICATION_SERVICE);
				int icon = R.drawable.ic_launcher;
				Notification notification = new Notification(icon, tickerText,
						System.currentTimeMillis());
                notification.defaults = Notification.DEFAULT_SOUND;
				notification.flags = Notification.DEFAULT_LIGHTS
						| Notification.FLAG_AUTO_CANCEL;

				Intent mainIntent = new Intent(Intent.ACTION_MAIN);
				mainIntent.setClass(context, MainActivity.class);
				mainIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				mainIntent.putExtras(intent.getExtras());
				mainIntent.putExtra(NOTIFICATION_BOOT, true);

				// Intent that is called once user clicks on notification
				PendingIntent contentIntent = PendingIntent.getActivity(
						context.getApplicationContext(), 0, mainIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				notification.setLatestEventInfo(context, "<Veepiz>", tickerText,
						contentIntent);
                
				nm.notify(1, notification);

				// display a toast to the user to inform him on the incoming
				// notification

				LayoutInflater inflater = LayoutInflater.from(context
						.getApplicationContext());
				View layout = inflater.inflate(R.layout.notification, null);
				ImageView image = (ImageView) layout.findViewById(R.id.image);
				image.setImageResource(R.drawable.icon_small);
				TextView text = (TextView) layout.findViewById(R.id.text);
				text.setText(tickerText);
				Toast toast = new Toast(context.getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setView(layout);
				toast.show();
        // My application on my host server sends back to "EXTRAS" variables message and msgcnt
        // Depending on how you build your server app you can specify what variables you want to send
        //
        json.put("message", extras.getString("message"));
        json.put("msgcnt", extras.getString("msgcnt"));

        Log.v(ME + ":onMessage ", json.toString());

        GCMPlugin.sendJavascript( json );
        // Send the MESSAGE to the Javascript application
      }
      catch( JSONException e)
      {
        Log.e(ME + ":onMessage", "JSON exception");
      }        	
    }


  }

  @Override
  public void onError(Context context, String errorId) {
    Log.e(TAG, "onError - errorId: " + errorId);
  }




}
