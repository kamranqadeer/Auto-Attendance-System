package com.example.kkapp.autoattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spl_main);
        Splash();
    }
        public void Splash()
        {
                    Thread td=new Thread()
                    {
                        public void run()
                        {
                            try{
                                sleep(1000);
                                Intent it=new Intent(getApplicationContext(),MainActivity.class );
                                startActivity(it);
                                finish();
                            }
                            catch (Exception ex)
                            {
                                ex.printStackTrace();
                            }
                        }
                    };
                    td.start();

        }
}
