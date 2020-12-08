package com.control.logic;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.control.networkHttp.HttpRequestFactory;
import com.view.R;
import com.view.gui.MainActivity;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AsyncLogin extends AsyncTask<Void, Void, Void>{

    private static final String TAG="AsyncLogin";

    private TextView textView;
    private String responseText;
    private String userName;
    private String password;
    final HttpRequestFactory httpEngine = new HttpRequestFactory();


    public AsyncLogin(TextView passedTextView, String _userName, String _password) {
        textView = passedTextView;
        responseText = "null";
        userName = _userName;
        password = _password;
    }

    @Override
    protected void onPreExecute() {
        //Here you can show progress bar or something on the similar lines.
        //Since you are in a UI thread here.
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        String mem;
        JSONObject response = null;

        RequestBody requestBody = new FormBody.Builder()
                .add("userName", userName)
                .add("password", password)
                .build();
        try {
            response = httpEngine.POST("http://10.0.2.2:3001/api/auth/login", requestBody, "");
            responseText = response.getString("token");

        }
             catch(Exception e){
                e.printStackTrace();
                responseText = e.toString();
                Log.d(TAG, "AsyncLogin: " + e.toString());
            }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here

        //mainActivity.changeTextFromAsync(data);
        textView.setText(responseText);
        Log.d(TAG, "onPostExecute: test");
        AsyncWhoAmI request = new AsyncWhoAmI(textView, responseText);
        request.execute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // You can track you progress update here
    }

}