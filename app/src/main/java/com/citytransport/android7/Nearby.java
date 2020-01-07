package com.citytransport.android7;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Nearby extends AppCompatActivity  {
    //Variables
    TextView txtLat;
    private Button button_start;
    private Button button_end;
    private TextView txt_lat;
    private TextView txt_lang;
    private BroadcastReceiver broadcastReceiver;
    private String lang;
    private String lat;
    private TextView textView;

    private static String URL_UPDATECODINATES = "http://192.168.10.100/citytransport_android/update.php";

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        //Assign values to variables
        button_start = (Button) findViewById(R.id.btn_start);
        button_end = (Button) findViewById(R.id.btn_end);
        txt_lang = (TextView) findViewById(R.id.txt_lang);
        txt_lat = (TextView) findViewById(R.id.txt_lat);

        BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                lang = intent.getExtras().get("coordinates_long").toString();
                lat = intent.getExtras().get("coordinates_lat").toString();
            }
        };
        registerReceiver(receiver,new IntentFilter("location_update"));
        viewButtons();

    }

    private void viewButtons() {
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GpsService.class);
                startService(i);
                getBaseContext().startService(i);
                UpdateTripStatus();

            }
        });
    }


    private boolean runtime_permission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                enable_buttons();
            } else {
                runtime_permission();
            }
        }
    }


    private void UpdateTripStatus() {
        StringRequest requestUpdateStatus = new StringRequest(Request.Method.POST, URL_UPDATECODINATES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(Nearby.this, "Inserted!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Nearby.this, "Not Inserted!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Nearby.this, "Server Error!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //String scheduleNo = "42";
                params.put("lat", lat);
                params.put("lng", lang);
                return params;
            }
        };

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(requestUpdateStatus);

    }


}