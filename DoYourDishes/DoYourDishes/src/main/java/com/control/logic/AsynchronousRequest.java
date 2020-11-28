package com.control.logic;

import android.os.AsyncTask;

import com.view.gui.MainActivity;

public class AsynchronousRequest extends AsyncTask<Void, Void, Void> {

    private MainActivity mainActivity;

    private String data;

    public AsynchronousRequest(MainActivity a,String passedString ) {
        this.mainActivity = a;
        data = passedString;
    }

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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here
        mainActivity.changeTextFromAsync(data);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // You can track you progress update here
    }

}