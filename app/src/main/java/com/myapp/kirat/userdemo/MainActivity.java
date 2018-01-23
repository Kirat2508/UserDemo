package com.myapp.kirat.userdemo;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    EditText ePhone;
    EditText eMessage;
    Button button;
    PackageManager packageManager;
    String url1 = "http://demo0661390.mockable.io/whatsapp";
    String phone;
    String message;
    String eephone;
    JSONArray jsonArray;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ePhone = (EditText) findViewById(R.id.ePhone);
        eMessage = (EditText) findViewById(R.id.eMessage);
        button = (Button) findViewById(R.id.button);



        //    eephone = Integer.toString(phone);
        if (!isAccessibilitySettingsOn(getApplicationContext())) {

            startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 1);
            Toast.makeText(this, "Please On the Accessibility for further usage", Toast.LENGTH_SHORT).show();
        }
        else {
//            if(isMyServiceRunning(JsonService.class)) {
            startService(new Intent(this, JsonService.class));
            Toast.makeText(this, "service started"+(i++), Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(this, "service stopped", Toast.LENGTH_SHORT).show();
//                stopService(new Intent(this, JsonService.class));
//            }
        }







        // onBackPressed();




//        Intent sendIntent = new Intent("android.intent.action.MAIN");
//        sendIntent.setAction(Intent.ACTION_VIEW);
//        sendIntent.setPackage("com.whatsapp");
//        //  sendIntent.setClassName("com.whatsapp.Main",null);
//        String url = "https://api.whatsapp.com/send?phone=" + "91" + eephone + "&text=" + message;
//        sendIntent.setData(Uri.parse(url));
//        startActivity(sendIntent);


//
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){



//            String phone = ePhone.getText().toString();
//            String Message = eMessage.getText().toString();
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setAction(Intent.ACTION_VIEW);
                sendIntent.setPackage("com.whatsapp");
                //  sendIntent.setClassName("com.whatsapp.Main",null);
                String url = "https://api.whatsapp.com/send?phone=" + "91" + phone + "&text=" + message;
                sendIntent.setData(Uri.parse(url));
                startActivity(sendIntent);
//

            }
        });}
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }




    @Override
    public void onBackPressed() {

//        Intent sendIntent = new Intent("android.intent.action.MAIN");
//        sendIntent.setAction(Intent.ACTION_VIEW);
//        sendIntent.setPackage("com.whatsapp");
//        //  sendIntent.setClassName("com.whatsapp.Main",null);
//        String url = "https://api.whatsapp.com/send?phone=" + "91" + phone + "&text=" + message;
//        sendIntent.setData(Uri.parse(url));
//        startActivity(sendIntent);
        // finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + MyAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            // Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            //   Log.e(TAG, "Error finding setting, default accessibility to not found: "
            //        + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            //  Log.v(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    //      Log.v(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        //        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            //    Log.v(TAG, "***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }
    public void phoneData(String phone , String message) {

        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.setAction(Intent.ACTION_VIEW);
        sendIntent.setPackage("com.whatsapp");
        //  sendIntent.setClassName("com.whatsapp.Main",null);
        String url = "https://api.whatsapp.com/send?phone=" + "91" + phone + "&text=" + message;
        sendIntent.setData(Uri.parse(url));
        startActivity(sendIntent);

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

//    public  class MyAsyncTasks extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//
//            // implement API in background and store the response in current variable
//            String json  ="";
//            HttpURLConnection connection = null;
//            try{
//                URL url = new URL(urls[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                InputStream in = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(in);
//                int data = inputStreamReader.read();
//                while (data != -1) {
//                    char current = (char) data;
//                    json+= current;
//                    data = inputStreamReader.read();
//                }
//                return json;
//            }catch (Exception e){
//                Toast.makeText(getApplicationContext(),"you are not connected to internet",Toast.LENGTH_LONG).show();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            //   super.onPostExecute(s);
//
//            try {
//                 jsonArray = new JSONArray(s);
//                for (int i=0;i<jsonArray.length();i++) {
//                  JSONObject  object = jsonArray.getJSONObject(i);
//                    String phone = object.getString("number");
//                    String message = object.getString("message");
//                    Toast.makeText(MainActivity.this, "All the data has been retrieved", Toast.LENGTH_SHORT).show();
//                    phoneData(phone,message);
//
//
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//    }
}



