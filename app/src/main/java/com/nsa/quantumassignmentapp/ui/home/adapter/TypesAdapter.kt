package com.nsa.quantumassignmentapp.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.quantumassignmentapp.databinding.LooperLayoutBinding
import com.nsa.quantumassignmentapp.databinding.TypesLayoutBinding
import com.nsa.quantumassignmentapp.ui.home.models.data

class TypesAdapter(private var list:ArrayList<data>) : RecyclerView.Adapter<TypesAdapter.ViewHolder>() {
    private lateinit var binding: TypesLayoutBinding
    inner class ViewHolder(binding: TypesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: data) {
            binding.apply {
                imageView.setImageDrawable(data.drawable)
                textview.setText(data.text)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= TypesLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list.get(position))
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}