package com.example.kkapp.autoattendance;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherLog extends AppCompatActivity {
    DataBase_Helper db;
    // private SQLiteDatabase mDb;
    EditText editText;
    RecyclerView recyclerView;
    Button bt;
    AlertDialog alertDialog ;
    private CustomAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachersubjcet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db=new DataBase_Helper(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor =getAllBooks();
        // Toast.makeText(this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        adapter1=new CustomAdapter(this,cursor);
        recyclerView.setAdapter(adapter1);

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
                removeGuest(id);
                // COMPLETED (10) call swapCursor on mAdapter passing in getAllGuests() as the argument
                //update the list
                adapter1.swapCursor(getAllBooks());
            }

            //COMPLETED (11) attach the ItemTouchHelper to the waitlistRecyclerView
        }).attachToRecyclerView(recyclerView);

    }

    public void ADDSubject(View view) {
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(TeacherLog.this);
        View mview=getLayoutInflater().inflate(R.layout.subject_dialog,null);
        final EditText editText=(EditText) mview.findViewById(R.id.EnterSubject);
        Button button=(Button) mview.findViewById(R.id.Datesave2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=editText.getText().toString();
                if(s.trim().equals(""))
                {
                    Toast.makeText(TeacherLog.this, "Please Enter Book Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(add(s)==true)
                    {

                        adapter1.swapCursor(getAllBooks());
                        Toast.makeText(TeacherLog.this, "Book add Successfully", Toast.LENGTH_SHORT).show();
                        editText.getText().clear();
                        Dialog_Dismis();

                    }
                    else
                    {
                        Toast.makeText(TeacherLog.this, "Book not add Successfully", Toast.LENGTH_SHORT).show();

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
    public boolean add(String book_name)
    {
        SQLiteDatabase mDb=db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contracts.Teacher_Subjects.COLUMN_1,book_name);
        long result= mDb.insert(Contracts.Teacher_Subjects.TABLE_NAME, null, values);
        if (result==-1)
        {
            return  false;
        }
        else {

            return true;
        }
    }
    private Cursor getAllBooks() {
        SQLiteDatabase  mDb=db.getReadableDatabase();
        Cursor cr=mDb.rawQuery("select * from "+Contracts.Teacher_Subjects.TABLE_NAME,null);
        //  cr = db.query(Contract.TableDetail.TABLE_NAME, null, null, null, null,null, null);
        return cr;

    }
    private boolean removeGuest(long id) {
        SQLiteDatabase  mDb=db.getReadableDatabase();
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return mDb.delete(Contracts.Teacher_Subjects.TABLE_NAME, Contracts.Teacher_Subjects._ID + "=" + id, null) > 0;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
