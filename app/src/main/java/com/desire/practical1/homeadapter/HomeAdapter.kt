package com.desire.practical1.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desire.practical1.api.User
import com.desire.practical1.databinding.RcvItemBinding

class HomeAdapter(
    var listOfData: ArrayList<User>
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    fun deleteItem(id : Int){
        listOfData.removeAt(id)
        notifyDataSetChanged()
    }
    var onItemClick: ((User) -> Unit)? = null
    var onClickItem: ((User) -> Unit)? = null

    inner class HomeViewHolder(var view: RcvItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(user: User, position: Int,onClickItem: ((User) -> Unit)?) {
            view.tvName.text = user.title
            view.tvDescription.text = user.category
            Glide.with(view.imageProduct).load(user.image).into(view.imageProduct)
            view.btnDelete.setOnClickListener {
                onItemClick?.invoke(listOfData.get(position))
            }
            view.root.setOnClickListener {
                onClickItem?.invoke(user)
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
        holder.bind(listOfData[position], position, onClickItem)
    }


}
