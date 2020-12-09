package com.control.logic;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.control.networkHttp.HttpRequestFactory;
import com.view.gui.MainActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AsyncTaskFactory extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "AsyncFactory";

    ActiveState state;

    final HttpRequestFactory httpEngine = new HttpRequestFactory();
    private String responseText = "test";
    private TextView textView;
    private String token;
    private String userName;
    private String password;
    private MainActivity mainActivity;
    private Boolean tokenBool = false;
    private Boolean errorMessage = false;


    //AsyncWhoAmI
    public AsyncTaskFactory(TextView _passedTextView, String _token, ActiveState _state) {
        state = _state;

        switch (state) {
            case WHOAMI:
                this.textView = _passedTextView;
                this.token = _token;
                Log.d(TAG, "AsyncTaskFactory: ");
                break;
        }

    }
    // state = ActiveState.WHOAMI;


    //AsyncLogin
    public AsyncTaskFactory(TextView passedTextView, String _userName, String _password, MainActivity _mainActivity, ActiveState _state) {
        state = _state;

        switch (state) {
            case LOGIN:
                textView = passedTextView;
                responseText = "null";
                userName = _userName;
                password = _password;
                mainActivity = _mainActivity;
                break;
        }
        // weiter states

        // state = ActiveState.LOGIN;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          makeRequestBody()                                 //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @NotNull
    private RequestBody makeRequestBody() {

        RequestBody requestBody = null;

        switch (state) {
            case LOGIN:
                requestBody = new FormBody.Builder()
                        .add("userName", userName)
                        .add("password", password)
                        .build();
                break;

            case WHOAMI:
                requestBody = new FormBody.Builder()
                        // .add("userName", userName)
                        //.add("password", "test")
                        .build();
                break;

        }
        return requestBody;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         choose what to do doInBackround()                  //
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Void doInBackground(Void... params) {
        switch (state) {
            case LOGIN:
                doWhenLogin();


            case WHOAMI:
                doWhenWhoAmI();

        }
        return null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          doInBackround()                                   //
    ///////////////////////////////////////////////////////////////////////////////////////////////


    public Void doWhenLogin(Void... voids) {
        // TODO catch expection in background ??
        // https://stackoverflow.com/questions/26161538/throw-an-exception-in-doinbackground-and-catch-in-onpostexecute#:~:text=You%20cannot%20throw%20exceptions%20across,handle%20it%20in%20onPostExecute()%20.&text=No%2C%20you%20can't%20throw%20exception%20in%20the%20background%20thread.
        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody();

        try {
            response = httpEngine.POST("http://10.0.2.2:3001/api/auth/login", requestBody, "");
            if (response.has("token")) {
                responseText = response.getString("token");
                tokenBool = true;
            } else {
                responseText = response.getString("customMessage");
                errorMessage = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseText = e.toString();
            Log.d(TAG, "AsyncLogin: " + e.toString());
        }
        return null;
    }


    public Void doWhenWhoAmI(Void... voids) {

        //Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        JSONObject response = null;
        RequestBody requestBody = makeRequestBody();

        try {
            response = httpEngine.GET("http://10.0.2.2:3001/api/auth/whoAmI", requestBody, this.token);
            responseText = response.getJSONObject("data").getString("userName");
        } catch (Exception e) {
            e.printStackTrace();
            responseText = e.toString();
            Log.d(TAG, "AsyncWhoAmI: " + e.toString());
        }
        return null;
    }


}
