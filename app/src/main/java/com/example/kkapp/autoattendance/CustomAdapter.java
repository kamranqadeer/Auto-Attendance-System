package com.example.kkapp.autoattendance;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kamran qadeer on 4/30/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    Cursor cursor;
    Button button1;
    public CustomAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subjectname, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!cursor.moveToPosition(position)) {
            return; // bail if returned null
        }
        final String Subject_name = cursor.getString(cursor.getColumnIndex(Contracts.Teacher_Subjects.COLUMN_1));
        long id = cursor.getLong(cursor.getColumnIndex(Contracts.Teacher_Subjects._ID));
        int i = (int) id;
        final String ss = i + "";
        holder.textView.setText(Subject_name);
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Tea_Sub_Details.class);
                intent.putExtra("Subject_name", Subject_name);
                intent.putExtra("_ID", ss);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.TeacherSubjects);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

