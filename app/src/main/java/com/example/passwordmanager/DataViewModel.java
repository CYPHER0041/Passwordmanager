package com.example.passwordmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private final LiveData<List<Data>> mData;
    private static DataRepository mRepository;
    public DataViewModel(@NonNull Application application) {
        super(application);
        mRepository=new DataRepository(application);
        mData=mRepository.getAllOrderedUrl();
    }
    LiveData<List<Data>> getAllData(){return mData;}
    public void insert(Data data) { mRepository.insert(data); }
    public static void deleteData(Data data){mRepository.deleteData(data);}
    public void updateData(Data data){mRepository.update(data);}
    public static void deleteAll(){mRepository.deleteAll();}

}
