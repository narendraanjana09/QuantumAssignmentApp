package com.nsa.quantumassignmentapp.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.quantumassignmentapp.databinding.Level3LayoutBinding
import com.nsa.quantumassignmentapp.databinding.LooperLayoutBinding

class Level3_Adapter(private var list:ArrayList<Drawable>) : RecyclerView.Adapter<Level3_Adapter.ViewHolder>() {
    private lateinit var binding: Level3LayoutBinding
    inner class ViewHolder(binding: Level3LayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(drawable: Drawable) {
            binding.apply {
                imageView.setImageDrawable(drawable)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= Level3LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list.get(position))
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}