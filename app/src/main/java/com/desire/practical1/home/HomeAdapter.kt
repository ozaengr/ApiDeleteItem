package com.desire.practical1.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desire.practical1.api.User
import com.desire.practical1.databinding.RcvItemBinding

class HomeAdapter(var listOfData: ArrayList<User>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    fun deleteItem(id : Int){
        listOfData.removeAt(id)
        notifyDataSetChanged()
    }
    var onItemClick: ((User) -> Unit)? = null

    inner class HomeViewHolder(var view: RcvItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(user: User, position: Int) {
            view.tvName.text = user.title
            view.tvDescription.text = user.category
            Glide.with(view.imageProduct).load(user.image).into(view.imageProduct)
            view.btnDelete.setOnClickListener {
                onItemClick?.invoke(listOfData.get(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view = RcvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }


    override fun getItemCount(): Int {
        return listOfData.size
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(listOfData[position], position)
    }


}
