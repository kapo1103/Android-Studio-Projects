package com.example.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Besin
import com.example.myapplication.util.gorselIndir
import com.example.myapplication.util.placeHolderYap
import com.example.myapplication.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

class BesinRecyclerAdapter(val besinListesi : ArrayList<Besin> ) : RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>() {

    class BesinViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.besin_recycler_row,parent,false)
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemView.isimText.text = besinListesi.get(position).besinIsim
        holder.itemView.kaloriText.text = besinListesi.get(position).besinKalori


        holder.itemView.setOnClickListener {
            val action = BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        //glide
        holder.itemView.imageView.gorselIndir(besinListesi.get(position).besinGorsel, placeHolderYap(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    fun besinListesininGuncelle(yeniBesinListesi : List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }
}