package com.example.kkapp.autoattendance;

import android.provider.BaseColumns;

/**
 * Created by kamran qadeer on 5/6/2018.
 */

public class Contracts {
    public static final class Teacher_Table implements BaseColumns
    {
        public static final String TABLE_NAME = "Teacher";
        public static final String COLUMN_1 = "Teacher_Name";
        public static final String COLUMN_2 = "Teacher_ID";
        public static final String COLUMN_3 = "Teacher_Education";
        public static final String COLUMN_4 = "Teacher_PhoneNo";
    }
    public static final class Student_Table implements BaseColumns
    {
        public static final String TABLE_NAME = "Student";
        public static final String COLUMN_1 = "Student_Name";
        public static final String COLUMN_2 = "Student_ID";
        public static final String COLUMN_3 = "Student_Batch";
        public static final String COLUMN_4 = "Student_PhoneNo";
    }
    public static final class Student_list_table implements BaseColumns
    {
        public static final String TABLE_NAME = "Studentlist";
        public static final String COLUMN_1 = "Student_Name";
        public static final String COLUMN_2 = "Student_ID";
        public static final String COLUMN_3 = "Student_department";
        public static final String COLUMN_4 = "Student_Batch";

    }
    public static final class Teacher_Subjects implements BaseColumns
    {
        public static final String TABLE_NAME = "Teacher_Subject";
        public static final String COLUMN_1 = "Subject_Name";
    }
    public static final class Teacher_Subjects_Detail implements BaseColumns
    {
        public static final String TABLE_NAME = "Teacher_subject_Detail";
        public static final String COLUMN_1 = "Subject_Detail";
        public static final String COLUMN_2 = "Subject_Name_IDFK";

    }
    public static final class Teacher_subject_Date implements BaseColumns
    {
        public static final String TABLE_NAME = "TeaSubDate";
        public static final String COLUMN_1 = "Date_Detail";
        public static final String COLUMN_2 = "Date_IDFK";

    }
    public static final class Student_Subjects implements BaseColumns
    {
        public static final String TABLE_NAME = "Student_Subject";
        public static final String COLUMN_1 = "Subject_Name";
    }
    public static final class Student_Subjects_Detail implements BaseColumns
    {
        public static final String TABLE_NAME = "Student_subject_Detail";
        public static final String COLUMN_1 = "Subject_Detail";
        public static final String COLUMN_2 = "Subject_Name_IDFK";

    }
}
