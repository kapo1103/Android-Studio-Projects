package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Besin

class BesinListesiViewModel : ViewModel() { // fragment besin listesi kontrol etmek için
    val besinler = MutableLiveData<List<Besin>>()  // recyclerda veri kontrolu
    val besinHataMesaji = MutableLiveData<Boolean>()  // hata mesajı kontrolu
    val besinYukleniyor = MutableLiveData<Boolean>()  // yuklenıyor yazısı kontrolu


    fun refresData(){
        val muz = Besin("muz","1","1","1","1","www.test.com",)
        val muz1 = Besin("muz1","12","12","12","12","www.test.com",)
        val muz2 = Besin("muz2","13","13","13","13","www.test.com",)

        val besinListesi = arrayListOf<Besin>(muz,muz1,muz2)

        besinler.value = besinListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }
}