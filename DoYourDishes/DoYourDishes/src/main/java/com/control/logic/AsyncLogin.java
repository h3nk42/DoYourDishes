package com.control.logic;

import android.content.Intent;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.control.networkHttp.HttpRequest;
import com.view.gui.HomeActivity;
import com.view.gui.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AsyncLogin extends AsyncTask<Void, Void, Void> {

    private static final String TAG="AsyncLogin";
    private TextView textView;
    private String responseText;
    private String userName;
    private String password;
    final HttpRequest httpEngine = new HttpRequest();
    private MainActivity mainActivity;
    private Boolean token = false;
    private Boolean errorMessage = false;


    public AsyncLogin(TextView passedTextView, String _userName, String _password, MainActivity _mainActivity) {
        textView = passedTextView;
        responseText = "null";
        userName = _userName;
        password = _password;
        mainActivity = _mainActivity;
    }


    @NotNull
    private RequestBody makeRequestBody() {
        return new FormBody.Builder()
                .add("userName", userName)
                .add("password", password)
                .build();
    }

    @Override
    public Void doInBackground(Void... params) {
        // TODO catch expection in background ??
        // https://stackoverflow.com/questions/26161538/throw-an-exception-in-doinbackground-and-catch-in-onpostexecute#:~:text=You%20cannot%20throw%20exceptions%20across,handle%20it%20in%20onPostExecute()%20.&text=No%2C%20you%20can't%20throw%20exception%20in%20the%20background%20thread.
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        String mem;
        JSONObject response = null;

        RequestBody requestBody = makeRequestBody();
        try {
            response = httpEngine.POST("http://10.0.2.2:3001/api/auth/login", requestBody, "");
            if(response.has("token")) {
                responseText = response.getString("token");
                token = true;
            }
            else{
                responseText = response.getString("customMessage");
                errorMessage = true;
            }
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

        if(errorMessage) {
            textView.setText(responseText);
        }



        if(token) {
        Intent intent = new Intent(mainActivity, HomeActivity.class);
        intent.putExtra("TOKEN", responseText);
        mainActivity.startActivity(intent);
        }

        /* AsyncWhoAmI request = new AsyncWhoAmI(textView, responseText);
        request.execute();*/

    }
}