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

public class ProjectDetailsActivity extends Activity implements View.OnClickListener{

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText roleEditText;
    private EditText teamStrengthEditText;
    private EditText durationEditText;

    Button nextButton;

    private ArrayList pojoObjArrayList;
    //POJO class is a simple JAVA class which has private variables and public getters and setters for those variables.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        roleEditText = (EditText) findViewById(R.id.roleEditText);
        teamStrengthEditText = (EditText) findViewById(R.id.teamStrengthEditText);
        durationEditText = (EditText) findViewById(R.id.durationEditText);

     /*   nextButton = (Button) findViewById(R.id.nextButtonWork);
        nextButton.setOnClickListener(this);*/

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedTitle = titleEditText.getText().toString();
        String providedDescription = descriptionEditText.getText().toString();
        String providedRole = roleEditText.getText().toString();
        int providedTeamStrength = Integer.parseInt(teamStrengthEditText.getText().toString());
        int providedDuration = Integer.parseInt(durationEditText.getText().toString());

        ProjectDetailsPojo pojoObj = new ProjectDetailsPojo();
        pojoObj.setTitle(providedTitle);
        pojoObj.setDescription(providedDescription);
        pojoObj.setRole(providedRole);
        pojoObj.setTeamStrength(providedTeamStrength);
        pojoObj.setDuration(providedDuration);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);


    }

    public void insert(ProjectDetailsPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperProject androidOpenDbHelperProjectObj = new AndroidOpenDbHelperProject(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperProjectObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperProject.COLUMN_NAME_TITLE, paraPojoObj.getTitle());
        contentValues.put(AndroidOpenDbHelperProject.COLUMN_NAME_DESCRIPTION, paraPojoObj.getDescription());
        contentValues.put(AndroidOpenDbHelperProject.COLUMN_NAME_ROLE, paraPojoObj.getRole());
        contentValues.put(AndroidOpenDbHelperProject.COLUMN_NAME_TEAMSTRENGTH, paraPojoObj.getTeamStrength());
        contentValues.put(AndroidOpenDbHelperProject.COLUMN_NAME_DURATION, paraPojoObj.getDuration());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperProject.TABLE_NAME_PROJECT_DETAILS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }

    public void saveButtonProject(View view) {

        Intent intent = new Intent(this, FieldOfInterestActivity.class);
        startActivity(intent);

    }
}
