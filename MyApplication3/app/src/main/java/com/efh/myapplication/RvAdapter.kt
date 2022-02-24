package com.efh.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RvAdapter(var alList : ArrayList<ListeSınıfı>) : RecyclerView.Adapter<RvAdapter.LandmarkHolder>(){
    class LandmarkHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var textView : TextView
        var imageView : ImageView

        init {
            textView = itemView.findViewById(R.id.textView)
            imageView = itemView.findViewById(R.id.imageView)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {

        val binding = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)// list_item oluşturmuştuk
    return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {

        holder.textView.text = alList.get(position).ad
        holder.imageView.setImageResource(alList.get(position).resimId)

        //tvMetin.text = alList.get(position)

    }

    override fun getItemCount(): Int {
        return alList.size  // liste içeriği kadar çalış demek
    }
}