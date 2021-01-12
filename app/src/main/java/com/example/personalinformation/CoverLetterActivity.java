package com.example.personalinformation;

import android.app.Activity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CoverLetterActivity extends Activity implements View.OnClickListener {

    private EditText dateEditText;
    private EditText recAddressEditText;
    private EditText bodyEditText;

    private String name, address, email, contact;
    private String providedDate, providedRecAddress, providedBody;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_letter);

        dateEditText = (EditText) findViewById(R.id.dateEditText);
        recAddressEditText = (EditText) findViewById(R.id.recAddressEditText);
        bodyEditText = (EditText) findViewById(R.id.bodyEditText);

        saveButton = (Button) findViewById(R.id.saveButtonCover);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Document document = new Document();
       // Document document = new Document();

        Document document= (Document) new com.itextpdf.text.Document();

        providedDate = dateEditText.getText().toString();
        providedRecAddress = recAddressEditText.getText().toString();
        providedBody = bodyEditText.getText().toString();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyCVApp";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

//            Log.d("PDFCreator", "PDF Path: " + path);

            File file = new File(dir, "CoverLetter.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance((com.itextpdf.text.Document) document, fOut);

            //open the document
            ((com.itextpdf.text.Document) document).open();


            ///////////////////////////-------------------------////////////////////////////

            // First we need to make contact with the database we have created using the DbHelper class
            AndroidOpenDbHelperPersonal openHelperClass = new AndroidOpenDbHelperPersonal(this);

            // Then we need to get a readable database
            SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

            // We need a a guy to read the database query. Cursor interface will do it for us
            //(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
            Cursor cursor = sqliteDatabase.query(AndroidOpenDbHelperPersonal.TABLE_NAME_PERSONAL_DETAILS, null, null, null, null, null, null);
            // Above given query, read all the columns and fields of the table

            startManagingCursor(cursor);

            // Cursor object read all the fields. So we make sure to check it will not miss any by looping through a while loop
            while (cursor.moveToNext()) {
                // In one loop, cursor read one undergraduate all details
                // Assume, we also need to see all the details of each and every undergraduate
                // What we have to do is in each loop, read all the values, pass them to the POJO class
                //and create a ArrayList of undergraduates

                name = cursor.getString(cursor.getColumnIndex(AndroidOpenDbHelperPersonal.COLUMN_NAME_NAME));
                address = cursor.getString(cursor.getColumnIndex(AndroidOpenDbHelperPersonal.COLUMN_NAME_ADDRESS));
                email = cursor.getString(cursor.getColumnIndex(AndroidOpenDbHelperPersonal.COLUMN_NAME_EMAIL));
                contact = cursor.getString(cursor.getColumnIndex(AndroidOpenDbHelperPersonal.COLUMN_NAME_CONTACT));
            }

            ///////////////////////////-------------------------////////////////////////////

            //setting the universal font

            //empty paragraph for line spacing
            Paragraph paraEmpty = new Paragraph("\n");

            //1st paragraph for name of sender
            Paragraph para1 = new Paragraph(name);
            para1.setAlignment(Paragraph.ALIGN_CENTER);
            ((com.itextpdf.text.Document) document).add(para1);

            //2nd paragraph for address of sender
            Paragraph para2 = new Paragraph(address);
            para2.setAlignment(Paragraph.ALIGN_CENTER);
            ((com.itextpdf.text.Document) document).add(para2);

            //3rd paragraph for contact of sender
            Paragraph para3 = new Paragraph(contact);
            para3.setAlignment(Paragraph.ALIGN_CENTER);
            ((com.itextpdf.text.Document) document).add(para3);

            //4th paragraph for email of sender
            Paragraph para4 = new Paragraph(email);
            para4.setAlignment(Paragraph.ALIGN_CENTER);
            ((com.itextpdf.text.Document) document).add(para4);

            //5th paragraph for the separator line
            Paragraph para5 = new Paragraph("______________________________________________________________________________");
            ((com.itextpdf.text.Document) document).add(para5);

            //6th paragraph for providedDate
            Paragraph para6 = new Paragraph(providedDate);
            para6.setAlignment(Paragraph.ALIGN_LEFT);
            ((com.itextpdf.text.Document) document).add(para6);

            ((com.itextpdf.text.Document) document).add(paraEmpty);

            //7th paragraph for receiver's address
            Paragraph para7 = new Paragraph(providedRecAddress);
            para7.setAlignment(Paragraph.ALIGN_LEFT);
            ((com.itextpdf.text.Document) document).add(para7);

            ((com.itextpdf.text.Document) document).add(paraEmpty);

            //8th paragraph for body
            Paragraph para8 = new Paragraph(providedBody);
            para8.setAlignment(Paragraph.ALIGN_LEFT);
            ((com.itextpdf.text.Document) document).add(para8);

            Toast.makeText(getApplicationContext(), "Cover Letter Generated...", Toast.LENGTH_SHORT).show();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.getClass();
        }
    }
}