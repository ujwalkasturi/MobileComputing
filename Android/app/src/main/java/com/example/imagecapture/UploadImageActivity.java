package com.example.imagecapture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadImageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private ImageView capturedImage;
    private Button uploadButton;
    private static final String[] categories = {"Animals", "Food", "Misc", "People", "Places"};
    String imageCategory;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_upload_image);
        spinner = (Spinner)findViewById(R.id.categories);
        capturedImage = findViewById(R.id.imageViewUpload);
        uploadButton = findViewById(R.id.uploadButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UploadImageActivity.this,
                android.R.layout.simple_spinner_item, categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        Bitmap imageBitMap = (Bitmap) extras.getParcelable("capturedImageBitMap");
        capturedImage.setImageBitmap(imageBitMap);
        //Conversion of bitmap to byte array to send it to server
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        imageBitMap.compress(Bitmap.CompressFormat.JPEG,100,imageStream);
        byte[] byteArray = imageStream.toByteArray();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttpClient client = new OkHttpClient();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String filename = "image_"+timeStamp+".jpg";
                RequestBody formBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("category", imageCategory)
                        .addFormDataPart("image", filename,RequestBody.create(MediaType.parse("image/jpg"), byteArray))
                        .build();

                Request request = new Request.Builder().url("http://192.168.0.106:5000/").post(formBody).build();

                Call call = client.newCall(request);
                Response response = null;
                try {
                    response = call.execute();
                    System.out.println("from server - "+response.body().string());


                } catch (IOException e) {
                    e.printStackTrace();

                }

                //System.out.println(response.code());

                System.out.println("Category Selected: "+ imageCategory);
                testMethod(imageCategory);
            }
        });
    }

    private void testMethod(String imageCategory){
        Toast.makeText(this, "Image Category:-  "+ imageCategory, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         switch (i){
             case 0:
                 imageCategory = "Animals";
                 // Whatever you want to happen when the first item gets selected
                 break;
             case 1:
                 imageCategory = "Food";
                 // Whatever you want to happen when the second item gets selected
                 break;
             case 2:
                 imageCategory = "Misc";
                 // Whatever you want to happen when the thrid item gets selected
                 break;
             case 3:
                 imageCategory = "People";
                 // Whatever you want to happen when the thrid item gets selected
                 break;
             case 4:
                 imageCategory = "Places";
                 // Whatever you want to happen when the thrid item gets selected
                 break;
         }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}