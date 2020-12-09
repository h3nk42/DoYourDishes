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

public class AsyncWhoAmI extends AsyncTask<Void, Void, Void>{

    private static final String TAG="AsyncWho";

    final HttpRequestFactory httpEngine = new HttpRequestFactory();
    private String responseText = "test";
    private TextView textView;
    private String token;

    public AsyncWhoAmI(TextView _passedTextView, String _token) {
        this.textView = _passedTextView;
        this.token = _token;
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
                // .add("userName", userName)
                //.add("password", "test")
                .build();

        try {
            response = httpEngine.GET("http://10.0.2.2:3001/api/auth/whoAmI", requestBody, this.token);
            responseText = response.getJSONObject("data").getString("userName");
        }

        catch(Exception e){
            e.printStackTrace();
            responseText = e.toString();
            Log.d(TAG, "AsyncWhoAmI: " + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here

        //mainActivity.changeTextFromAsync(data);
        textView.setText("you are: " + responseText);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // You can track you progress update here
    }

}