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

public class ReferenceDetailsActivity extends Activity implements View.OnClickListener{

    private EditText nameEditText;
    private EditText designationEditText;
    private EditText organizationEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    private Button nextButton;

    private ArrayList pojoObjArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference_details);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        designationEditText = (EditText) findViewById(R.id.designationEditText);
        organizationEditText = (EditText) findViewById(R.id.organizationEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);

        nextButton = (Button) findViewById(R.id.nextButtonReference);
        nextButton.setOnClickListener(this);

        pojoObjArrayList = new ArrayList();
    }

    @Override
    public void onClick(View v) {

        //get the values provided by the UI (User Interface)
        String providedName = nameEditText.getText().toString();
        String providedDesignation = designationEditText.getText().toString();
        String providedOrganization = organizationEditText.getText().toString();
        String providedAddress = addressEditText.getText().toString();
        String providedPhone = phoneEditText.getText().toString();
        String providedEmail = emailEditText.getText().toString();

        ReferenceDetailsPojo pojoObj = new ReferenceDetailsPojo();
        pojoObj.setName(providedName);
        pojoObj.setDesignation(providedDesignation);
        pojoObj.setOrganization(providedOrganization);
        pojoObj.setAddress(providedAddress);
        pojoObj.setPhone(providedPhone);
        pojoObj.setEmail(providedEmail);

        pojoObjArrayList.add(pojoObj);

        insert(pojoObj);

        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }

    public void insert(ReferenceDetailsPojo paraPojoObj)
    {
        // First we have to open our DbHelper class by creating a new object of that
        AndroidOpenDbHelperReference androidOpenDbHelperReferenceObj = new AndroidOpenDbHelperReference(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = androidOpenDbHelperReferenceObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_NAME, paraPojoObj.getName());
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_DESIGNATION, paraPojoObj.getDesignation());
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_ORGANIZATION, paraPojoObj.getOrganization());
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_ADDRESS, paraPojoObj.getAddress());
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_PHONE, paraPojoObj.getPhone());
        contentValues.put(AndroidOpenDbHelperReference.COLUMN_NAME_EMAIL, paraPojoObj.getEmail());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(AndroidOpenDbHelperReference.TABLE_NAME_REFERENCE_DETAILS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }
}
