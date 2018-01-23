package com.myapp.kirat.userdemo;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonService extends Service {

    String url = "http://demo0661390.mockable.io/whatsapp";
    JSONArray jsonArray;
    JSONObject  object;
    String phone;
    String message;
    int index = 0;

    @Override
    public void onCreate() {
        AsyncTasks asyncTasks = new AsyncTasks();
        asyncTasks.execute(url);




    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Testing", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    public void phoneData() {
        if(index<jsonArray.length() ){


            try {
                object = jsonArray.getJSONObject(index);
                phone = object.getString("number");
                message = object.getString("message");
                Toast.makeText(this, ""+phone, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.setAction(Intent.ACTION_VIEW);
            sendIntent.setPackage("com.whatsapp");
            //  sendIntent.setClassName("com.whatsapp.Main",null);
            String url = "https://api.whatsapp.com/send?phone=" + "91" + phone + "&text=" + message;
            sendIntent.setData(Uri.parse(url));
            startActivity(sendIntent);
            index++;

            try {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  phoneData();
                                              }
                                          },5000
                );

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

            Toast.makeText(getApplicationContext(), "Send All Messages!!", Toast.LENGTH_SHORT).show();

            return;
        }

    }


    public  class AsyncTasks extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {


            String json  ="";
            HttpURLConnection connection = null;
            try{
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char current = (char) data;
                    json+= current;
                    data = inputStreamReader.read();
                }
                return json;
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"you are not connected to internet",Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //   super.onPostExecute(s);

            try {
                jsonArray = new JSONArray(s);
//                for (int i=0;i<jsonArray.length();i++) {
//                    object = jsonArray.getJSONObject(0);
//                    String phone = object.getString("number");
//                    String message = object.getString("message");
//                Toast.makeText(getApplicationContext(), "number : "+phone , Toast.LENGTH_SHORT).show();
                if(jsonArray != null) {
                    Toast.makeText(getApplicationContext(), "jsonArray.length()= " + jsonArray.length(), Toast.LENGTH_SHORT).show();

                    phoneData();

                }else{
                    Toast.makeText(getApplicationContext(), jsonArray.toString(), Toast.LENGTH_LONG).show();
                }

//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}


