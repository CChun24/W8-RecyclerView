package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val studentList: ArrayList<Student>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cur = studentList[position]
        holder.imgView.setImageResource(R.drawable.ic_baseline_tag_faces_24)
        holder.txtViewName.text = cur.name
        holder.txtViewProgramme.text = cur.programme
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val imgView : ImageView = view.findViewById(R.id.imgPhoto)
        val txtViewName : TextView = view.findViewById(R.id.tvName)
        val txtViewProgramme : TextView = view.findViewById(R.id.tvProgramme)

    }
}