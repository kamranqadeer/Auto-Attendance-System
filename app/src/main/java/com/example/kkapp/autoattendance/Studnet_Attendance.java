package com.example.kkapp.autoattendance;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Studnet_Attendance extends AppCompatActivity {
    Button WIFI, ATTENDACE;
    EditText name, pass;
    Context context=null;
    WifiConfiguration wifiConfiguration = new WifiConfiguration();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studnet__attendance);
        StudentWhiFi();
        WiFi_connect();


    }

    public void StudentWhiFi() {
        WIFI = (Button) findViewById(R.id.wifi);
        WIFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                WifiApControl apControl = WifiApControl.getInstance(context);
                apControl.setEnabled(wifiConfiguration, true);
            }
        });
    }
    public void WiFi_connect()
    {
        name=findViewById(R.id.wifiname);
        pass=findViewById(R.id.wifipassword);
        wifiConfiguration.SSID = name.getText().toString();
        wifiConfiguration.preSharedKey = pass.getText().toString();
        wifiConfiguration.hiddenSSID = false;
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wifiConfiguration.allowedKeyManagement.set(4);
        wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    }
}
