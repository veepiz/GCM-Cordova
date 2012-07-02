# Cordova GCM Plugin for Android

---

## About

This plugin is for use with [Cordova](http://incubator.apache.org/cordova/) and in an Android project to enable push notifications via [Google's GCM (Google Cloud Messaging) service](http://developer.android.com/guide/google/gcm/index.html). Previously, push notifications on Android used C2DM, but that [has since been deprecated](http://developer.android.com/guide/google/gcm/c2dm.html).

## Installation

1. add the com.cordova.gcm, com.google.android.gcm, and com.plugin.GCM packages to your project.

2. Modify your AndroidManifest.xml to include the following lines to your manifest tag, replacing your_app_package with your app's package path:


    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />

    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
    <permission android:name="your_app_package.permission.C2D_MESSAGE" android:protectionLevel="signature" />
    <uses-permission android:name="your_app_package.permission.C2D_MESSAGE" />


3. Modify your AndroidManifest.xml to include the following lines to your application tag, replacing your_app_package with your app's package path:


    <receiver android:name="com.google.android.gcm.GCMBroadcastReceiver" android:permission="com.google.android.c2dm.permission.SEND" >
      <intent-filter>
        <action android:name="com.google.android.c2dm.intent.RECEIVE" />
        <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
        <category android:name="your_app_package" />
      </intent-filter>
    </receiver>

    <service android:name="your_app_package.GCMIntentService" />



