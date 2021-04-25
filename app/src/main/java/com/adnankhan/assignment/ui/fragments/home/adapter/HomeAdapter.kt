package com.adnankhan.assignment.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.adnankhan.assignment.R
import com.adnankhan.assignment.data.model.Drinks

class HomeAdapter(val list: MutableList<Drinks>, private val listener: OnClickListener) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_drink, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDrinkName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDrinkRec: TextView = itemView.findViewById(R.id.tv_desc)
        val ivDrink: ImageView = itemView.findViewById(R.id.siv_dp)
        val ivFav: ImageView = itemView.findViewById(R.id.iv_fav)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.tvDrinkName.text = data.strDrink
        holder.tvDrinkRec.text = data.strInstructions
        Glide.with(holder.ivDrink.context)
            .load(data.strDrinkThumb)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.ivDrink)
        if (data.isFavorite) {
            holder.ivFav.setImageResource(R.drawable.yellow_star)
        } else {
            holder.ivFav.setImageResource(R.drawable.ic_star_empty)
        }
        holder.ivFav.setOnClickListener {
            listener.onClickFav(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<Drinks>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClickFav(drinks: Drinks)
    }
}