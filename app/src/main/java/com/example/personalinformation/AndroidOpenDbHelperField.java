package com.example.personalinformation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

// A helper class to manage database creation and version management.
public class AndroidOpenDbHelperField extends SQLiteOpenHelper{

    // Database attributes
    public static final String DB_NAME = "field_interest_db";
    public static final int DB_VERSION = 1;

    // Table attributes
    public static final String TABLE_NAME_FIELD_OF_INTEREST = "field_of_interest_table";
    public static final String COLUMN_NAME_FIELD_OF_INTEREST = "field_fieldofinterest_column";
    public static final String COLUMN_NAME_SKILLS = "field_skills_column";
    public static final String COLUMN_NAME_STRENGTHS = "field_strengths_column";
    public static final String COLUMN_NAME_HOBBIES = "field_hobbies_column";

    public AndroidOpenDbHelperField(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Called when the database is created for the first time.
    //This is where the creation of tables and the initial population of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // We need to check whether table that we are going to create is already exists.
        //Because this method get executed every time we created an object of this class.
        //"create table if not exists TABLE_NAME ( BaseColumns._ID integer primary key autoincrement, FIRST_COLUMN_NAME text not null, SECOND_COLUMN_NAME integer not null);"
        String sqlQueryToCreateFieldOfInterestTable = "create table if not exists " + TABLE_NAME_FIELD_OF_INTEREST + " ( " + BaseColumns._ID + " integer primary key autoincrement, "
                + COLUMN_NAME_FIELD_OF_INTEREST + " text not null, "
                + COLUMN_NAME_SKILLS + " text not null, "
                + COLUMN_NAME_STRENGTHS + " text not null, "
                + COLUMN_NAME_HOBBIES + " text not null);";

        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(sqlQueryToCreateFieldOfInterestTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2) {
            //Upgrade the database
        }
    }
}
