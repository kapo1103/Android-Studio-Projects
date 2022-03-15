package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.BesinRecyclerAdapter
import com.example.myapplication.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*


class BesinListesiFragment : Fragment() {
    private lateinit var viewModel : BesinListesiViewModel
    private val recyclerBesinAdapter = BesinRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BesinListesiViewModel :: class.java)
        viewModel.refresData()

        besinListRecycler.layoutManager = LinearLayoutManager(context)
        besinListRecycler.adapter = recyclerBesinAdapter

        swipeRefreshLayout.setOnRefreshListener {
            besinYukleniyor.visibility = View.VISIBLE
            besinHataMesaji.visibility = View.GONE
            besinListRecycler.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    fun observeLiveData(){  // recyclerview , hata mesajı, yukleniyor yazısı kontrol

        viewModel.besinler.observe(viewLifecycleOwner, Observer { besinler ->
            besinler?.let {
                besinListRecycler.visibility = View.VISIBLE
                recyclerBesinAdapter.besinListesininGuncelle(besinler)
            }

        })

        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if(it){
                    besinHataMesaji.visibility = View.VISIBLE
                    besinListRecycler.visibility = View.GONE
                }
                else {
                    besinHataMesaji.visibility = View.GONE
                }
            }
        })

        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if(it){
                    besinListRecycler.visibility = View.GONE
                    besinHataMesaji.visibility = View.GONE
                    besinYukleniyor.visibility = View.VISIBLE
                }
                else {
                    besinYukleniyor.visibility = View.GONE
                }
            }
        })

    }
}