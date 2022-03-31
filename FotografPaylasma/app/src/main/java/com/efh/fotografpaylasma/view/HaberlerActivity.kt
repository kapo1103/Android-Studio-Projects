package com.efh.fotografpaylasma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.efh.fotografpaylasma.model.Post
import com.efh.fotografpaylasma.R
import com.efh.fotografpaylasma.adapter.HaberRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_haberler.*
import java.util.ArrayList

class HaberlerActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseFirestore
    private lateinit var recyclerViewAdapter : HaberRecyclerAdapter
    private lateinit var postListesi : ArrayList<Post>

    //val postListesi = ArrayList<Post>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haberler)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        postListesi = ArrayList<Post>()

        verileriAl()


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter = HaberRecyclerAdapter(postListesi)
        recyclerView.adapter = recyclerViewAdapter
        println("aaabbbbba")


    }
    private fun verileriAl(){
// .addSnapshotListener devamlı güncellenir ör whatsapp       .get tek seferde veri getirir
        // orderby postları tarihe göre sıralamada kullanılır "tarih" data basedeki ad
        database.collection("post").orderBy("tarih", Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if (exception != null) { // hata kontrolu
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
                println("ilkk")
            }
            else {
                println("ikincii")
                if (snapshot != null) {
                    println("ucuncuu")
                    if (!snapshot.isEmpty) { //snapshot.isEmpty == false
                        println("dorduncu")

                        postListesi.clear()

                        val documents = snapshot.documents

                    println("besinci")

                        for (document in documents) { //"kullaniciemail", "kullaniciyorum", "gorselurl" bunlar databasedeki isimlerlerle aynı olmalı
                            val kullaniciemail = document.get("kullaniciemail") as String
                            val kullaniciyorum= document.get("kullaniciyorum") as String
                            val gorselurl = document.get("gorselurl") as String

                            println(kullaniciemail)

                            val indirilenPost = Post(kullaniciemail,kullaniciyorum, gorselurl)
                            postListesi.add(indirilenPost)
                            println("yedinci")
                        }
                        recyclerViewAdapter.notifyDataSetChanged() // yenile yap demek
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater  //haberleractivity ile menuyu bağlama
        menuInflater.inflate(R.menu.secenekler_menusu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.fotograf_paylas) {

            val intent = Intent(this, FotografPaylasmaActivity::class.java)
            startActivity(intent)

        }
        else if (item.itemId == R.id.cikis_yap){
            auth.signOut() // firebaseden çıkış yap
            val intent = Intent(this, KullaniciActivity::class.java)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}