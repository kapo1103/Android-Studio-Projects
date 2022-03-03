package com.efh.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class LandmarkAdapter( val LandmarkList : ArrayList<Landmark>) : RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {

    class LandmarkHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return LandmarkHolder(itemView)

    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.itemView.textView.text = LandmarkList.get(position).name

    }

    override fun getItemCount(): Int {
        return LandmarkList.size

    }
}