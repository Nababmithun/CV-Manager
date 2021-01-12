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

public class WorkExperienceActivity extends Activity implements View.OnClickListener{

    private EditText organizationEditText;
    private EditText designationEditText;
    private EditText fromDateEditText;
    private EditText toDateEditText;
    private EditText rolePlayedEditText;

    private Button nextButton;

    private ArrayList pojoObjArrayList;
    //POJO class is a simple JAVA class which has private variables and public getters and setters for those variables.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        organizationEditText = (EditText) findViewById(R.id.organizationEditText);
        designationEditText = (EditText) findViewById(R.id.designationEditText);
        fromDateEditText = (EditText) findViewById(R.id.fromEditText);
        toDateEditText = (EditText) findViewById(R.id.toEditText);
        rolePlayedEditText = (EditText) findViewById(R.id.rolePlayedEditText);

       // nextButton = (Button) findViewById(R.id.nextButtonWork);
       // nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedOrganization = organizationEditText.getText().toString();
        String providedDesignation = designationEditText.getText().toString();
        String providedFromDate = fromDateEditText.getText().toString();
        String providedToDate = toDateEditText.getText().toString();
        String providedRolePlayed = rolePlayedEditText.getText().toString();

        WorkExperiencePojo pojoObj = new WorkExperiencePojo();

        pojoObj.setOrganization(providedOrganization);
        pojoObj.setDesignation(providedDesignation);
        pojoObj.setFromDate(providedFromDate);
        pojoObj.setToDate(providedToDate);
        pojoObj.setRole(providedRolePlayed);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);


    }

    public void insert(WorkExperiencePojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperWork androidOpenDbHelperWorkObj = new AndroidOpenDbHelperWork(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperWorkObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperWork.COLUMN_NAME_ORGANIZATION, paraPojoObj.getOrganization());
        contentValues.put(AndroidOpenDbHelperWork.COLUMN_NAME_DESIGNATION, paraPojoObj.getDesignation());
        contentValues.put(AndroidOpenDbHelperWork.COLUMN_NAME_FROMDATE, paraPojoObj.getFromDate());
        contentValues.put(AndroidOpenDbHelperWork.COLUMN_NAME_TODATE, paraPojoObj.getToDate());
        contentValues.put(AndroidOpenDbHelperWork.COLUMN_NAME_ROLE, paraPojoObj.getRole());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperWork.TABLE_NAME_WORK_EXPERIENCE, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }

    public void nextwork(View view) {

        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        startActivity(intent);
    }
}
