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

public class AchievementsActivity extends Activity implements View.OnClickListener{

    EditText achievementsEditText;
    EditText curricularEditText;
    EditText extraCurricularEditText;

    Button nextButton;

    private ArrayList pojoObjArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        achievementsEditText = (EditText) findViewById(R.id.achievementsEditText);
        curricularEditText = (EditText) findViewById(R.id.curricularEditText);
        extraCurricularEditText = (EditText) findViewById(R.id.extraCurricularEditText);

        nextButton = (Button) findViewById(R.id.nextButtonAchievements);
        nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        String providedAchievements = achievementsEditText.getText().toString();
        String providedCurricular = curricularEditText.getText().toString();
        String providedExtraCurricular = extraCurricularEditText.getText().toString();

        AchievementsPojo pojoObj = new AchievementsPojo();
        pojoObj.setAchievements(providedAchievements);
        pojoObj.setCurricular(providedCurricular);
        pojoObj.setExtraCurricular(providedExtraCurricular);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);

        Intent intent = new Intent(this, ReferenceDetailsActivity.class);
        startActivity(intent);
    }

    public void insert(AchievementsPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperAchievements androidOpenDbHelperAchievementsObj = new AndroidOpenDbHelperAchievements(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperAchievementsObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperAchievements.COLUMN_NAME_ACHIEVEMENTS, paraPojoObj.getAchievements());
        contentValues.put(AndroidOpenDbHelperAchievements.COLUMN_NAME_CURRICULAR, paraPojoObj.getCurricular());
        contentValues.put(AndroidOpenDbHelperAchievements.COLUMN_NAME_EXTRACURRICULAR, paraPojoObj.getExtraCurricular());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperAchievements.TABLE_NAME_ACHIEVEMENTS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }
}
