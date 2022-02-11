package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.show_ip);
        b.setOnClickListener(this);
        findViewById(R.id.connect).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_ip: {
                WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                manager.setWifiEnabled(true);
                String ipAddress = Formatter.formatIpAddress(manager.getConnectionInfo().getIpAddress());
                ((Button) view).setText(ipAddress);
                String warningNoWifiConnected = "Please check that you are connected to wifi";
                if(ipAddress.equals("0.0.0.0"))
                    Toast.makeText(this,warningNoWifiConnected,Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.connect: {
                EditText ipInput = findViewById(R.id.input_ip_address);
                if(ipInput.getVisibility() == View.INVISIBLE)ipInput.setVisibility(View.VISIBLE);
                else {

                }
            }
        }
    }
}