package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Besin

class BesinDetayiViewModel : ViewModel() {

    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAl(){

        val muz = Besin("muz","1","1","1","1","www.test.com",)
        besinLiveData.value = muz

    }
}