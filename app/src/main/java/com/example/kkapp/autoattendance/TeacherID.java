package com.example.kkapp.autoattendance;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherID extends AppCompatActivity {
    Button next,save;
    EditText name,id,edu,cont;
    private DataBase_Helper dataBase_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.from_middle, R.anim.in_middle);
        SubjectList();
        InsertTeacherInformation();
       // ShowTeacherInformation();
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

    public void SubjectList()
    {
        next = (Button) findViewById(R.id.NextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(TeacherID.this,TeacherLog.class);
                startActivity(in);
            }
        });
    }
    public void InsertTeacherInformation()
    {
        dataBase_helper=new DataBase_Helper(this);
        name=findViewById(R.id.NAME);
        id=findViewById(R.id.ID);
        edu=findViewById(R.id.Education);
        cont=findViewById(R.id.Contect);
        save=findViewById(R.id.TeacherDetainlSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean Tost=dataBase_helper.Teacher_Information(name.getText().toString(),id.getText().toString(),edu.getText().toString(),cont.getText().toString());
              if(Tost)
                  Toast.makeText(TeacherID.this,"INFORMATION IS SUCCESS_FULLY SAVE",Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(TeacherID.this,"INFORMATION IS NOT SAVE",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ShowTeacherInformation()
    {
        Cursor res=dataBase_helper.GetTeacherData();
        if(res.getCount()==0)
        {
            Toast.makeText(TeacherID.this,"PLEAS ENTER A DETAIL",Toast.LENGTH_SHORT).show();
        }
        else
        {
            name=findViewById(R.id.NAME);
            id=findViewById(R.id.ID);
            edu=findViewById(R.id.Education);
            cont=findViewById(R.id.Contect);
            while (res.moveToNext())
            {
                name.setText(res.getString(0), TextView.BufferType.EDITABLE);
                id.setText(res.getString(1), TextView.BufferType.EDITABLE);
                edu.setText(res.getString(3), TextView.BufferType.EDITABLE);
                cont.setText(res.getString(4), TextView.BufferType.EDITABLE);
            }
            Toast.makeText(TeacherID.this,"DATA IS SHOW",Toast.LENGTH_SHORT).show();
        }

    }

}
