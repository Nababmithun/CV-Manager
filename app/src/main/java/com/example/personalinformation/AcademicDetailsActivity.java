package com.example.personalinformation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AcademicDetailsActivity extends Activity implements OnClickListener {

    private EditText courseEditText;
    private EditText marksSecuredEditText;
    private EditText yearOfPassingEditText;

    private Button nextButton;

    private ArrayList pojoObjArrayList;
    //POJO class is a simple JAVA class which has private variables and public getters and setters for those variables.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_details);

        courseEditText = (EditText) findViewById(R.id.courseEditText);
        marksSecuredEditText = (EditText) findViewById(R.id.marksSecuredEditText);
        yearOfPassingEditText = (EditText) findViewById(R.id.yearOfPassingEditText);

        nextButton = (Button) findViewById(R.id.nextButtonAcademic);
        nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedCourse = courseEditText.getText().toString();
        float providedMarksSecured = Float.parseFloat(marksSecuredEditText.getText().toString());
        int providedYearOfPassing = Integer.parseInt(yearOfPassingEditText.getText().toString());

        AcademicDetailsPojo pojoObj = new AcademicDetailsPojo();
        pojoObj.setDegreeOrCourse(providedCourse);
        pojoObj.setMarksOrCgpa(providedMarksSecured);
        pojoObj.setYearOfPassing(providedYearOfPassing);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);

        Intent intent = new Intent(this, WorkExperienceActivity.class);
        startActivity(intent);
    }

    public void insert(AcademicDetailsPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperAcademic androidOpenDbHelperAcademicObj = new AndroidOpenDbHelperAcademic(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperAcademicObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperAcademic.COLUMN_NAME_COURSE, paraPojoObj.getDegreeOrCourse());
        contentValues.put(AndroidOpenDbHelperAcademic.COLUMN_NAME_MARKS, paraPojoObj.getMarksOrCgpa());
        contentValues.put(AndroidOpenDbHelperAcademic.COLUMN_NAME_YEAR, paraPojoObj.getYearOfPassing());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperAcademic.TABLE_NAME_ACADEMIC_DETAILS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }
}