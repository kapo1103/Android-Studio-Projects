package com.efh.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var LandmarkList : ArrayList<Landmark>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LandmarkList = ArrayList<Landmark>()

        val a = Landmark("fatih")
        val a1 = Landmark("fatih")
        val a2= Landmark("fatih")
        val ab = Landmark("fatih")
        val abc = Landmark("fatih")
        val ad = Landmark("fatih")
        val ac = Landmark("fatih")
        val af = Landmark("fatih")
        val ag = Landmark("fatih")
        val ah = Landmark("fatih")
        val aj = Landmark("fatih")
        val ak = Landmark("fatih")
        val al = Landmark("fatih")

        LandmarkList.add(a)
        LandmarkList.add(a1)
        LandmarkList.add(a2)
        LandmarkList.add(ab)
        LandmarkList.add(abc)
        LandmarkList.add(ad)
        LandmarkList.add(ac)
        LandmarkList.add(af)
        LandmarkList.add(ag)
        LandmarkList.add(ah)
        LandmarkList.add(aj)
        LandmarkList.add(ak)
        LandmarkList.add(al)


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = LandmarkAdapter(LandmarkList)
        recyclerView.adapter = adapter




    }
}
