package com.jonathannalikka.chooserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class NewPersonActivity extends AppCompatActivity {

    //ui instance variables for buttons and textboxes

    EditText etFname, etLname;
    Button btnAdd, btnCancel, btnImage;
    ImageView ivPerson;

    Uri targetUri;
    Bitmap bitmap;

    //result code for when ok button is pressed
    public static final int add_person_result_code = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        getSupportActionBar().hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.75),(int)(height * 0.75));

        //initialize edit texts

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);

        //initialize buttons and add click listeners
        btnAdd = findViewById(R.id.btnAddFinal);
        btnCancel = findViewById(R.id.btnCancel);
        btnImage = findViewById(R.id.btnChooseImage);
        ivPerson = findViewById(R.id.ivPerson);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when adding a new person, and their name to the array list and set the result

                String fname = etFname.getText().toString().trim();
                String lname = etLname.getText().toString().trim();

                ApplicationClass.people.add(new Person(fname, lname, bitmap));
                setResult(add_person_result_code);
                finish();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //closes activity
                finish();
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        //if result is ok, get the data returned and set image view to image selected


        if (resultCode == RESULT_OK){
            targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                ivPerson.setVisibility(View.VISIBLE);
                ivPerson.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

}