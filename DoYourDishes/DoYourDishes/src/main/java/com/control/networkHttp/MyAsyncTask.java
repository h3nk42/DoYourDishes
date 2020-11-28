package com.control.networkHttp;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
        //Here you can show progress bar or something on the similar lines.
        //Since you are in a UI thread here.
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        // Here you are in the worker thread and you are not allowed to access UI thread from here
        //Here you can perform network operations or any heavy operations you want.
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // You can track you progress update here
    }

}
