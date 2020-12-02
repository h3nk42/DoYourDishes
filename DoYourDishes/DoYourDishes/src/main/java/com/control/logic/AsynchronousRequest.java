package com.control.logic;

import android.os.AsyncTask;
import android.widget.TextView;

import com.view.R;
import com.view.gui.MainActivity;

public class AsynchronousRequest extends AsyncTask<Void, Void, Void>{

    private MainActivity mainActivity;

    private TextView textView;

    private String newText;

    private Crud crud = new Crud();

    public AsynchronousRequest(MainActivity a, TextView passedTextView) {
        this.mainActivity = a;
        textView = passedTextView;
        newText = "null";
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
            newText = crud.fetchData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //After completing execution of given task , control will return here.
        //Hence if you want to populate UI elements with fetched data, do it here

        //mainActivity.changeTextFromAsync(data);
        textView.setText(newText);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        // You can track you progress update here
    }

}