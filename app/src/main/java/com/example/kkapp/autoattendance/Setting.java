package com.example.kkapp.autoattendance;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends AppCompatActivity {
    String Name=null,Password=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CreatHotsPotButton();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void CreatHotsPotButton()
    {
        Button hotspotbutton = (Button) findViewById(R.id.HotspotCreat);
        hotspotbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bulidHotspot();
                CreateNewWifiApNetwork();
            }
        });
    }
    //this method is use to creat WIFI with user name and password
    public void CreateNewWifiApNetwork() {
        EditText name = (EditText)findViewById(R.id.HotspotName);
        EditText pass = (EditText)findViewById(R.id.HotspotPass);
        Name = name.getText().toString();
        Password = pass.getText().toString();
        if ((Name!= null) && (Password != null)) {
            ApManager ap = new ApManager(this.getApplicationContext());
            ap.createNewNetwork(Name, Password);
        }
        else  {
            ApManager ap = new ApManager(this.getApplicationContext());
            ap.createNewNetwork("AUTTOATTENDANCE", "KAMRAN.KK");
        }
    }
    public void bulidHotspot()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this.getApplicationContext())) {

            } else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
        CreateNewWifiApNetwork();
    }
}
