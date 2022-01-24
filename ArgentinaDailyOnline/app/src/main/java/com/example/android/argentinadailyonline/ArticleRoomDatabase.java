package com.example.android.argentinadailyonline;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
/*
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleRoomDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

    private static ArticleRoomDatabase INSTANCE;
    private static Context mContext;

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(INSTANCE, mContext).execute();
        }
    };

    public static ArticleRoomDatabase getDatabase(final Context context) {
        mContext = context;
        //Check that there is only 1 instance of database
        if (INSTANCE == null) {
            synchronized (ArticleRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Create THE Instance of Database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ArticleRoomDatabase.class, "article_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsyncTask extends AsyncTask<ArticleRoomDatabase, Void, Void> {
        private final ArticleDao mDao;
        private static Context mContext;

        //Data to populate de db

        public PopulateDbAsyncTask(ArticleRoomDatabase db, Context context) {
            mDao = db.articleDao();
            mContext = context;
        }

        @Override
        protected Void doInBackground(ArticleRoomDatabase... articleRoomDatabases) {


            TypedArray articlesDetailsArray;
            String[] stringArray;
            int imageResource;
            Article article;

            //Clear the database
            mDao.deleteAll();


            //Initialize data

            TypedArray articlesArray = mContext.getResources().obtainTypedArray(R.array.articles_array);

            for (int i = 0; i < articlesArray.length(); i++) {

                articlesDetailsArray = mContext.getResources().obtainTypedArray(articlesArray.getResourceId(i, 0));

                stringArray =  mContext.getResources().getStringArray(articlesDetailsArray.getResourceId(0, 0));
                imageResource = articlesDetailsArray.getResourceId(1, 0);

                article = new Article(stringArray[0], stringArray[1], imageResource, stringArray[2], stringArray[3], stringArray[4], stringArray[5], stringArray[6]);
                mDao.insert(article);

                articlesDetailsArray.recycle();

            }
            articlesArray.recycle();

            return null;
        }
    }
}
*/