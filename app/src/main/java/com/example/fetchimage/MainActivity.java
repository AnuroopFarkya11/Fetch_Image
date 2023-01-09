package com.example.fetchimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fetchimage.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    FirebaseStorage storage;
    StorageReference reference;
    File image_file;
    ProgressDialog progressDialog;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        progressDialog =new ProgressDialog(this);

        storage = FirebaseStorage.getInstance();
        reference = storage.getReference("IMAGES");
        try {
            image_file = File.createTempFile("temp",".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }


        binding.fetchButton.setOnClickListener(view1 -> fetchImage());


    }

    private void fetchImage() {


        progressDialog.setTitle("Downloading");
        progressDialog.setMessage("Processing!");
        progressDialog.show();

         StorageReference imageReference = reference.child("img1");

         imageReference.getFile(image_file)
                 .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                         if(task.isSuccessful())
                         {
                             progressDialog.dismiss();
                             Bitmap image_bitmap = BitmapFactory.decodeFile(image_file.getAbsolutePath());
                             binding.imageview.setImageBitmap(image_bitmap);
                             Toast.makeText(MainActivity.this, "Download Complete", Toast.LENGTH_SHORT).show();


                         }else
                         {
                             Toast.makeText(MainActivity.this, "Sorry", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });


    }
}