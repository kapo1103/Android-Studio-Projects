package com.example.myapplication.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Besin

@Dao
interface BesinDAO {
    //data access objects veritabanına ulaştıgımız ve verileri eklediğimiz çektimiz

    @Insert // veri ekleme
    suspend fun  insertAll(vararg besin : Besin) : List<Long>

    // insert -> room, insert into
    // suspend -> coroutine scope
    // vararg -> birden fazla ve istediğimiz sayıda besin
    // List<Long> -> long, döndürdük id'ler için

    @Query("SELECT * FROM besin") // veri alma
    suspend fun getAllBesin() : List<Besin>

    @Query("SELECT * FROM besin Where uuid =:besinId ")
    suspend fun getBesin(besinId : Int) : Besin

    @Query("DELETE FROM besin") //veri silme
    suspend fun deleteAllBesin()
}