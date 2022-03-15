package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Besin
import com.example.myapplication.servis.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : BaseViewModel(application) {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(uuid : Int){
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }
}