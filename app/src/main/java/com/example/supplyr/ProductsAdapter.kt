package com.example.supplyr

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(val productTitles : ArrayList<String>, val productImages : ArrayList<Int>, val productPrices : ArrayList<Double>,
val productCondition : ArrayList<String>, context : Context): RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {
    class MyViewHolder( item : View): RecyclerView.ViewHolder(item) {
        var productImg : ImageView
        var productTitle : TextView
        var productCondition : TextView
        var productPrice : TextView
        var productLayout : ConstraintLayout
        var context : Context
        init {
            context = item.context
            productImg = item.findViewById(R.id.productImg)
            productTitle = item.findViewById(R.id.productTitle)
            productPrice = item.findViewById(R.id.productPrice)
            productCondition = item.findViewById(R.id.productCondition)
            productLayout = item.findViewById(R.id.productLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val product = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return MyViewHolder( product)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productTitle.text = productTitles[position]
        holder.productPrice.text = "${productPrices[position].toString()}Ron"
        holder.productCondition.text = productCondition[position]
        holder.productImg.setImageResource(productImages[position])
        holder.productLayout.setOnClickListener{
            val intent = Intent(holder.context, ProductActivity::class.java)
            intent.putExtra("name", productTitles[position])
            intent.putExtra("price", productPrices[position])
            intent.putExtra("condition", productCondition[position])
            intent.putExtra("images", productImages[position])
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productTitles.size
    }
}