package com.example.fetchimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fetchimage.databinding.ActivityFetchAllBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FetchAll extends AppCompatActivity {

//    ActivityFetchAllBinding binding;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<Image_Model> image_modelList;
    ImageView imageView;

    GridView gridView;
    GridAdapter gridAdapter;
    Button fetch_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_all);



        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("IMAGES");

        image_modelList = new ArrayList<>();


        gridView = findViewById(R.id.grid_view);
        gridAdapter = new GridAdapter(FetchAll.this,image_modelList);

        gridView.setAdapter(gridAdapter);


//        fetch_btn = findViewById(R.id.fetch_button);
//        imageView = findViewById(R.id.image);

//
//        fetch_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(FetchAll.this,image_modelList.get(0).getIMAGE_NAME(),Toast.LENGTH_SHORT).show();
//                Picasso.get().load(image_modelList.get(0).getIMAGE_URL()).into(imageView);
//            }
//        });


//        binding.gridView.setAdapter(card_adapter);



//        binding.


        whatIsInList();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                image_modelList.clear();
//                String temp = snapshot.getValue().toString();
//                Log.d("FETCH_UPDATE",temp);
                if(snapshot.exists())
                {
                    for(DataSnapshot data : snapshot.getChildren())
                    {
                        Image_Model new_image = data.getValue(Image_Model.class);
                        String name = new_image.getIMAGE_NAME();
                        String url = new_image.getIMAGE_URL();
                        image_modelList.add(new_image);
                        gridAdapter.notifyDataSetChanged();

                        Log.d("FETCH_UPDATE",name+url);

                    }
                }
                else
                {
                    Toast.makeText(FetchAll.this, "sorry", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void whatIsInList() {

        Log.d("LIST_UPDATE",""+image_modelList.size());

        for(Image_Model image_model : image_modelList)
        {
            Log.d("LIST_UPDATE",image_model.getIMAGE_NAME());
        }

    }
}