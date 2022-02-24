package com.efh.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.efh.myapplication.ListeSınıfı
import com.efh.myapplication.R
import com.efh.myapplication.RvAdapter
import com.efh.myapplication.databinding.FragmentAnaSayfaBinding
import kotlinx.android.synthetic.main.fragment_ana_sayfa.*


class AnaSayfaFragment : Fragment() {
    private var _binding : FragmentAnaSayfaBinding? = null
    private val binding get() = _binding!!
    private lateinit var alList : ArrayList<ListeSınıfı>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        alList = ArrayList<ListeSınıfı>()


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


        //recyclerView.layoutManager = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
        //binding.recyclerView.layoutManager = LinearLayoutManager()
        //val RvAdapter = RvAdapter(alList)
        //binding.recyclerView.adapter = RvAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnaSayfaBinding.inflate(inflater,container,false)
        val view = binding.root
        //binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = GridLayoutManager(activity,2,GridLayoutManager.VERTICAL,false)
        val RvAdapter = RvAdapter(alList)
        binding.recyclerView.adapter = RvAdapter
        return view
        // Inflate the layout for this fragment

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
