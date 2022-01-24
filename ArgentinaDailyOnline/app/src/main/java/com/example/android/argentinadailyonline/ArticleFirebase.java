package com.example.android.argentinadailyonline;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ArticleFirebase {

    private final static String TAG_FIRESTORE = "Firestore Firebase";
    private final static String TAG_STORAGE = "Storage  Firebase";
    private final static String ARTICLES_COLLECTION = "articulos";
    ;
    private MutableLiveData<List<Article>> myLiveDataList;
    private List<Article> myList;

    private final static String CATEGORY_BUSINESS = "Economía";
    private static final String CATEGORY_POLITICS = "Politica";
    private final static String CATEGORY_TECHNOLOGY = "Tecnología";
    private static final String CATEGORY_WORLD = "Internacional";

    private CollectionReference articlesCollection;
    private StorageReference storageReference;

    ArticleFirebase() {
        myList = new ArrayList<>();
        myLiveDataList = new MutableLiveData<>();

        //private  FirebaseFirestore db = FirebaseFirestore.getInstance(); ;

        articlesCollection = FirebaseFirestore.getInstance().collection(ARTICLES_COLLECTION);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    LiveData<List<Article>> getAllArticles() {
        if (myList.isEmpty()) {

            articlesCollection.whereEqualTo("category", CATEGORY_BUSINESS)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG_FIRESTORE, "get " + document.getId() + " =>  Successfull");
                                    Article data = document.toObject(Article.class);
                                    downloadImage(data);
                                }
                            } else {
                                Log.d(TAG_FIRESTORE, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
        return myLiveDataList;
    }

    LiveData<List<Article>> getAllBusinessArticles() {
        if (myList.isEmpty()) {
            articlesCollection.whereEqualTo("category", CATEGORY_BUSINESS)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG_FIRESTORE, "get " + document.getId() + " =>  Successfull");
                                    Article data = document.toObject(Article.class);
                                    downloadImage(data);
                                }
                            } else {
                                Log.d(TAG_FIRESTORE, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }

        return myLiveDataList;
    }

    LiveData<List<Article>> getAllPoliticsArticles() {

        if (myList.isEmpty()) {

            articlesCollection.whereEqualTo("category", CATEGORY_POLITICS)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG_FIRESTORE, "get " + document.getId() + " =>  Successfull");
                                    Article data = document.toObject(Article.class);
                                    downloadImage(data);
                                }
                            } else {
                                Log.d(TAG_FIRESTORE, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }

        return myLiveDataList;
    }


    LiveData<List<Article>> getAllTechnologyArticles() {

        if (myList.isEmpty()) {
            articlesCollection.whereEqualTo("category", CATEGORY_TECHNOLOGY)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG_FIRESTORE, "get " + document.getId() + " =>  Successfull");
                                    Article data = document.toObject(Article.class);
                                    downloadImage(data);
                                }
                            } else {
                                Log.d(TAG_FIRESTORE, "Error getting documents: ", task.getException());
                            }
                        }
                    });

        }
        return myLiveDataList;
    }


    LiveData<List<Article>> getAllWorldArticles() {
        if (myList.isEmpty()) {
            articlesCollection.whereEqualTo("category", CATEGORY_WORLD)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                    Log.d(TAG_FIRESTORE, "get " + document.getId() + " =>  Successfull");
                                    Article data = document.toObject(Article.class);
                                    downloadImage(data);
                                }
                            } else {
                                Log.d(TAG_FIRESTORE, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }
        return myLiveDataList;
    }


    private void downloadImage(Article data) {

        final long ONE_MEGA_BYTE = 1024 * 1024;
        final Article mData = data;

        StorageReference mImageRef = storageReference.child(data.imageRef);

        mImageRef.getBytes(ONE_MEGA_BYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Log.d(TAG_STORAGE, "Successful!");
                        mData.imageByte = bytes;
                        updateList(mData);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG_STORAGE, "Fail");
            }
        });
    }

    private void updateList(Article data) {
        myList.add(data);
        myLiveDataList.setValue(myList);
    }
}