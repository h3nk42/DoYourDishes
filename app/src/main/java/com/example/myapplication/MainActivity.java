package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button button;
    private TextView textView;
    private int numTimesClicked = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.viewTitle);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("the button got tapped 0 times\n");

        View.OnClickListener ourOnClickListner = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                numTimesClicked = numTimesClicked + 1;
                String result = "\nthe button got tapped " + numTimesClicked + " time" ;
                if (numTimesClicked != 1 ) { result += "s\n"; } else { result += "\n"; }
                textView.setText("");
                textView.append(result);
            }
        };
        button.setOnClickListener(ourOnClickListner);

    }
}