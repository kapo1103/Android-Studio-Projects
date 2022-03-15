package com.example.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.BesinRecyclerRowBinding
import com.example.myapplication.model.Besin
import com.example.myapplication.util.gorselIndir
import com.example.myapplication.util.placeHolderYap
import com.example.myapplication.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_besin_listesi.view.*

class BesinRecyclerAdapter(val besinListesi : ArrayList<Besin> ) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>(),BesinClickListener {

    class BesinViewHolder(var view : BesinRecyclerRowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view = inflater.inflate(R.layout.besin_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater,R.layout.besin_recycler_row,parent,false)
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

        holder.view.besin = besinListesi[position]
        holder.view.listener = this
        /*
        holder.itemView.isimText.text = besinListesi.get(position).besinIsim
        holder.itemView.kaloriText.text = besinListesi.get(position).besinKalori
        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
          }
          //glide
         holder.itemView.imageView.gorselIndir(besinListesi.get(position).besinGorsel, placeHolderYap(holder.itemView.context))
           */


    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    fun besinListesininGuncelle(yeniBesinListesi : List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }

    override fun besinTiklandi(view: View) {
        var uuid = view.besin_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }
}