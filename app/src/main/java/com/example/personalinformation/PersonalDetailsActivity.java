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

public class PersonalDetailsActivity extends Activity implements OnClickListener{

    private EditText nameEditText;
    private EditText addressEditText;
    private EditText emailEditText;
    private EditText contactEditText;

    private Button nextButton;

    private ArrayList pojoObjArrayList;
    //POJO class is a simple JAVA class which has private variables and public getters and setters for those variables.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        contactEditText = (EditText) findViewById(R.id.contactEditText);

        nextButton = (Button) findViewById(R.id.nextButtonPersonal);
        nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedName = nameEditText.getText().toString();
        String providedAddress = addressEditText.getText().toString();
        String providedEmail = emailEditText.getText().toString();
        String providedContact = contactEditText.getText().toString();

        PersonalDetailsPojo pojoObj = new PersonalDetailsPojo();
        pojoObj.setName(providedName);
        pojoObj.setAddress(providedAddress);
        pojoObj.setEmail(providedEmail);
        pojoObj.setContact(providedContact);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);

        Intent intent = new Intent(this, AcademicDetailsActivity.class); //Intent intent = new Intent(this, AcademicDetailsActivity.class);
        startActivity(intent);
    }

    public void insert(PersonalDetailsPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperPersonal androidOpenDbHelperPersonalObj = new AndroidOpenDbHelperPersonal(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperPersonalObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperPersonal.COLUMN_NAME_NAME, paraPojoObj.getName());
        contentValues.put(AndroidOpenDbHelperPersonal.COLUMN_NAME_ADDRESS, paraPojoObj.getAddress());
        contentValues.put(AndroidOpenDbHelperPersonal.COLUMN_NAME_EMAIL, paraPojoObj.getEmail());
        contentValues.put(AndroidOpenDbHelperPersonal.COLUMN_NAME_CONTACT, paraPojoObj.getContact());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperPersonal.TABLE_NAME_PERSONAL_DETAILS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();
    }
}
