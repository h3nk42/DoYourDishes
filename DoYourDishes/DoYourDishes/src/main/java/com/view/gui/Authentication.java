package com.view.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.view.R;


import org.json.JSONException;
import org.json.JSONObject;


public class Authentication extends AppCompatActivity {

    private static final String TAG = "Authentication";
    RequestQueue queue;

    private void fetchData() {
        final TextView textView = (TextView) findViewById(R.id.showFetchData);
        textView.setText("");
        //final Object[] data = new Object[1];
        final JSONObject[] data = new JSONObject[1];

        queue = Volley.newRequestQueue(this);
        String url = "https://shareyourplant.herokuapp.com/api/getData";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        JSONObject jo = null;
                        try {
                            jo = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        data[0] = jo;
                        try {
                            for (Integer i = 0; i < data[0].getJSONArray("data").length(); i++) {
                                String message = data[0].getJSONArray("data").getJSONObject(i).get("message").toString();
                                Log.d(TAG, "fetchData response:" + message);
                                textView.setText(textView.getText() + "\n" + message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
                Log.d(TAG, "fetchData error:" + error);
            }
        });
        queue.add(stringRequest);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        fetchData();


        final Button fetchDataButton = (Button) findViewById(R.id.fetchDataButton);

        fetchDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });

        Log.d(TAG, "onCreate: out");

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: in");
        super.onStop();
        Log.d(TAG, "onStop: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: in");
        super.onPause();
        Log.d(TAG, "onPause: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: in");
        super.onRestart();
        Log.d(TAG, "onRestart: out");
    }
}