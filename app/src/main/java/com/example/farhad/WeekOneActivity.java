package com.example.farhad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WeekOneActivity extends AppCompatActivity {
    private TextView helloWorldText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_one);

        helloWorldText = (TextView) findViewById(R.id.textView4);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void changeImage(View view) {
        if (helloWorldText.getText() == "") {
            helloWorldText.setText("Hello World!!");
            imageView.setImageResource(R.drawable.hello_world_2);
        }
        else {
            helloWorldText.setText("");
            imageView.setImageResource(R.drawable.hello_world_1);
        }
    }
}