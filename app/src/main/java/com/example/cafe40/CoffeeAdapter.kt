package com.example.cafe40

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CoffeeAdapter : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {
    private var coffeeList = emptyList<Coffee>()

    class CoffeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeName: TextView = itemView.findViewById(R.id.coffeeName)
        val coffeeImage: ImageView = itemView.findViewById(R.id.coffeeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val currentItem = coffeeList[position]
        holder.coffeeName.text = currentItem.name
        Picasso.get().load(currentItem.img).into(holder.coffeeImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, CoffeeDetailActivity::class.java)
            intent.putExtra("COFFEE_NAME", currentItem.name)
            intent.putExtra("COFFEE_DESCRIPTION", currentItem.description)
            intent.putExtra("COFFEE_IMAGE", currentItem.img)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    fun submitList(list: List<Coffee>) {
        coffeeList = list
        notifyDataSetChanged()
    }
}
