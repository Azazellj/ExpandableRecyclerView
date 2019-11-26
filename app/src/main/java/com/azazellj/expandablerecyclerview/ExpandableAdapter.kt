package com.azazellj.expandablerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by azazellj on 11/25/19.
 */
class ExpandableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_BRAND = 0
    private val TYPE_SHOP = 1

    val data = mutableListOf<Any>()

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is Brand) TYPE_BRAND else TYPE_SHOP
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BRAND -> BrandViewHolder(parent.inflate(R.layout.item_brand))
            TYPE_SHOP -> ShopViewHolder(parent.inflate(R.layout.item_shop))
            else -> throw IllegalArgumentException("wrong viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        when (holder) {
            is BrandViewHolder -> {
                holder.bind(item as Brand, this::swap)
//                if (holder.adapterPosition == 0) {
//                    holder.itemView.setBackgroundResource(R.drawable.background_first)
//                }
//                if (holder.adapterPosition == itemCount - 1) {
//                    holder.itemView.setBackgroundResource(R.drawable.background_last)
//                }
            }
            is ShopViewHolder -> holder.bind(item as Shop)
        }
    }

    override fun getItemCount(): Int = data.size

    private fun swap(position: Int, isExpanded: Boolean) {
        if (isExpanded) {
            val brand = data[position] as Brand
            data.addAll(position + 1, brand.shops)
            notifyItemRangeInserted(position + 1, brand.shops.size)
        } else {
            val brand = data[position] as Brand

            for (i in 0 until brand.shops.size) {
                data.removeAt(position + 1)
            }
            notifyItemRangeRemoved(position + 1, brand.shops.size)
        }
    }
}


class BrandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val address = itemView.findViewById<TextView>(R.id.address)
    private val expander = itemView.findViewById<ImageView>(R.id.expander)

    init {
        itemView.tag = false
    }

    fun bind(brand: Brand, func: (Int, Boolean) -> Unit) {
        image.setImageResource(brand.image)
        name.text = brand.name
        address.text = brand.address
        expander.setOnClickListener {
            it.animate()
                .rotationBy(180f * 3)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
            val newTag = !(itemView.tag as Boolean)
            itemView.tag = newTag
            func.invoke(adapterPosition, newTag)
        }
    }
}

class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val address = itemView.findViewById<TextView>(R.id.address)

    init {
    }

    fun bind(shop: Shop) {
        image.setImageResource(shop.image)
        name.text = shop.name
        address.text = shop.address
    }
}

fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}
