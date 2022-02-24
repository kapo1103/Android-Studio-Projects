package com.efh.myapplication

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.efh.myapplication.databinding.ActivityMainBinding
import com.efh.myapplication.fragments.AnaSayfaFragment
import com.efh.myapplication.fragments.BizKimizFragment
import com.efh.myapplication.fragments.KategoriFragment
import kotlinx.android.synthetic.main.fragment_kategori.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var alList: ArrayList<ListeSınıfı>

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)



        replaceFragment(AnaSayfaFragment()) //ilk olarak anasayfa fragment açılması için

        //alList = ArrayList<ListeSınıfı>()

        binding.nav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.anaSayfa -> {
                    replaceFragment(AnaSayfaFragment())
                    true
                }
                R.id.kategori -> {
                    replaceFragment(KategoriFragment())
                    true
                }
                R.id.bizKimiz -> {
                    replaceFragment(BizKimizFragment())
                    true
                }
                else -> false
            }
        }




    }

    /* //data
        val pisa = ListeSınıfı("60TL",R.drawable.pembe)
        val colosseum = ListeSınıfı("45TL",R.drawable.bluz)
        val eyfel = ListeSınıfı("35TL",R.drawable.cicek)
        val londonBridge = ListeSınıfı("30TL",R.drawable.pantolon)
        val etek = ListeSınıfı("25TL",R.drawable.etek)
        val ayakkabi = ListeSınıfı("20TL",R.drawable.ayakkabi)
        val ayakkabiguzel = ListeSınıfı("20TL",R.drawable.ayakkabiguzel)
        val canta = ListeSınıfı("20TL",R.drawable.canta)


        alList.add(pisa)
        alList.add(colosseum)
        alList.add(eyfel)
        alList.add(londonBridge)
        alList.add(etek)
        alList.add(ayakkabi)
        alList.add(ayakkabiguzel)
        alList.add(canta)

        binding.recyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        //binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val RvAdapter = RvAdapter(alList)
        binding.recyclerView.adapter = RvAdapter


*/

    // val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
    //recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    //val adapter =



    private fun replaceFragment(fragment: Fragment) {  // FRAGMENT GEÇİŞ FONKSİYONU
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}

