package com.citytransport.android7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button1 = (Button) findViewById(R.id.button1); //Login
        Button button2 = (Button) findViewById(R.id.button2); //FindRoute
        Button button3 = (Button) findViewById(R.id.button3); //Nearby
        Button button4 = (Button) findViewById(R.id.button4); //Bus Routes
        Button button5 = (Button) findViewById(R.id.button5); //AboutUs
        Button button6 = (Button) findViewById(R.id.button6); //Help


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
                openLogin();
                break;
            case R.id.button2:
                Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                openFindRoute();
                break;
            case R.id.button3:
                Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
                openNearby();
                break;
            case R.id.button4:
                Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show();
                openBusRoutes();
                break;
            case R.id.button5:
                Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                openAboutUs();
                break;
            case R.id.button6:
                Toast.makeText(this, "Button 6 clicked", Toast.LENGTH_SHORT).show();
                openHelp();
                break;
        }
    }



    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openFindRoute() {
        Intent intent = new Intent(this, FindRoute.class);
        startActivity(intent);
    }

    public void openNearby() {
        Intent intent = new Intent(this, Nearby.class);
        startActivity(intent);
    }

    public void openBusRoutes() {
        Intent intent = new Intent(this, BusRoutes.class);
        startActivity(intent);
    }

    public void openAboutUs() {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void openHelp() {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

}
