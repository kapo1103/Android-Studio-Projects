package com.example.myapplication.servis

import com.example.myapplication.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

interface BesinAPI {

    // https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json veri alacağımız site

    //BASE_URL https://raw.githubusercontent.com
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getBesin() : Single<List<Besin>>
}