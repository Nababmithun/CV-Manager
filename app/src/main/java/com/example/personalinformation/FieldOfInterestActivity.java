package com.example.personalinformation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FieldOfInterestActivity extends Activity implements View.OnClickListener{

    private EditText fieldEditText;
    private EditText skillsEditText;
    private EditText strengthsEditText;
    private EditText hobbiesEditText;

    private Button nextButton;

    private ArrayList pojoObjArrayList;
    //POJO class is a simple JAVA class which has private variables and public getters and setters for those variables.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_of_interest);

        fieldEditText = (EditText) findViewById(R.id.fieldOfInterestEditText);
        skillsEditText = (EditText) findViewById(R.id.skillsEditText);
        strengthsEditText = (EditText) findViewById(R.id.strengthsEditText);
        hobbiesEditText = (EditText) findViewById(R.id.hobbiesEditText);

        nextButton = (Button) findViewById(R.id.nextButtonField);
        nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedField = fieldEditText.getText().toString();
        String providedSkills = skillsEditText.getText().toString();
        String providedStrengths = strengthsEditText.getText().toString();
        String providedHobbies = hobbiesEditText.getText().toString();

        FieldOfInterestPojo pojoObj = new FieldOfInterestPojo();
        pojoObj.setField(providedField);
        pojoObj.setSkills(providedSkills);
        pojoObj.setStrengths(providedStrengths);
        pojoObj.setHobbies(providedHobbies);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);

        Intent intent = new Intent(this, AchievementsActivity.class);
        startActivity(intent);
    }

    public void insert(FieldOfInterestPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperField androidOpenDbHelperFieldObj = new AndroidOpenDbHelperField(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperFieldObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperField.COLUMN_NAME_FIELD_OF_INTEREST, paraPojoObj.getField());
        contentValues.put(AndroidOpenDbHelperField.COLUMN_NAME_SKILLS, paraPojoObj.getSkills());
        contentValues.put(AndroidOpenDbHelperField.COLUMN_NAME_STRENGTHS, paraPojoObj.getStrengths());
        contentValues.put(AndroidOpenDbHelperField.COLUMN_NAME_HOBBIES, paraPojoObj.getHobbies());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperField.TABLE_NAME_FIELD_OF_INTEREST, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }
}
