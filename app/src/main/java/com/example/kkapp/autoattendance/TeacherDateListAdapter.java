package com.example.kkapp.autoattendance;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kamran qadeer on 5/15/2018.
 */

public class TeacherDateListAdapter extends RecyclerView.Adapter<TeacherDateListAdapter.MyViewHolder> {
    Context context;
    Cursor cursor;
    public TeacherDateListAdapter(Context context, Cursor cursor) {

        this.context=context;
        this.cursor=cursor;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.attendance_item, parent, false);
        return new TeacherDateListAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (!cursor.moveToPosition(position)) {
            return; // bail if returned null
        }
        final String Date = cursor.getString(cursor.getColumnIndex(Contracts.Teacher_Subjects_Detail.COLUMN_1));
        long idd =cursor.getLong(cursor.getColumnIndex(Contracts.Teacher_Subjects_Detail._ID));
        holder.textView.setText(Date);
        holder.itemView.setTag(idd);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(context,KK_Notes.class);
                //intent.putExtra("Note",notes);
                //context.startActivity(intent);
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
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.AttendaceDate);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
