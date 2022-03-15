package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Besin
import com.example.myapplication.servis.BesinAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BesinListesiViewModel : ViewModel() { // fragment besin listesi kontrol etmek için
    val besinler = MutableLiveData<List<Besin>>()  // recyclerda veri kontrolu
    val besinHataMesaji = MutableLiveData<Boolean>()  // hata mesajı kontrolu
    val besinYukleniyor = MutableLiveData<Boolean>()  // yuklenıyor yazısı kontrolu

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()


    fun refresData(){
        verileriInternettenAl()

    }

    private fun verileriInternettenAl(){

        besinYukleniyor.value = true

        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Besin>>() {
                    override fun onSuccess(t: List<Besin>) {
                        // Başarılı olursa
                        besinler.value = t
                        besinHataMesaji.value = false
                        besinYukleniyor.value = false
                    }

                    override fun onError(e: Throwable) {
                        // Hata alırsa

                        besinHataMesaji.value = true
                        besinYukleniyor.value = true
                        e.printStackTrace() // hatayı logcatte göstermeye yarar
                    }

                })



        )

    }
}