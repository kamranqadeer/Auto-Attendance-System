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

import java.net.IDN;

public class StudentID extends AppCompatActivity {
    Button next,save;
    EditText name,id,edu,cont;
    private DataBase_Helper dataBase_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_id);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SubjectList();
        save_button();
       // ShowStudentInformation();
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
    public void save_button()
    {
        save = (Button) findViewById(R.id.StudentDetainlSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertStudentInformation();
            }
        });
    }
    public void SubjectList()
    {
        next = (Button) findViewById(R.id.NextButton1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(StudentID.this,Studnet_Attendance.class);
                startActivity(in);
            }
        });
    }
    public void InsertStudentInformation()
    {
        dataBase_helper=new DataBase_Helper(this);
        name=findViewById(R.id.NAME1);
        id=findViewById(R.id.ID1);
        edu=findViewById(R.id.Education1);
        cont=findViewById(R.id.Contect1);
        save=findViewById(R.id.StudentDetainlSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Tost=dataBase_helper.Student_Information(name.getText().toString(),id.getText().toString(),edu.getText().toString(),cont.getText().toString());
                if(Tost)
                    Toast.makeText(StudentID.this,"INFORMATION IS SUCCESS_FULLY SAVE",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(StudentID.this,"INFORMATION IS NOT SAVE",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ShowStudentInformation()
    {
        Cursor res=dataBase_helper.GetStrudentData();
        String Na = null,Id=null,Edu=null,Con=null;
        name=findViewById(R.id.NAME1);
        id=findViewById(R.id.ID1);
        edu=findViewById(R.id.Education1);
        cont=findViewById(R.id.Contect1);
        if(res.getCount()==0)
        {
            Toast.makeText(StudentID.this,"PLEAS ENTER A DETAIL",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (res.moveToNext())
            {
               Na=res.getString(0);
              // name.setText(Na, TextView.BufferType.EDITABLE);
                Id=res.getString(1);
                //id.setText(Id, TextView.BufferType.EDITABLE);
                Edu=res.getString(2);
                //edu.setText(Edu, TextView.BufferType.EDITABLE);
                Con=res.getString(3);
                //cont.setText(Con, TextView.BufferType.EDITABLE);
            }
            Toast.makeText(StudentID.this,Na,Toast.LENGTH_SHORT).show();
        }

    }
}
