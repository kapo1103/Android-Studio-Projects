package com.efh.fotografpaylasma.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.efh.fotografpaylasma.R
import com.efh.fotografpaylasma.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_row.view.*

class HaberRecyclerAdapter(private val postList : ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclerAdapter.PostHolder>(){

    class PostHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.itemView.emailTextView.text = postList.get(position).kullaniciemail
        holder.itemView.yorumTextView.text = postList.get(position).kullaniciyorum
        Picasso.get().load(postList[position].gorselurl).into(holder.itemView.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}