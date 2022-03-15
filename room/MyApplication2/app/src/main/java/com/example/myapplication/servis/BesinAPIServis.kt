package com.example.myapplication.servis

import com.example.myapplication.model.Besin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class BesinAPIServis {

    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json veri alacağımız site

    //BASE_URL https://raw.githubusercontent.com
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // json formatının modele çevirme
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rxjava kullanılacağını belirtme rxjava kullanılmıyorsa gerek yok
        .build()
        .create(BesinAPI::class.java)

    fun getData() : Single<List<Besin>> {
        return api.getBesin()
    }


}