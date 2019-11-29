package com.example.kkapp.autoattendance;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tea_Sub_Details extends AppCompatActivity {
    TextView textView,tex;
    Button bt;
    Long id;
    String d ="1/12"+"2018";
    DataBase_Helper db_helper;
    RecyclerView recyclerView;
    Tea_Sub_Det_Adapter adapter2;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea__sub__details);
        Intent i=getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        set_time_date();
        db_helper=new DataBase_Helper(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView =(TextView)findViewById(R.id.textView2);
        Toast.makeText(this, "FIRST CREAT HOTSPOT", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "SELECT DATE AND TIME", Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        if(intent.hasExtra("Subject_name"))
        {
            String s=intent.getStringExtra("Subject_name");
            textView.setText(s);

        }
        if(intent.hasExtra("_ID"))
        {
            String a=intent.getStringExtra("_ID");
            id=Long.parseLong(a);
        }

        Toast.makeText(this, "id "+id, Toast.LENGTH_SHORT).show();
        Cursor cursor =getAllNotes();
        // Toast.makeText(this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        adapter2=new Tea_Sub_Det_Adapter(this,cursor);
        recyclerView.setAdapter(adapter2);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            // COMPLETED (4) Override onMove and simply return false inside
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //do nothing, we only care about swiping
                return false;
            }

            // COMPLETED (5) Override onSwiped
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // COMPLETED (8) Inside, get the viewHolder's itemView's tag and store in a long variable id
                //get the id of the item being swiped
                long id = (long) viewHolder.itemView.getTag();
                // COMPLETED (9) call removeGuest and pass through that id
                //remove from DB
                removeNotes(id);
                // COMPLETED (10) call swapCursor on mAdapter passing in getAllGuests() as the argument
                //update the list
                adapter2.swapCursor(getAllNotes());
            }

            //COMPLETED (11) attach the ItemTouchHelper to the waitlistRecyclerView
        }).attachToRecyclerView(recyclerView);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return Tea_Sub_Details.super.onOptionsItemSelected(item);
    }

    public void back(View view) {

        startActivity(new Intent(Tea_Sub_Details.this,MainActivity.class));
    }

    public void AddDate(View view) {
        Toast.makeText(this, "CURRENT TIME "+d, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(Tea_Sub_Details.this);
        View mview=getLayoutInflater().inflate(R.layout.date_log,null);
        Button button=(Button) mview.findViewById(R.id.Datesave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(d.trim().equals(""))
                {
                    Toast.makeText(Tea_Sub_Details.this, "Please Select DATE", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(addN(d,id)==true)
                    {

                        adapter2.swapCursor(getAllNotes());
                        Toast.makeText(Tea_Sub_Details.this, "Notes add Successfully", Toast.LENGTH_SHORT).show();
                      //  editText.getText().clear();
                        Dialog_Dismis();

                    }
                    else
                    {
                        Toast.makeText(Tea_Sub_Details.this, "Notes not add Successfully", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        mbuilder.setView(mview);
        alertDialog=mbuilder.create();
        alertDialog.show();
    }
    public void Dialog_Dismis()
    {
        alertDialog.dismiss();
    }

    public boolean addN(String date,Long id)
    {
        SQLiteDatabase mDb=db_helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contracts.Teacher_Subjects_Detail.COLUMN_1,date);
        values.put(Contracts.Teacher_Subjects_Detail.COLUMN_2,id);
        long result= mDb.insert(Contracts.Teacher_Subjects_Detail.TABLE_NAME, null, values);
        if (result==-1)
        {
            return  false;
        }
        else {

            return true;
        }
    }
    private Cursor getAllNotes() {
        SQLiteDatabase  mDb=db_helper.getReadableDatabase();
        String[] columns=new String[]{Contracts.Teacher_Subjects_Detail._ID,Contracts.Teacher_Subjects_Detail.COLUMN_1};
        Cursor cr=mDb.query(Contracts.Teacher_Subjects_Detail.TABLE_NAME,columns,Contracts.Teacher_Subjects_Detail.COLUMN_2+" = '"+id+"'",null,null,null,null);
        return cr;

    }
    private boolean removeNotes(long id) {
        SQLiteDatabase  mDb=db_helper.getReadableDatabase();
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return mDb.delete(Contracts.Teacher_Subjects_Detail.TABLE_NAME, Contracts.Teacher_Subjects_Detail._ID + "=" + id, null) > 0;
    }
    public void hotspotsetting(View view) {
        Intent intent=new Intent(Tea_Sub_Details.this,Setting.class);
        startActivity(intent);
    }
    public void set_time_date()
    {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        d=currentDateTimeString;
    }
}
