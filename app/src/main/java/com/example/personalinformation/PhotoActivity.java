package com.example.personalinformation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PhotoActivity extends Activity implements View.OnClickListener{

    private static int RESULT_LOAD_IMAGE = 1;
    SQLiteDatabase db;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Button loadPhotoButton = (Button) findViewById(R.id.loadPhotoButton);
        loadPhotoButton.setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            Bitmap bmp = BitmapFactory.decodeFile(picturePath);
            int nh = (int) ( bmp.getHeight() * (2048.0 / bmp.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(bmp, 2048, nh, true);
            imageView.setImageBitmap(scaled);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.loadPhotoButton)
        {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }

        else if(id == R.id.loadPhotoButton)
        {
            //create database
            db = openOrCreateDatabase("photo.db", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists tb (a blob)");
            //Toast.makeText(this, "Database created in onActivityResult", Toast.LENGTH_SHORT).show();

            //------------------delete database---------------

            deleteDatabase("photo.db");
            //Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();

            //------------------delete database---------------

            //---------------once again, create the database to get new data----------------
            db = openOrCreateDatabase("photo.db", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists tb (a blob)");
            //Toast.makeText(this, "Database Re-Created in onActivityResult", Toast.LENGTH_SHORT).show();

            try {
                FileInputStream fin = new FileInputStream(picturePath);
                byte[] image = new byte[fin.available()];
                fin.read(image);

                ContentValues values = new ContentValues();
                values.put("a", image);

                //insert ContentValues in database "db"
                db.insert("tb", null, values);

                fin.close();

//                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
//                    Cursor c = db.rawQuery("select * from tb", null);
//                    if(c.moveToNext())
//                    {
//                        byte[] image1 = c.getBlob(0);
//                        Bitmap bmp = BitmapFactory.decodeByteArray(image1, 0, image1.length);
                ///////////////scales the image to size of texture in screen
//                        int nh = (int) ( bmp.getHeight() * (512.0 / bmp.getWidth()) );
//                        Bitmap scaled = Bitmap.createScaledBitmap(bmp, 512, nh, true);
                /////////////
//                        imageView.setImageBitmap(scaled);//imageView.setImageBitmap(bmp);
//                    }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void doneButtonPhoto(View view) {

        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }
}

