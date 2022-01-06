package com.nsa.quantumassignmentapp.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.quantumassignmentapp.databinding.LooperLayoutBinding

class LoopingAdapter(private var list:ArrayList<Drawable>) : RecyclerView.Adapter<LoopingAdapter.ViewHolder>() {
    private lateinit var binding: LooperLayoutBinding
    inner class ViewHolder(binding: LooperLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(drawable: Drawable) {
            binding.apply {
                imageView.setImageDrawable(drawable)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= LooperLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list.get(position))
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}