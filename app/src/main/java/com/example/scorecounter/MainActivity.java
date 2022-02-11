package com.example.scorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.startServer);
        b.setOnClickListener(this);
        findViewById(R.id.connect).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startServer: {
                WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                manager.setWifiEnabled(true);
                String ipAddress = Formatter.formatIpAddress(manager.getConnectionInfo().getIpAddress());
                TextView ip = findViewById(R.id.server_ip);
                ip.setVisibility(View.VISIBLE);
                ip.setText(String.format("Server ip : %s", ipAddress));

                copyToClipboard("ScoreCounter server ip", ipAddress);

                if(ipAddress.equals("0.0.0.0"))showWifiWarning();
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
    public void showWifiWarning(){
        String warningNoWifiConnected = "Please check that you are connected to wifi";
        Toast.makeText(this,warningNoWifiConnected,Toast.LENGTH_LONG).show();
    }
    public void copyToClipboard(String label,String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label,text);
        clipboard.setPrimaryClip(clip);
    }
}