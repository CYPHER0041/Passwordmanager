package com.example.passwordmanager;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {
    private DataDao mDataDao;
    private LiveData<List<Data>> mOrderedURL;
    DataRepository (Application application) {
        DataRoomDatabase db = DataRoomDatabase.getDatabase(application);
        mDataDao = db.dataDao();
        mOrderedURL = mDataDao.getOrderedUrl();
    }
        LiveData<List<Data>> getAllOrderedUrl()
        {
            return mOrderedURL;
        }

    public void insert(Data delegate){

        new insertAsyncTask(mDataDao).execute(delegate);  //running on non ui thread to prevent app crash
    }
    public void update(Data delegate){

        new updateAsyncTask(mDataDao).execute(delegate);  //running on non ui thread to prevent app crash
    }
    
    private static class insertAsyncTask extends AsyncTask<Data,Void,Void> {
        private DataDao mAsyncTaskDao;

        insertAsyncTask(DataDao dao){
            mAsyncTaskDao=dao;
        }
        @Override
        protected Void doInBackground(final Data...params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Data,Void,Void> {
        private DataDao mAsyncTaskDao;

        updateAsyncTask(DataDao dao){
            mAsyncTaskDao=dao;
        }
        @Override
        protected Void doInBackground(final Data...params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private DataDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(DataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mDataDao).execute();
    }
    private static class deleteAsyncTask extends AsyncTask<Data, Void, Void> {
        private DataDao mAsyncTaskDao;

        deleteAsyncTask(DataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Data... params) {
            mAsyncTaskDao.deleteData(params[0]);
            return null;
        }
    }
    public void deleteData(Data delegate){
        new deleteAsyncTask(mDataDao).execute(delegate);
    }

}


