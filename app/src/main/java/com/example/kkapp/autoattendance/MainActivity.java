package com.example.kkapp.autoattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    DataBase_Helper D_Helper;
    RelativeLayout bnew,teacherid,studentid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.from_middle, R.anim.in_middle);
        Setting();
        TeacherID();
        student();
        D_Helper=new DataBase_Helper(this);
    }
    public void Setting()
    {
        bnew = (RelativeLayout) findViewById(R.id.hotspotsetting);
        bnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Setting.class);
                startActivity(intent);
            }
        });
    }
    public void TeacherID()
    {
        teacherid = (RelativeLayout) findViewById(R.id.teacherlog);
        teacherid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,TeacherID.class);
                startActivity(in);
            }
        });
    }
    public void student()
    {
        studentid = (RelativeLayout) findViewById(R.id.studentlog);
        studentid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,StudentID.class);
                startActivity(in);
            }
        });
    }

}
