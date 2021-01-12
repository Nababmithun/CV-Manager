package com.example.personalinformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

// A helper class to manage database creation and version management.
public class AndroidOpenDbHelperAcademic extends SQLiteOpenHelper{

    // Database attributes
    public static final String DB_NAME = "academic_details_db";
    public static final int DB_VERSION = 1;

    // Table attributes
    public static final String TABLE_NAME_ACADEMIC_DETAILS = "academic_details_table";
    public static final String COLUMN_NAME_COURSE = "academic_course_column";
    public static final String COLUMN_NAME_MARKS = "academic_marks_column";
    public static final String COLUMN_NAME_YEAR = "academic_year_column";

    public AndroidOpenDbHelperAcademic(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Called when the database is created for the first time.
    //This is where the creation of tables and the initial population of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // We need to check whether table that we are going to create is already exists.
        //Because this method get executed every time we created an object of this class.
        //"create table if not exists TABLE_NAME ( BaseColumns._ID integer primary key autoincrement, FIRST_COLUMN_NAME text not null, SECOND_COLUMN_NAME integer not null);"
        String sqlQueryToCreateAcademicDetailsTable = "create table if not exists " + TABLE_NAME_ACADEMIC_DETAILS + " ( " + BaseColumns._ID + " integer primary key autoincrement, "
                + COLUMN_NAME_COURSE + " text not null, "
                + COLUMN_NAME_MARKS + " text not null, "
                + COLUMN_NAME_YEAR + " text not null);";

        //+ COLLUMN_NAME_CGPA + " real not null);"; -> when the data type of cgpa is float or double

        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(sqlQueryToCreateAcademicDetailsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2) {
            //Upgrade the database
        }
    }
}
