package com.example.android.firebasetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference blaRef;

    String str1;
    String str2;
    int i ;
    double myPi ;
    boolean isWorking ;
    String imageStr;
    TextView tv;

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("test1/document1");

    private static final int STORAGE_PERMISSION_CODE = 101;


    @Override
    protected void onStart() {
        super.onStart();
        mDocRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){

                    str1 = documentSnapshot.getString("str1");
                    myPi = documentSnapshot.getDouble("myPi");
                    isWorking = documentSnapshot.getBoolean("isWorking");

                    String txt = str1+ String.valueOf(myPi)+Boolean.toString(isWorking);

                    tv = findViewById(R.id.textView);
                    tv.setText(txt);

                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do something
                tv.setText("Your pressed the button asshole!");
                dataFetch();
            }
        });



    }

    public void downloadByte(){

        //final StorageReference csRef = storageReference.child("images/cs.png");
        final StorageReference csRef = storageReference.child(imageStr);

        final long ONE_MEGA_BYTE = 1024 * 1024;

        csRef.getBytes(ONE_MEGA_BYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Log.d("DownloadByte", "Successful!");
                        ImageView iv = findViewById(R.id.imageView);
                        Glide.with(getApplicationContext()).load(bytes).into(iv);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("DownloadByte", "Fail");
            }
        });
    }


    public void uploadPutByte(){
        ImageView iv = findViewById(R.id.imageView);
        iv.setDrawingCacheEnabled(true);
        iv.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable)iv.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        blaRef = storageReference.child("images/bla.png");


        UploadTask uploadTask = blaRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("UploadByte","Unsuccessful");

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("UploadByte", "Successful!");
            }
        });
    }

//THE FILE HAS TO BE IN THE DEVICE RUNNING THE APP
    public  void uploadPutStream()  {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE  );
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File( "/storage/emulated/0/DCIM/Camera/IMG_20200507_170425.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StorageReference blablaRef = storageReference.child("images/bla.png");


        UploadTask uploadTask = blablaRef.putStream(stream);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("PutStream","Unsuccessful");

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("PutStream", "Successful!");
            }
        });
    }

    //THE FILE HAS TO BE IN THE DEVICE RUNNING THE APP
    public void uploadPutFile(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE  );
        Uri file = Uri.fromFile(new File( "/storage/emulated/0/DCIM/Camera/IMG_20200507_170425.jpg"));
//        Uri file = Uri.parse( "file:///home/manus/AndroidStudioProjects/Firebase/app/src/main/res/drawable/blabla.png");

        StorageReference blablaRef = storageReference.child("images/" + file.getLastPathSegment());



        UploadTask uploadTask = blablaRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("PutFile","Unsuccessful");

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("PutFile", "Successful!");
            }
        });


    }


    public void print(String str1, double myPi, Boolean isWorking){
        Toast.makeText(this, str1+ String.valueOf(myPi)+Boolean.toString(isWorking), Toast.LENGTH_LONG).show();
    }


    public void dataFetch(){
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){

                    //Option 1
                    str1 = documentSnapshot.getString("str1");
                    myPi = documentSnapshot.getDouble("myPi");
                    isWorking = documentSnapshot.getBoolean("isWorking");
                    imageStr = documentSnapshot.getString("image_storage");

                    print(str1, myPi, isWorking);
                    downloadByte();


                    //Option 2
                    /*
                    Map<String, Object> data = documentSnapshot.getData();
                    print(data.get("str1").toString(), (Double) data.get("myPi"), (Boolean) data.get("isWorking"));
                    */

                    //Option 3
                    /*
                    TestClass data = documentSnapshot.toObject(TestClass.class);
                    print(data.getStr1(), data.getMyPi(),data.getIsWorking());
                    */


                }
            }
        });


    }


    public void dataSave(){
        String str1 = "this is my first string";
        String str2 = "this is my second string";
        int i = 4;
        double myPi = 3.14156;
        boolean isWorking = true;




        Map<String, Object> dataToSave = new HashMap<String, Object>();

        dataToSave.put("str1", str1);
        dataToSave.put("str2", str2);
        dataToSave.put("i", i);
        dataToSave.put("myPi", myPi);
        dataToSave.put("isWorking", isWorking);


        mDocRef.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Log.d("Database", "Saved!");
                else
                    Log.w("Database", "Oh no", task.getException());
            }
        });

    }


}
