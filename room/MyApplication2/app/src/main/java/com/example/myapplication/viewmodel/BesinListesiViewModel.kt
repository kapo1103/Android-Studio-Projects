package com.example.myapplication.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.Besin
import com.example.myapplication.servis.BesinAPIServis
import com.example.myapplication.servis.BesinDatabase
import com.example.myapplication.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application) : BaseViewModel(application) { // fragment besin listesi kontrol etmek için
    val besinler = MutableLiveData<List<Besin>>()  // recyclerda veri kontrolu
    val besinHataMesaji = MutableLiveData<Boolean>()  // hata mesajı kontrolu
    val besinYukleniyor = MutableLiveData<Boolean>()  // yuklenıyor yazısı kontrolu

    private var guncellemeZamani = 0.2 * 60 * 1000 * 1000 * 1000L //dakikanın nano time a çevrimi

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable()
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())



    fun refresData(){
        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani ){
            //sqlite tan çek
            verileriSQLitetanAl()
        }
        else{
            verileriInternettenAl()
        }
    }

    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    private fun verileriSQLitetanAl(){
        besinYukleniyor.value = true

        launch {
           val besinListesi = BesinDatabase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(),"besinleri room dan aldık",Toast.LENGTH_LONG).show()
        }
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
                        sqliteSakla(t)
                        Toast.makeText(getApplication(),"besinleri internetten dan aldık",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        // Hata alırsa

                        besinHataMesaji.value = true
                        besinYukleniyor.value = false
                        e.printStackTrace() // hatayı logcatte göstermeye yarar
                    }

                })
        )
    }

    private fun besinleriGoster(besinlerListesi : List<Besin>){
        besinler.value = besinlerListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }

    private fun sqliteSakla(besinListesi : List<Besin>){

        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinListesi.toTypedArray())
            var i = 0
            while (i < besinListesi.size){
                besinListesi[i].uuid = uuidListesi[i].toInt()
                i = i + 1
            }
            besinleriGoster(besinListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}