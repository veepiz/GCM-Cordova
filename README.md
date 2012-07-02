# Cordova GCM Plugin for Android

---

## About

This plugin is for use with [Cordova](http://incubator.apache.org/cordova/) and in an Android project to enable push notifications via [Google's GCM (Google Cloud Messaging) service](http://developer.android.com/guide/google/gcm/index.html). Previously, push notifications on Android used C2DM, but that [has since been deprecated](http://developer.android.com/guide/google/gcm/c2dm.html).

## Installation

1. add the com.cordova.gcm, com.google.android.gcm, and com.plugin.GCM packages to your project.

2. Modify your AndroidManifest.xml to include the following lines to your manifest tag:

  <uses-permission android:name="android.permission.GET_ACCOUNTS" />
  <uses-permission android:name="android.permission.WAKE_LOCK" />

  <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
  <permission android:name="com.deltek.konamobile.permission.C2D_MESSAGE" android:protectionLevel="signature" />
  <uses-permission android:name="com.deltek.konamobile.permission.C2D_MESSAGE" />

and these following lines to your application tag:

  <receiver android:name="com.google.android.gcm.GCMBroadcastReceiver" android:permission="com.google.android.c2dm.permission.SEND" >
    <intent-filter>
      <action android:name="com.google.android.c2dm.intent.RECEIVE" />
      <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
      <category android:name="com.deltek.konamobile" />
    </intent-filter>
  </receiver>

  <service android:name="com.deltek.konamobile.GCMIntentService" />


